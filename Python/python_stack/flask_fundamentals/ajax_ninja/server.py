from flask import Flask, render_template, request, redirect

app = Flask(__name__)

@app.route('/')
def default():
	return render_template('index.html')

app.run(debug=True)