from django.conf.urls import url
from . import views 
urlpatterns = [
	url(r'^$', views.index),
	url(r'new_word$', views.new_word),
	url(r'reset$', views.reset)
]