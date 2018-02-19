from django.conf.urls import url
from . import views 
urlpatterns = [
	url(r'^$', views.index),
	url(r'new$', views.new),
	url(r'success/url', views.success),
	url(r'upload', views.upload_file)
]
