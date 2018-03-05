from django.conf.urls import url
from . import views 
urlpatterns = [
	url(r'^$', views.index),
	url(r'^newuser$', views.newuser),
	url(r'^showuser/(\d+)', views.showuser),
	url(r'^profile', views.profile),
	url(r'^wall', views.wall),
	url(r'^profile/(\d+)', views.profile)
]
