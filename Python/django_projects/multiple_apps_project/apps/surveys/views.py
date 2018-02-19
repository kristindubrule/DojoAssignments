from django.shortcuts import render, HttpResponse, redirect
# the index function is called when root is visited
def index(request):
	response = "Hello, I am your surveys!"
	return HttpResponse(response)

def new(request):
	response = "Hello, I am a form to create a new survey!"
	return HttpResponse(response)
