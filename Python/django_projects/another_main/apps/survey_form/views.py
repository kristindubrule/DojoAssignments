from django.shortcuts import render, redirect

# Create your views here.
def index(request):
	return render(request, "survey_form/index.html")

def process(request):
	if request.method == "POST":
		answers = {
			"name": request.POST['first_name']
		}
		request.session["name"] = request.POST['first_name']
		request.session["location"] = request.POST['location']
		request.session["language"] = request.POST['language']
		request.session["comments"] = request.POST['comments']

		answers
		if "counter" in request.session:
			request.session['counter'] += 1
		else:
			request.session['counter'] = 1

	return redirect('survey_form/result')

def result(request,answers):
	return render(request, "survey_form/results.html")
