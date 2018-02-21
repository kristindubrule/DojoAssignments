from __future__ import unicode_literals
from django.db import models

class DescriptionManager(models.Manager):
	def basic_validator(self,postData):
		errors = {}
		if len(postData['desc']) < 16:
			errors["desc"] = "Course description must be more than 15 characters"
		return errors

class Description(models.Model):
	content = models.TextField()
	created_at = models.DateTimeField(auto_now_add=True)
	updated_at = models.DateTimeField(auto_now=True)

	objects = DescriptionManager()

class CourseManager(models.Manager):
	def basic_validator(self,postData):
		errors = {}
		if len(postData['name']) < 6: 
			errors["course_name"] = "Course name must be more than 5 characters"
		return errors

class Course(models.Model):
	name = models.CharField(max_length=255)
	description = models.ForeignKey(Description, related_name="course", on_delete=models.CASCADE, default=None)
	created_at = models.DateTimeField(auto_now_add=True)
	updated_at = models.DateTimeField(auto_now=True)

	objects = CourseManager()

