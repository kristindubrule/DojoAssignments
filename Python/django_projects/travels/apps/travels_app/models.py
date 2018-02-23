from __future__ import unicode_literals
from django.db import models
from datetime import datetime, date
from ..login_reg_app.models import User

# Create your models here.
class TripManager(models.Manager):
	required_fields = {'destination': 'destination',
						'plan': 'description',
						'startdate': 'trip start date',
						'enddate': 'trip end date',
						'plan': 'plan'
						}
	trip_dates = ['startdate','enddate']

	def validate_newtrip(self,postData,user_id):
		errors = {}
		users = User.objects.filter(id=user_id)
		if users.count() != 1:
			errors["user"] = "No user or too many users found."
		else:
			user = users[0]

		for field in TripManager.required_fields.keys():
			if not len(postData[field]):
				errors[field] = "Please enter a {}.".format(field)

		for trip_date in TripManager.trip_dates:
			if len(postData[trip_date]) and datetime.strptime(postData[trip_date],'%Y-%m-%d').date() < date.today():
				errors[trip_date+"_future"] = "Your {} should be later than today.".format(TripManager.required_fields[trip_date])
		if len(postData['startdate']) and len(postData['enddate']) and datetime.strptime(postData['startdate'],'%Y-%m-%d').date() > datetime.strptime(postData['enddate'],'%Y-%m-%d').date():
			errors['date_backwards'] = "Your start date must not be after your end date."

		if not errors:
			trip = Trip.objects.create(
				destination=postData['destination'],
				startdate=postData['startdate'],
				enddate=postData['enddate'],
				plan=postData['plan'],
				owner=user
			)
			usertrip = UserTrip.objects.create(
				user=user,
				trip=trip
			)
		return errors

class Trip(models.Model):
	destination = models.CharField(max_length=255)
	startdate = models.DateField()
	enddate = models.DateField()
	plan = models.CharField(max_length=255)
	owner = models.ForeignKey(User, related_name="trips")
	created_at = models.DateTimeField(auto_now_add=True)
	updated_at = models.DateTimeField(auto_now=True)

	objects = TripManager()

class UserTripManager(models.Manager):
	def validate_jointrip(self,trip_id,user_id):
		errors = {}
		if User.objects.filter(id=user_id).count() != 1:
			errors["user"] = "No user or too many users found."
		if Trip.objects.filter(id=trip_id).count() != 1:
			errors["trip"] = "No trip or too many trips found."
		if not len(errors):
			UserTrip.objects.create(trip=Trip.objects.get(id=trip_id),user=User.objects.get(id=user_id))
		return errors

class UserTrip(models.Model):
	user = models.ForeignKey(User)
	trip = models.ForeignKey(Trip)
	created_at = models.DateTimeField(auto_now_add=True)
	updated_at = models.DateTimeField(auto_now=True)

	objects = UserTripManager()