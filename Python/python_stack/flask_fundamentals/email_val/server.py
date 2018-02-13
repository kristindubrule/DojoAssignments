from flask import Flask, request, redirect, render_template, session, flash
from mysqlconnection import MySQLConnector
app = Flask(__name__)
mysql = MySQLConnector(app,'sakila')
@app.route('/')
def index():
	return render_template('index.html')

@app.route('/validate', methods=['POST'])
def validate():
	query = "SELECT * FROM customer WHERE email = :email"
	data = {'email': request.form['email']}
	customers = mysql.query_db(query, data)
	found = 1
	if customers:
		found = 2
	return render_template('index.html', customers=customers, found=found, email=request.form['email'])

@app.route('/delete', methods=['POST'])
def delete():
    #query = "DELETE FROM customer WHERE id IN (:ids)"
    #data = {'ids': ",".join(request.form['remove_id']}
    #mysql.query_db(query, data)
    return redirect('/')

app.run(debug=True)