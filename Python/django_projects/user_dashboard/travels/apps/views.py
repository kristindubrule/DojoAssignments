from django.shortcuts import render, HttpResponse, redirect
from django.http import HttpResponseRedirect
from django.contrib import messages

def index(request):
	return render(request, "<app_str>/index.html")

def new(request):
	response = "Hello, I am a form to create something new!"
	return HttpResponse(response)
