from django.shortcuts import render, HttpResponse, redirect
from .models import Author, Book

def index(request):
	my_author1 = Author.objects.create(name="Stephen King")
	my_author2 = Author.objects.create(name="Louisa May Alcott")
	my_book1 = Book.objects.create(title="Little Women", author=my_author2)
	my_book2 = Book.objects.create(title="It", author=my_author1)

	context = {"authors": Author.objects.all()}
	return render(request, "books_app/index.html", context)

def new(request):
	response = "Hello, I am a form to create something new!"
	return HttpResponse(response)
