from django.shortcuts import render, HttpResponse, redirect
from django.http import HttpResponseRedirect
from .forms import UploadFileForm

# Imaginary function to handle an uploaded file.
from .file_handling import handle_uploaded_file

def index(request):
	form = UploadFileForm()
	return render(request, 'fileupload_app/index.html', {'form': form})

def new(request):
	response = "Hello, I am a form to create something new!"
	return HttpResponse(response)

def upload_file(request):
	if request.method == 'POST':
		form = UploadFileForm(request.POST, request.FILES)
		if form.is_valid():
			print "Form is valid"
			handle_uploaded_file(request.FILES['file'])
			return HttpResponseRedirect('/success/url/')
	return redirect('/')

def success(request):
	return render(request, "fileupload_app/upload.html")
