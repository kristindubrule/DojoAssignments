from django.shortcuts import render, HttpResponse, redirect
from django.utils.crypto import get_random_string

# Create your views here.
def index(request, random_word=""):
	context = {
		"word": get_random_string(14),
	}
	if 'attempt_count' in request.session:
		request.session['attempt_count'] += 1
	else:
		request.session['attempt_count'] = 1
	return render(request, "index.html", context)

def generate(request):
	return redirect('/random_word')

def reset(request):
	del request.session['attempt_count']
	return redirect('/random_word')