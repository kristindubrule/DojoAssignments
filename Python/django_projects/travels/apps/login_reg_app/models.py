from __future__ import unicode_literals
from django.db import models
import re
import bcrypt

PASSWORD_REGEX = re.compile(r'^\S{8,}$')
NAME_REGEX = re.compile(r'^\S{3,}$')

class UserManager(models.Manager):
	def validate_newuser(self,postData):
		errors = {}
		if not NAME_REGEX.match(postData['username']):
			errors['username'] = "Please enter a username of at least 3 characters"
		if not NAME_REGEX.match(postData['first_name']):
			errors['first_name'] = "Please enter a name of at least 3 characters"
		if postData['password'] != postData['password_confirm']:
			errors["password_confirm"] = "Password values do not match each other"
		if not PASSWORD_REGEX.match(postData['password']):
			errors["password"] = "Password must be at least 8 characters"

		username_check = User.objects.filter(email=postData['username'])
		if username_check.count() > 0:
			errors["username"] = "Email address is already in use"

		return errors

class User(models.Model):
	first_name = models.CharField(max_length=255)
	last_name = models.CharField(max_length=255)
	email = models.EmailField()
	password = models.CharField(max_length=100)
	created_at = models.DateTimeField(auto_now_add=True)
	updated_at = models.DateTimeField(auto_now=True)

	objects = UserManager()

