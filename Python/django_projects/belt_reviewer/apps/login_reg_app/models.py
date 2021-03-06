from __future__ import unicode_literals
from django.db import models
import re
import bcrypt

EMAIL_REGEX = re.compile(r'^[a-zA-Z0-9.+_-]+@[a-zA-Z0-9._-]+\.[a-zA-Z]+$')
NAME_REGEX = re.compile(r'^[a-zA-Z]{2,}$')
PASSWORD_REGEX = re.compile(r'^\w{8,}$')

class UserManager(models.Manager):
	def validate_newuser(self,postData):
		errors = {}
		if not NAME_REGEX.match(postData['first_name']):
			errors["first_name"] = "First name must be at least 2 characters."
		if not NAME_REGEX.match(postData['last_name']):
			errors["last_name"] = "Last name must be at least 2 characters."
		if not EMAIL_REGEX.match(postData['email']):
			errors["email"] = "Email is invalid"
		if postData['password'] != postData['password_confirm']:
			errors["password_confirm"] = "Password values do not match each other"
		if not PASSWORD_REGEX.match(postData['password']):
			errors["password"] = "Password must be at least 8 characters"

		email_check = User.objects.filter(email=postData['email'])
		if len(email_check) != 0:
			errors["email_count"] = "Email address is already in use"

		return errors

class User(models.Model):
	first_name = models.CharField(max_length=255)
	last_name = models.CharField(max_length=255)
	email = models.EmailField()
	password = models.CharField(max_length=100)
	created_at = models.DateTimeField(auto_now_add=True)
	updated_at = models.DateTimeField(auto_now=True)

	objects = UserManager()

