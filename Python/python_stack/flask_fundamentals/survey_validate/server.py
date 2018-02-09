from flask import Flask, render_template, request, redirect, session, flash
import re

EMAIL_REGEX = re.compile(r'^[a-zA-Z0-9.+_-]+@[a-zA-Z0-9._-]+\.[a-zA-Z]+$')

app = Flask(__name__)
app.secret_key = 'ThisIsSecret' # you need to set a secret key for security purposes

# our index route will handle rendering our form
@app.route('/')
def index():
  return render_template("index.html")

@app.route('/process', methods=['POST'])
def submit():
	if len(request.form['name']) < 1:
		flash("Name cannot be empty!") # just pass a string to the flash function
	else:
		session["name"] = request.form['name']

	if len(request.form['comments']) > 120:
		flash("Comments should not be longer than 120 characters") # just pass a string to the flash function
	elif len(request.form['comments']) < 1:
		flash("Comments cannot be empty!")

	if len(request.form['email']) < 1:
		flash("Email cannot be blank!")
	elif not EMAIL_REGEX.match(request.form['email']):
 		flash("Invalid Email Address!")

	if '_flashes' in session:
		return redirect('/')
	else:
		return render_template("results.html", name=request.form.get('name'), language=request.form.get('language'), location=request.form.get('location'), comments=request.form.get('comments'))

app.run(debug=True) # run our server
