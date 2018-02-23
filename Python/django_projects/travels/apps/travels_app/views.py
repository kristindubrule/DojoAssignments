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
		GOTO = '/'
		if request.method == 'POST':
			# Validate trip info; create trip if successful
			errors = Trip.objects.validate_newtrip(request.POST,request.session['user_id'])

			# Process errors, if any
			if len(errors):
				for tag, error in errors.iteritems():
					messages.error(request, error, extra_tags=tag)

				# Save user entered values, if there were errors
				set_session_keys(request.session,request.POST)

				# Return to the add trip page to show errors
				GOTO = '/travels_app/addtrip'
			else:
				# if we were successful, clear any saved values and return to the trip list page
				clear_session_keys(request.session)
				GOTO = '/travels_app'
		return redirect(GOTO)
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
		trips = Trip.objects.filter(id=trip_id)
		if trips.count() == 1:
			trip = trips[0]
		else:
			trip = None
		context = {"user": User.objects.filter(id=request.session['user_id'])[0],
					"trip": trip,
					"other_users": UserTrip.objects.filter(trip_id=trip_id).exclude(user__in=Trip.objects.values('owner_id').filter(id=trip_id))
		}
		return render(request, "travels_app/showtrip.html", context)
	else:
		return redirect('/')
