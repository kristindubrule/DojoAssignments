from flask import Flask, render_template, request, redirect

app = Flask(__name__)

@app.route('/')
def default():
	return render_template('index.html')

@app.route('/ninja')
def allninjas():
	return render_template('ninjas.html', color='all')

@app.route('/ninja/<ninja_color>')
def someninjas(ninja_color):
	colors = "red,blue,purple,orange"
	if not ninja_color in colors:
		color = "invalid"
	return render_template('ninjas.html', color=ninja_color)

app.run(debug=True)