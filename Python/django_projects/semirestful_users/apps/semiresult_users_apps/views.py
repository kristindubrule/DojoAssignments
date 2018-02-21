from django.shortcuts import render, redirect
from django.db.models import Count
import os, binascii, md5
from django.contrib.messages import constants as messages
from django.contrib import messages
from .models import User

def index(request):
	return render(request, "semiresult_users_apps/index.html", {"users": User.objects.all()})

def new(request):
	return render(request, 'semiresult_users_apps/new.html')

def create(request):
	errors = User.objects.insert_validator(request.POST)
	if len(errors):
		for tag, error in errors.iteritems():
			messages.error(request, error, extra_tags=tag)
		context = { "first_name": request.POST['first_name'],
					"last_name": request.POST['last_name'],
					"email": request.POST['email']
					}
		return render(request, "semiresult_users_apps/new.html", context)
	else:
		salt = binascii.b2a_hex(os.urandom(15))
		hashed_pw = md5.new(request.POST['password'] + salt).hexdigest()
		User.objects.create(first_name=request.POST['first_name'],
							last_name=request.POST['last_name'],
							email=request.POST['email'],
							password=hashed_pw,
							salt=salt)
		return redirect('/', user_id=User.objects.latest('id').id)

def show(request,user_id):
	return render(request,"semiresult_users_apps/show.html",{"user":User.objects.get(id=user_id)})

def destroy(request,user_id):
	User.objects.get(id=user_id).delete()
	return redirect("/")

def edit(request,user_id):
	if "first_name" in request.session:
		user = { "id": user_id,
					"first_name": request.session["first_name"],
					"last_name": request.session["last_name"],
					"email": request.session["email"]
				}
		del request.session["first_name"]
		del request.session["last_name"]
		del request.session["email"]	
		return render(request,"semiresult_users_apps/edit.html", {"user":user})			
	else:
		user = User.objects.filter(id=user_id)
		if len(user) == 1:
			return render(request,"semiresult_users_apps/edit.html", {"user":user.first()})			
		else:
			messages.error(request,"No user or too many users found")
			return redirect('/')					

def update(request,user_id):
	errors = User.objects.update_validator(request.POST,user_id)
	if len(errors):
		for tag, error in errors.iteritems():
			messages.error(request, error, extra_tags=tag)
		request.session["first_name"] = request.POST['first_name']
		request.session["last_name"] = request.POST['last_name']
		request.session["email"] = request.POST['email']
		return redirect('/'+user_id+'/edit')
	else:
		user = User.objects.get(id=user_id)
		user.first_name = request.POST['first_name']
		user.last_name = request.POST['last_name']
		user.email = request.POST['email']
		user.password = request.POST['password']
		user.save()
		return redirect('/', user_id=user_id)
