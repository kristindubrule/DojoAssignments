from django.shortcuts import render, HttpResponse, redirect
from django.http import HttpResponseRedirect
from django.contrib import messages
from .models import User, Role, Message, Comment

def index(request):
	users = User.objects.all().order_by('-created_at')
	context = {"users": users}
	return render(request, "dashboard/index.html", context)

def newuser(request):
	return render(request, "dashboard/newuser.html")

def showuser(request,user_id):
	return render(request, "dashboard/user.html")

def profile(request,user_id=None):
	return render(request, "dashboard/profile.html")

def wall(request):
	return render(request, "dashboard/wall.html")
