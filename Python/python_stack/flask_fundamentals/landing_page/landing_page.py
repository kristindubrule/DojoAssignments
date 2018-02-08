from flask import Flask, render_template

app = Flask(__name__)

@app.route('/')
def greet():
	return render_template('index.html')

@app.route('/ninjas')
def ninjas():
	return render_template('ninjas.html')

@app.route('/dojos/new')
def newdojo():
	return render_template('dojos.html')

app.run(debug=True)