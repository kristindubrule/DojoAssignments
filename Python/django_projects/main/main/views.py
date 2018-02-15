from django.shortcuts import render, HttpResponse, redirect

# the index function is called when root is visited
def index(request):
	response = "placeholder placeholder"
	return HttpResponse(response)
