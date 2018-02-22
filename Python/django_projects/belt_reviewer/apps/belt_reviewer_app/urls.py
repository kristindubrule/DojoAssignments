from django.conf.urls import url
from . import views 
urlpatterns = [
	url(r'^$', views.index),
	url(r'^addbook$', views.addbook),
	url(r'^processbook$', views.processbook),
	url(r'^(\d+)$', views.show),
	url(r'^processreview$', views.processreview),
	url(r'^user/(\d+)$', views.user),
	url(r'^destroy/(\d+)$', views.destroy)
]
