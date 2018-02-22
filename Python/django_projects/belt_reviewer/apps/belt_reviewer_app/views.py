from django.shortcuts import render, HttpResponse, redirect
from django.http import HttpResponseRedirect
from .models import Author, Book, Review
from django.contrib import messages
from ..login_reg_app.models import User

def index(request):
	if 'user_id' in request.session:
		return render(request, "belt_reviewer_app/index.html", {"user":User.objects.get(id=request.session['user_id']),
			"books":Book.objects.values('id','title').filter(reviews__id__isnull=False).distinct(),
			"recent_books":Book.objects.recent()})
	else:
		return redirect('/')

def show(request,book_id):
	if 'user_id' in request.session:
		return render(request, "belt_reviewer_app/book.html", {"user":User.objects.get(id=request.session['user_id']),
		"book":Book.objects.get(id=book_id),
		"reviews":Book.objects.get(id=book_id).reviews.all()})
	else:
		return redirect('/')

def user(request,user_id):
	if 'user_id' in request.session:
		return render(request, "belt_reviewer_app/user.html", {"user":User.objects.get(id=user_id),
			"books":Book.objects.values('id','title').filter(reviews__user_id=user_id).distinct(),
			"reviews":Review.objects.filter(user_id=user_id)})
	else:
		return redirect('/')

def destroy(request,review_id):
	if 'user_id' in request.session:	
		delreview = Review.objects.filter(user_id=request.session['user_id'],id=review_id)
		if delreview.count() == 1:
			delreview[0].delete()
		return HttpResponseRedirect(request.META.get('HTTP_REFERER', '/'))
	else:
		return redirect('/')

def addbook(request):
	if 'user_id' in request.session:
		return render(request, "belt_reviewer_app/addbook.html", {"authors":Author.objects.all()})
	else:
		return redirect('/')

def processreview(request):
	if 'user_id' in request.session:	
		if request.method == 'POST':
			review_validate = Review.objects.validate_newreview(request.POST,request.POST['book_id'],request.session['user_id'])
			if len(review_validate['errors']):
				for tag, error in review_errors.iteritems():
					messages.error(request, error, extra_tags=tag)
				request.session['review'] = request.POST['review']
				request.session['rating'] = request.POST['rating']
				return render(request, "belt_reviewer_app/book.html", {"book":Author.objects.all()})
			return redirect('/books/'+request.POST['book_id'])
		return redirect('/books')
	else:
		return redirect('/')

def processbook(request):
	if 'user_id' in request.session:
		if request.method == 'POST':
			book_validate = None
			review_validate = None

			author_validate = Author.objects.validate_newauthor(request.POST)
			if author_validate['author_id']:
				print "Validating new book"
				book_validate = Book.objects.validate_newbook(request.POST,author_validate['author_id'])
			if author_validate['author_id'] and book_validate['book_id']:
				review_validate = Review.objects.validate_newreview(request.POST,book_validate['book_id'],request.session['user_id'])

			if len(author_validate['errors']):
				for tag, error in author_validate['errors'].iteritems():
					messages.error(request, error, extra_tags=tag)
			if book_validate and len(book_validate['errors']):
				for tag, error in book_validate['errors'].iteritems():
					messages.error(request, error, extra_tags=tag)
			if review_validate and len(review_validate['errors']):
				for tag, error in review_validate['errors'].iteritems():
					messages.error(request, error, extra_tags=tag)

			if review_validate and review_validate['review']:
				if 'author_first' in request.session:
					del request.session['author_first']
					del request.session['author_last']
					del request.session['book_title']
					del request.session['review']
					del request.session['rating']
				return redirect('/books')
			else:
				request.session['author_first'] = request.POST['author_first']
				request.session['author_last'] = request.POST['author_last']
				request.session['book_title'] = request.POST['book_title']
				request.session['review'] = request.POST['review']
				request.session['rating'] = request.POST['rating']
		return render(request, "belt_reviewer_app/addbook.html", {"authors":Author.objects.all()})
	else:
		return redirect('/')