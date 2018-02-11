from flask import Flask, render_template, request, redirect, session, flash
from datetime import datetime, date
import re

EMAIL_REGEX = re.compile(r'^[a-zA-Z0-9.+_-]+@[a-zA-Z0-9._-]+\.[a-zA-Z]+$')
NAME_REGEX = re.compile(r'^[^0-9+]+$')
PASSWORD_REGEX = re.compile(r'^(?=.*[A-Z])(?=.*\d)[A-Za-z\d\S]{8,}$')
# \s*\d\s*[A-Z]\s*|\s*[A-Z]\s*\d\s*

app = Flask(__name__)
app.secret_key = 'ThisIsSecret' # you need to set a secret key for security purposes

# our index route will handle rendering our form
@app.route('/')
def index():
  return render_template("index.html")

@app.route('/process', methods=['POST'])
def submit():
	if not NAME_REGEX.match(request.form['first_name']):
		flash("Invalid first name!")
	if not NAME_REGEX.match(request.form['last_name']):
		flash("Invalid last name!")
	if not EMAIL_REGEX.match(request.form['email']):
 		flash("Invalid email Address!")
 	if not PASSWORD_REGEX.match(request.form['password']):
 	 	flash("Password must be at least 8 characters and contain one uppercase letter and one number")
 	if len(request.form['birthdate']) < 1:
		flash("You must enter a birthdate")
	elif datetime.strptime(request.form['birthdate'],'%Y-%m-%d').date() >= date.today():
		flash("Birthdate must be earlier than today")
	if '_flashes' in session:
		return redirect('/')
	else:
		return render_template("results.html", first_name=request.form.get('first_name'), last_name=request.form.get('last_name'), email=request.form.get('email'), birthdate=request.form.get('birthdate'))

app.run(debug=True) # run our server
