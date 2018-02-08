from flask import Flask, render_template, request, redirect

app = Flask(__name__)

@app.route('/')
def default():
	return render_template('index.html')

@app.route('/results', methods=['POST'])
def process():
	print request.form
	return render_template('results.html', name=request.form.get('name'), language=request.form.get('language'), location=request.form.get('location'), comments=request.form.get('comments'))

app.run(debug=True)