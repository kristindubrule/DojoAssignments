from django.shortcuts import render, HttpResponse, redirect
from django.http import HttpResponseRedirect
from django.contrib import messages
from .models import User, Trip, UserTrip

SESSION_KEYS = ['destination','plan','startdate','enddate']

def clear_session_keys(sessionData):
	for key in SESSION_KEYS:
		if key in sessionData:
			del sessionData[key]
	return

def set_session_keys(sessionData,postData):
	for key in SESSION_KEYS:
		if key in postData:
			sessionData[key] = postData[key]
	return

def index(request):
	if 'user_id' in request.session:
		context = {"user_trips": Trip.objects.filter(id__in=UserTrip.objects.values('trip_id').filter(user_id=request.session['user_id'])).order_by('startdate'),
					"other_trips": Trip.objects.exclude(id__in=UserTrip.objects.values('trip_id').filter(user_id=request.session['user_id'])).order_by('startdate'),
					"user": User.objects.filter(id=request.session['user_id'])[0]
		}
		return render(request, "travels_app/index.html", context)
	else:
		return redirect('/')

def addtrip(request):
	if 'user_id' in request.session:
		context = {"user": User.objects.filter(id=request.session['user_id'])
		}
		return render(request, "travels_app/addtrip.html", context)
	else:
		return redirect('/')

def create(request):
	if 'user_id' in request.session:
		if request.method == 'POST':
			print "Posted!"
			errors = Trip.objects.validate_newtrip(request.POST,request.session['user_id'])
			if len(errors):
				for tag, error in errors.iteritems():
					messages.error(request, error, extra_tags=tag)
				print "Errors!"
				set_session_keys(request.session,request.POST)
				return redirect('/travels_app/addtrip')
			else:
				clear_session_keys(request.session)
		return redirect('/travels_app')
	else:
		return redirect('/')

def join(request,trip_id):
	if 'user_id' in request.session:
		errors = UserTrip.objects.validate_jointrip(trip_id,request.session['user_id'])
		if len(errors):
			for tag, error in errors.iteritems():
				messages.error(request, error, extra_tags=tag)
		return redirect('/travels_app')
	else:
		return redirect('/')

def show(request,trip_id):
	if 'user_id' in request.session:
		context = {"user": User.objects.filter(id=request.session['user_id'])[0],
					"trip": Trip.objects.filter(id=trip_id)[0],
					"other_users": UserTrip.objects.filter(trip_id=trip_id).exclude(user__in=Trip.objects.values('owner_id').filter(id=trip_id))
				}
		return render(request, "travels_app/showtrip.html", context)
	else:
		return redirect('/')
