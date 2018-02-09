from flask import Flask, render_template, request, redirect, session
app = Flask(__name__)
app.secret_key = 'ThisIsSecret' # you need to set a secret key for security purposes

# our index route will handle rendering our form
@app.route('/')
def index():
	if 'counter' in session:
		session['counter'] += 1
	else:
		session['counter'] = 1
  	return render_template("index.html")

@app.route('/bytwo',methods=['POST'])
def bytwo():
	if 'counter' in session:
		session['counter'] += 2
	else:
		session['counter'] = 2
  	return render_template("index.html")

@app.route('/reset',methods=['POST'])
def reset():
	if 'counter' in session:
		session['counter'] = 0
  	return render_template("index.html")

app.run(debug=True) # run our server
