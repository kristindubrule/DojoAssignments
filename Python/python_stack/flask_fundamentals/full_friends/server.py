from flask import Flask, request, redirect, render_template, session, flash
from mysqlconnection import MySQLConnector
app = Flask(__name__)
mysql = MySQLConnector(app,'friendsdb')
@app.route('/')
def index():
	friends = mysql.query_db("SELECT *, TRUNCATE(DATEDIFF(CURDATE(),birthdate)/365,0) as age from friends")
	return render_template('index.html', all_friends=friends)

@app.route('/friends/<friend_id>')
def show(friend_id):
	query = "SELECT * FROM friends WHERE id = :specific_id"
	data = {'specific_id': friend_id}
	friends = mysql.query_db(query, data)
	return render_template('index.html', one_friend=friends[0])

@app.route('/friends', methods=['POST'])
def create():
	# add a friend to the database!
	query = "INSERT INTO friends (first_name, last_name, occupation, birthdate, created_at, "\
		+"updated_at) VALUES (:first_name, :last_name, :occupation, :birthdate, NOW(), NOW())"
	data = {
		'first_name': request.form['first_name'],
		'last_name': request.form['last_name'],
		'birthdate': request.form['birthdate'],
		'occupation': request.form['occupation']
	}
	mysql.query_db(query, data)
	return redirect('/')

@app.route('/update_friend/<friend_id>', methods=['POST'])
def update(friend_id):
    query = "UPDATE friends SET first_name = :first_name, last_name = :last_name, occupation = :occupation WHERE id = :id"
    data = {
             'first_name': request.form['first_name'],
             'last_name':  request.form['last_name'],
             'occupation': request.form['occupation'],
             'id': friend_id
           }
    mysql.query_db(query, data)
    return redirect('/')

@app.route('/remove_friend', methods=['POST'])
def delete():
    query = "DELETE FROM friends WHERE id = :id"
    data = {'id': request.form['remove_id']}
    mysql.query_db(query, data)
    return redirect('/')

app.run(debug=True)