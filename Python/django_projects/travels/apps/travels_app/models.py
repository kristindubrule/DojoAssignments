from __future__ import unicode_literals
from django.db import models
from datetime import datetime, date
from ..login_reg_app.models import User

# Create your models here.
class TripManager(models.Manager):

	def clean_date(self,date_str):
		if len(date_str):
			return datetime.strptime(date_str,'%Y-%m-%d').date()
		else:
			return None

	def validate_newtrip(self,postData,user_id):
		required_fields = {'destination': 'destination',
							'plan': 'description',
							'startdate': 'trip start date',
							'enddate': 'trip end date',
							'plan': 'plan'
							}
		errors = {}
		users = User.objects.filter(id=user_id)
		if users.count() != 1:
			errors["user"] = "No user or too many users found."
		else:
			user = users[0]

		# Check to make sure all required fields are filled in
		for field in required_fields.keys():
			if not len(postData[field]):
				errors[field] = "Please enter a {}.".format(field)

		# Max length of destination and plan is 255, so check the lengths
		if len(postData['destination']) > 255:
			errors['destination_length'] = "The maximum length for destination is 255."
		if len(postData['plan']) > 255:
			errors['description_length'] = "The maximum length for description is 255."

		# Check that dates are in the future
		startdate = self.clean_date(postData['startdate'])
		if startdate and startdate <= date.today():
			errors['startdate_future'] = "Your start date should be later than today."
		enddate = self.clean_date(postData['enddate'])
		if enddate and enddate <= date.today():
			errors['enddate_future'] = "Your end date should be later than today."

		# Check that start date is less than or equal to end date
		if startdate and enddate and startdate > enddate: 
			errors['date_backwards'] = "Your end date must be after your start date."

		# If there are no errors, create the trip, and enter a record for this user-trip pair
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
			# Get is safe here, because we check for existence above.
			UserTrip.objects.create(trip=Trip.objects.get(id=trip_id),user=User.objects.get(id=user_id))
		return errors

class UserTrip(models.Model):
	user = models.ForeignKey(User)
	trip = models.ForeignKey(Trip)
	created_at = models.DateTimeField(auto_now_add=True)
	updated_at = models.DateTimeField(auto_now=True)

	objects = UserTripManager()