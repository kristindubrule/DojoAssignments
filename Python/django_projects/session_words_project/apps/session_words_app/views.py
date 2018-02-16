from django.shortcuts import render

# Create your views here.
from django.shortcuts import render, HttpResponse, redirect
# the index function is called when root is visited
def index(request):
	return render(request, "session_words_app/index.html")

def new_word(request):
	if request.method == "POST":
		if len(request.POST['word']) > 0:
			word_object = {}
			word_object['word'] = request.POST['word']
			if 'color' in request.POST:
				word_object['color'] = request.POST['color']
			if 'big_font' in request.POST:
				word_object['size'] = "bigword"
			else:
				word_object['size'] = "smallword"
			if "words" not in request.session:
				request.session["words"] = []
			request.session["words"].append(word_object)
			request.session.modified = True
			print "Request ", request.session["words"]
	return redirect('/session_words_app')

def reset(request):
	del request.session['words']
	return redirect('/session_words_app')
