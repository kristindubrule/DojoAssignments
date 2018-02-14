from flask import Flask, request, redirect, render_template, session, flash
from mysqlconnection import MySQLConnector
from datetime import datetime, date
import re
import os, binascii, md5

EMAIL_REGEX = re.compile(r'^[a-zA-Z0-9.+_-]+@[a-zA-Z0-9._-]+\.[a-zA-Z]+$')
NAME_REGEX = re.compile(r'^[a-zA-Z]{2,}$')
PASSWORD_REGEX = re.compile(r'^(?=.*[A-Z])(?=.*\d)[A-Za-z\d\S]{8,}$')

app = Flask(__name__)
app.secret_key = 'ThisIsSecret' # you need to set a secret key for security purposes
mysql = MySQLConnector(app,'user_dashboard')

@app.route('/')
def index():
	return render_template('login.html')

@app.route('/login', methods=['POST'])
def login():
	query = "SELECT * from users where email_address=:email_address"
	data = {
		'email_address': request.form['email']
	}
	user = mysql.query_db(query,data)
	print user

	if len(user) != 0:
		encrypted_password = md5.new(request.form['password'] + user[0]['salt']).hexdigest()
		if user[0]['password'] == encrypted_password:
			session['id'] = user[0]['id']
			return render_template('logged_in.html')
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
	query = "SELECT * from users where email_address=:email_address"
	data = {
		'email_address': request.form['email']
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
		query = "INSERT INTO users (first_name, last_name, email_address, password, salt, created, "\
		+"updated) VALUES (:first_name, :last_name, :email_address, :password, :salt, NOW(), NOW())"
		data = {
			'first_name': request.form['first_name'],
			'last_name': request.form['last_name'],
			'email_address': request.form['email'],
			'password': hashed_pw,
			'salt': salt
		}
		user_id = mysql.query_db(query, data)
		session['id'] = user_id
		return render_template('logged_in.html')

@app.route('/logged_in')
def logged_in():
	if 'id' in session:
		print "logged in"
		return render_template('logged_in.html')
	else:
		return render_template('login.html')

@app.route('/register')
def register():
	return render_template('index.html')

@app.route('/logout')
def logout():
	session.pop('id')
	return redirect('/')

app.run(debug=True)