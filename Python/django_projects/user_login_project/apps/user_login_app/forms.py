# myapp/forms.py
from django import forms
from django.forms import ModelForm
from django.core.validators import MinLengthValidator
from .models import User

class UserForm(forms.ModelForm):
	def __init__(self, *args, **kwargs):
		super(UserForm, self).__init__(*args, **kwargs)
		self.fields["email_address"].min_length = 50
		self.fields["email_address"].validators.append(MinLengthValidator)

	class Meta:
		model = User
		fields = ['first_name','last_name','email_address','age']
