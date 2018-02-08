from flask import Flask, render_template, request, redirect

app = Flask(__name__)

@app.route('/')
def greet():
	return render_template('index.html')

@app.route('/process', methods=['POST'])
def process():
	print request.form.get('name')
	return redirect('/')

app.run(debug=True)