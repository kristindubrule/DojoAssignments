from __future__ import unicode_literals
from django.db import models
from ..login_reg_app.models import User

class AuthorManager(models.Manager):
	def validate_newauthor(self,postData):
		errors = {}
		author_id = None
		if len(postData['author_first']) or len(postData['author_last']):
			if len(postData['author_first']) < 2:
				errors["author_first"] = "Please enter a first name longer than 1 character"
			if len(postData['author_last']) < 2:
				errors["author_last"] = "Please enter a last name longer than 1 character"
			if len(Author.objects.filter(first_name=postData['author_first'],last_name=postData['author_last'])) > 0:
				errors["author_exists"] = "This author already exists"
			if not errors:
				author_id = Author.objects.create(first_name=postData['author_first'],last_name=postData['author_last']).id
		else:
			authormatches = Author.objects.filter(id=postData['author'])
			if len(authormatches) == 1:
				author_id = authormatches[0].id
			else:
				errors["incorrect_authors"] = "No authors or too many authors found"
		print "Author ", errors
		return {"errors": errors, "author_id": author_id}

class Author(models.Model):
	first_name = models.CharField(max_length=255)
	last_name = models.CharField(max_length=255)
	created_at = models.DateTimeField(auto_now_add=True)
	updated_at = models.DateTimeField(auto_now=True)

	objects = AuthorManager()

class BookManager(models.Manager):
	def validate_newbook(self,postData,author_id):
		errors = {}
		book_id = None
		if len(postData['book_title']) < 2:
			errors["book_title"] = "Please enter a book title longer than 1 character"
		if len(Book.objects.filter(title=postData['book_title'],author=Author.objects.get(id=author_id))) > 0:
			errors["author_exists"] = "This book already exists"
		print "Book ", errors
		if not errors:
			book = Book.objects.create(title=postData['book_title'],author=Author.objects.get(id=author_id))
			if book:
				book_id = book.id
			print book
		return {"errors": errors, "book_id": book_id}
	
	def recent(self):
		return Book.objects.raw("SELECT DISTINCT b.* from belt_reviewer_app_book b" \
			" JOIN belt_reviewer_app_review r ON b.id = r.book_id where b.id IN (" \
			" SELECT book_id from belt_reviewer_app_review" \
			" group by book_id" \
			" order by created_at desc LIMIT 3)" \
			" order by r.created_at desc")

class Book(models.Model):
	title = models.CharField(max_length=255)
	author = models.ForeignKey(Author, related_name="books")
	created_at = models.DateTimeField(auto_now_add=True)
	updated_at = models.DateTimeField(auto_now=True)

	def get_reviews(self):
		return Review.objects.filter(book=Book.objects.get(id=self.id)).order_by('-created_at')[:1]

 	objects = BookManager()

class ReviewManager(models.Manager):
	def validate_newreview(self,postData,book_id,user_id):
		errors = {}
		review = None
		if len(postData['review']) < 4:
			errors["review"] = "Please enter a review longer than 4 characters"
		if not postData['rating']:
			errors["rating"] = "Please enter a rating"
		if not errors:
			review = Review.objects.create(content=postData['review'],rating=postData['rating'],
				user=User.objects.get(id=user_id),book=Book.objects.get(id=book_id))
		print "Review ", errors
		return {"errors":errors, "review":review}

class Review(models.Model):
	content = models.TextField()
	rating = models.IntegerField()
	user = models.ForeignKey(User, related_name="reviews")
	book = models.ForeignKey(Book, related_name="reviews", default=None)
	created_at = models.DateTimeField(auto_now_add=True)
	updated_at = models.DateTimeField(auto_now=True)

	objects = ReviewManager()
