from django.shortcuts import render, HttpResponse, redirect
from django.contrib import messages
import bcrypt
from .models import User

def index(request):
	print "login request"
	return render(request, 'login_reg_app/login.html')

def register(request):
	if request.method == "POST":
		errors = User.objects.validate_newuser(request.POST)
		# Process errors
		if len(errors):
			for tag, error in errors.iteritems():
				messages.error(request, error, extra_tags=tag)
			request.session["first_name"] = request.POST['first_name']
			request.session['last_name'] = request.POST['last_name']
			request.session['email'] = request.POST['email']
			return redirect('/')
		# Otherwise, create user
		else: 
			hashed_pw = bcrypt.hashpw(request.POST['password'].encode(), bcrypt.gensalt())
			User.objects.create(first_name=request.POST['first_name'],
							last_name=request.POST['last_name'],
							email=request.POST['email'],
							password=hashed_pw)
			if 'first_name' in request.session:
				del request.session["first_name"]
				del request.session["last_name"]
				del request.session["email"]
			request.session["user_id"] = User.objects.latest('id').id
			return redirect('/books')
	else:
		return redirect('/')

def login(request):
	if request.method == "POST":
		retuser = User.objects.filter(email=request.POST['email'])
		if len(retuser) == 1:
			if bcrypt.checkpw(request.POST['password'].encode(), retuser[0].password.encode()):
				request.session['user_id'] = retuser[0].id
				return redirect('/books')
			else:
				messages.error(request, "Invalid credentials")			
		else:
			messages.error(request, "Invalid credentials")
	return redirect('/')

def loggedin(request):
	if "user_id" in request.session:
		return render(request, 'login_reg_app/logged_in.html')	
	else:
		return redirect('/')

def logout(request):
	del request.session["user_id"]
	return redirect('/')