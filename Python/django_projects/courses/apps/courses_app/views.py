from django.shortcuts import render, HttpResponse, redirect
from django.contrib.messages import constants as messages
from django.contrib import messages
from .models import Course, Description

def index(request):
	return render(request, "courses_app/index.html", {"courses":Course.objects.all()})

def create(request):
	if request.method == 'POST':
		errors = Course.objects.basic_validator(request.POST)
		errors.update(Description.objects.basic_validator(request.POST))
		if len(errors):
			for tag, error in errors.iteritems():
				messages.error(request, error, extra_tags=tag)
			request.session['name'] = request.POST['name']
			request.session['desc'] = request.POST['desc']
		else:
			desc = Description.objects.create(content=request.POST['desc'])
			Course.objects.create(name=request.POST['name'],description=desc)
			if 'name' in request.session:
				del request.session['name']
			if 'desc' in request.session:
				del request.session['desc']
	return redirect('/')

def delete(request,course_id):
	return render(request, "courses_app/confirm.html", {"course":Course.objects.get(id=course_id)})

def destroy(request,course_id):
	if request.method == 'POST':
		if request.POST['confirm'] == 'yes':
			desc = Course.objects.get(id=course_id).description
			desc.delete()
			# Course.objects.get(id=course_id).delete()
	return redirect('/')
