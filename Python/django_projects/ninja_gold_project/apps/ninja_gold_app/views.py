from django.shortcuts import render, HttpResponse, redirect
import random

gold_values = { "farm": [10,20],
				"cave": [5,10],
				"house": [2,5],
				"casino": [-50,50]
			};

# the index function is called when root is visited
def index(request):
	if "activities" not in request.session:
		request.session["activities"] = []
	if "gold" not in request.session:
		request.session["gold"] = 0
	print "Gold ", request.session["gold"]
	return render(request, "ninja_gold_app/index.html")

def process_money(request):
	if request.method == "POST":
		building = request.POST["building"]		
	  	gold_change = random.randrange(gold_values[building][0], gold_values[building][1])
	  	request.session['gold'] += gold_change
	  	if gold_change > 0:
	  		request.session['activities'].append({ "string": "Earned {} golds from the {}!".format(gold_change,building),
	  												"class": "earn" })
	  	elif gold_change < 0:
	  		request.session['activities'].append({ "string": "Entered a casino and lost {} golds... Ouch.".format(gold_change),
	  												"class": "lose" })
	  	else:
	  		request.session['activities'].append({ "string": "Entered a casino and didn't win or lose. Could be worse.".format(gold_change),
	  												"class": "tie" })
		request.session.modified = True
	  	return redirect('/')

def reset(request):
	if "activities" in request.session:
		del request.session["activities"]
	if "gold" in request.session:
		del request.session["gold"]
	return redirect('/')