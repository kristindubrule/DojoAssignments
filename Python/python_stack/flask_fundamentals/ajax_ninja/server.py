from flask import Flask, render_template, request, redirect, jsonify, json

app = Flask(__name__)

@app.route('/')
def default():
	return render_template('index.html')

@app.route('/changecolor',methods=['GET','POST'])
def changecolor():
	# print request.args
	# color = request.args.get('color')
	# content = request.json
	# print content
	# print "Content ", content.color
	data = json.loads(request.data)
	return jsonify(color=data["color"])
	#return render_template('index.html')
app.run(debug=True)