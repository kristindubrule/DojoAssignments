from django.conf.urls import url
from . import views 
urlpatterns = [
	url(r'^$', views.index),
	url(r'^addtrip$', views.addtrip),
	url(r'^create$', views.create),
	url(r'^join/(\d+)', views.join),
	url(r'^(\d+)', views.show)
]
