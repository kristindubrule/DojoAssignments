from django.shortcuts import render, HttpResponse, redirect
from django.http import HttpResponseRedirect
from .models import Author, Book, Review
from django.contrib import messages
from ..login_reg_app.models import User

SESSION_KEYS = ['author_first','author_last','book_title','review','rating','author_id']

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
		clear_session_keys(request.session)
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
		clear_session_keys(request.session)
		return render(request, "belt_reviewer_app/user.html", {"showuser":User.objects.get(id=user_id),
			"books":Book.objects.values('id','title').filter(reviews__user_id=user_id).distinct(),
			"reviews":Review.objects.filter(user_id=user_id),
			"user":User.objects.get(id=request.session['user_id'])})
	else:
		return redirect('/')

def destroy(request,review_id):
	if 'user_id' in request.session:	
		clear_session_keys(request.session)
		delreview = Review.objects.filter(user_id=request.session['user_id'],id=review_id)
		if delreview.count() == 1:
			delreview[0].delete()
		return HttpResponseRedirect(request.META.get('HTTP_REFERER', '/books'))
	else:
		return redirect('/')

def addbook(request):
	if 'user_id' in request.session:
		return render(request, "belt_reviewer_app/addbook.html", {"user":User.objects.get(id=request.session['user_id']),"authors":Author.objects.all()})
	else:
		return redirect('/')

def processreview(request):
	if 'user_id' in request.session:
		if request.method == 'POST':
			review_validate = Review.objects.validate_newreview(request.POST,request.POST['book_id'],request.session['user_id'])
			if len(review_validate['errors']):
				for tag, error in review_validate['errors'].iteritems():
					messages.error(request, error, extra_tags=tag)
				set_session_keys(request.session,request.POST)
				print "session", request.session['review']
			return redirect('/books/'+request.POST['book_id'])
		return redirect('/books')
	else:
		return redirect('/')

def processbook(request):
	if 'user_id' in request.session:
		if request.method == 'POST':
			book_validate = None
			review_validate = None

			author_validate = Author.objects.validate_author(request.POST)
			if author_validate['author_id']:
				book_validate = Book.objects.validate_newbook(request.POST,author_validate['author_id'])
				request.session['author_id'] = author_validate['author_id']

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
				clear_session_keys(request.session)
				return redirect('/books')
			else:
				set_session_keys(request.session,request.POST)
		return redirect('/books/addbook')
	else:
		return redirect('/')