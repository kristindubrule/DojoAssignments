from flask import Flask, render_template, request, redirect

app = Flask(__name__)

@app.route('/')
def default():
	return render_template('index.html', red=255, blue=255, green=255)

@app.route('/color_change', methods=['POST'])
def change_color():
	return render_template('index.html', red=request.form.get("red"), blue=request.form.get("blue"), green=request.form.get("green"))

app.run(debug=True)