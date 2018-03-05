from __future__ import unicode_literals
from django.db import models
from ..login_reg_app.models import User

# Create your models here.
class Role(models.Model):
	name = models.CharField(max_length=255)
	role_users = models.ManyToManyField(User, related_name="user_roles")

class Message(models.Model):
	content = models.TextField()
	user = models.ForeignKey(User, related_name="messages",default=None)
	created_at = models.DateTimeField(auto_now_add=True)
	updated_at = models.DateTimeField(auto_now=True)

class Comment(models.Model):
	content = models.TextField()
	user = models.ForeignKey(User, related_name="comments",default=None)
	message = models.ForeignKey(Message, related_name="comments",default=None)
	created_at = models.DateTimeField(auto_now_add=True)
	updated_at = models.DateTimeField(auto_now=True)
