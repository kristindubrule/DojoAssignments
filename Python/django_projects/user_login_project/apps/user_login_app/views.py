from django.shortcuts import render, HttpResponse, redirect

def index(request):
	return render(request, "user_login_app/index.html")

def new(request):
	response = "Hello, I am a form to create something new!"
	return HttpResponse(response)
