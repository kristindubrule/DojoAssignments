from flask import Flask, render_template, request, redirect, session, jsonify, json
import random
app = Flask(__name__)
app.secret_key = 'ThisIsSecret' # you need to set a secret key for security purposes

gold_values = { "farm": [10,20],
				"cave": [5,10],
				"house": [2,5],
				"casino": [-50,50]
			};

# our index route will handle rendering our form
@app.route('/')
def index():
	if 'gold' not in session:
  		session['gold'] = 0
  	if 'activities' not in session:
  		session['activities'] = []
  	return render_template("index.html")

@app.route('/process_money',methods=['POST'])
def process_money():
	building = request.form.get('building')
  	gold_change = random.randrange(gold_values[building][0], gold_values[building][1])
  	if gold_change > 0:
  		session['activities'].append("Earned {} golds from the {}!".format(gold_change,building))
  	elif gold_change < 0:
  		session['activities'].append("Entered a casino and lost {} golds... Ouch.".format(gold_change))
  	else:
  		session['activities'].append("Entered a casino and didn't win or lose. Could be worse.".format(gold_change))
  	session['gold'] += random.randrange(gold_values[building][0], gold_values[building][1])
  	return redirect('/')

@app.route('/process_moneyJ',methods=['POST'])
def process_moneyJ():
	data = json.loads(request.data)
  	print data["building"]
 	#return jsonify()
  	return redirect('/')

@app.route('/reset',methods=['POST'])
def reset():
	if 'gold' in session:
		session.pop('gold')
	if 'activities' in session:
		session.pop('activities')
  	return redirect('/')

app.run(debug=True) # run our server
