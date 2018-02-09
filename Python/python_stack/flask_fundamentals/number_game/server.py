from flask import Flask, render_template, request, redirect, session, jsonify, json
import random
app = Flask(__name__)
app.secret_key = 'ThisIsSecret' # you need to set a secret key for security purposes

# our index route will handle rendering our form
@app.route('/')
def index():
  	return render_template("index.html")

@app.route('/guess',methods=['POST'])
def guess():
	random_number = random.randrange(0, 101)
	data = json.loads(request.data)
	result = "invalid"
	color = "gray"
	if int(data["number"]) < random_number:
		result = "too low"
		color = "red"
	elif int(data["number"]) > random_number:
		result = "too high"
		color = "red"
	else:
		result = "correct"
		color = "green"
  	return jsonify(result=result, color=color)
app.run(debug=True) # run our server
