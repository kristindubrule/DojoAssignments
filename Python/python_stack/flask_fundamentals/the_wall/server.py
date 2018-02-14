from flask import Flask, request, redirect, render_template, session, flash
from mysqlconnection import MySQLConnector
from datetime import datetime, date, timedelta
import re
import os, binascii, md5

EMAIL_REGEX = re.compile(r'^[a-zA-Z0-9.+_-]+@[a-zA-Z0-9._-]+\.[a-zA-Z]+$')
NAME_REGEX = re.compile(r'^[a-zA-Z]{2,}$')
PASSWORD_REGEX = re.compile(r'^(?=.*[A-Z])(?=.*\d)[A-Za-z\d\S]{8,}$')

app = Flask(__name__)
app.secret_key = 'ThisIsSecret' # you need to set a secret key for security purposes
mysql = MySQLConnector(app,'the_wall')

@app.route('/')
def landing():
	return render_template('login.html')

@app.route('/login', methods=['POST'])
def login():
	query = "SELECT * from users where email=:email"
	data = {
		'email': request.form['email']
	}
	user = mysql.query_db(query,data)

	if len(user) != 0:
		encrypted_password = md5.new(request.form['password'] + user[0]['salt']).hexdigest()
		if user[0]['password'] == encrypted_password:
			session['id'] = user[0]['id']
			session['name'] = user[0]['first_name'] + ' ' + user[0]['last_name']
			return redirect('the_wall')
		else:
			flash("Invalid login credentials")
	else:
		flash("Invalid login credentials")

	if '_flashes' in session:
		return redirect('/')

@app.route('/add_user', methods=['POST'])
def add_user():
	if not NAME_REGEX.match(request.form['first_name']):
		flash("First name must be only letters and at least 2 characters")
	if not NAME_REGEX.match(request.form['last_name']):
		flash("Last name must be only letters and at least 2 characters")
	if not EMAIL_REGEX.match(request.form['email']):
		flash("Email is invalid")
	if not PASSWORD_REGEX.match(request.form['password']):
		flash("Password must be at least 8 characters and include one number and one capital letter")
	if request.form['password'] != request.form['password_confirm']:
		flash("Password values do not match each other")
	# check to see if the user exists
	query = "SELECT * from users where email=:email"
	data = {
		'email': request.form['email']
	}
	user = mysql.query_db(query,data)
	if len(user) != 0:
		flash("User already exists")

	if '_flashes' in session:
		return redirect('/register')
	else:
		# Build password
		salt = binascii.b2a_hex(os.urandom(15))
		hashed_pw = md5.new(request.form['password'] + salt).hexdigest()

		# Insert new row
		query = "INSERT INTO users (first_name, last_name, email, password, salt, created_at, "\
		+"updated_at) VALUES (:first_name, :last_name, :email, :password, :salt, NOW(), NOW())"
		data = {
			'first_name': request.form['first_name'],
			'last_name': request.form['last_name'],
			'email': request.form['email'],
			'password': hashed_pw,
			'salt': salt
		}
		user_id = mysql.query_db(query, data)
		session['id'] = user_id
		session['name'] = request.form['first_name'] + ' ' + request.form['last_name']
		return render_template('the_wall.html')

@app.route('/the_wall')
def the_wall():
	if 'id' in session:
		query = "SELECT m.id as message_id, m.content as content, m.user_id as message_user_id, " \
		"m.created_at as message_created_at, DATE_FORMAT(m.updated_at,:date_str) as message_updated_at, " \
		"c.id as content_id, c.content as comment_content, c.user_id as comment_user_id, c.message_id as content_message_id, " \
		"DATE_FORMAT(c.updated_at,:date_str) as comment_updated_at, CONCAT_WS(' ',u.first_name,u.last_name) AS message_user, " \
		"CONCAT_WS(' ',cusers.first_name,cusers.last_name) as comment_user " \
		"from messages m "\
		"left join comments c on m.id = c.message_id " \
		"join users u on m.user_id = u.id " \
		"left join users cusers on c.user_id = cusers.id " \
		"order by m.created_at DESC, m.id DESC"
		data = { "date_str": '%M %e %Y'}
		message_data = mysql.query_db(query,data)
		messages = []
		message_comments = {}
		previous_message_id = None
		for message in message_data:
			if previous_message_id != message['message_id']:
				messages.append({"id": message['message_id'], \
					"user": message['message_user'], \
					"date": message['message_updated_at'], \
					"content": message['content'],
					"user_id": message['message_user_id'],
					"new": message['message_created_at'] >= datetime.now() - timedelta(minutes=30),
					"comments": []})
			if message['comment_content']:
				messages[-1]["comments"].append({"user": message['comment_user'], \
					"date": message['comment_updated_at'],
					"content": message['comment_content']})
			previous_message_id = message['message_id']
		return render_template('the_wall.html', messages=messages)
	else:
		return render_template('login.html')

@app.route('/register')
def register():
	return render_template('register.html')

@app.route('/logout')
def logout():
	session.pop('id')
	session.pop('name')
	return redirect('/')

@app.route('/post_message', methods=['POST'])
def post_message():
	# Insert new row
	query = "INSERT INTO messages (content, user_id, created_at, updated_at) VALUES (:content, :user_id, NOW(), NOW())"
	data = {
		"content": request.form['posted_message'],
		"user_id": session['id']
	}
	message = mysql.query_db(query, data)
	return redirect('/the_wall')

@app.route('/post_comment', methods=['POST'])
def post_comment():
	query = "INSERT INTO comments (content, user_id, message_id, created_at, updated_at) " \
	"VALUES (:content, :user_id, :message_id, NOW(), NOW())"
	data = {
	 	"content": request.form['comment'],
	 	"user_id": session['id'],
	 	"message_id": request.form['post_comment']
	}
	comment = mysql.query_db(query, data)
	return redirect('/the_wall')

@app.route('/delete/<message_id>')
def delete_message(message_id):
	data = {
		"message_id": message_id,
		"user_id": session['id']
	}
	query = "delete from comments where message_id=:message_id " \
		"and message_id IN (select id from messages where user_id=:user_id)"
	delete_query = mysql.query_db(query,data)

	query = "delete from messages where id=:message_id and user_id=:user_id"
	delete_query = mysql.query_db(query,data)
	return redirect('/the_wall')

app.run(debug=True)