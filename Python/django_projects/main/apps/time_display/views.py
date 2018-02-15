from django.shortcuts import render, HttpResponse, redirect
from datetime import datetime

# Create your views here.
def index(request):
	context = {
		"current_time": datetime.now()	
	}
	return render(request, "time_display/index.html", context)