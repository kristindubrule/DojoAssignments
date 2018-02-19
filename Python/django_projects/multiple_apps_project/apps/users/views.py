from django.shortcuts import render, HttpResponse, redirect
# the index function is called when root is visited
def index(request):
	response = "Hello, I am your users!"
	return HttpResponse(response)

def register(request):
	response = "placeholder for users to create a new user record"
	return HttpResponse(response)

def login(request):
	response = "placeholder for users to login"
	return HttpResponse(response)

def users(request):
	response = "placeholder to display list of users"
	return HttpResponse(response)