from django.shortcuts import render, HttpResponse, redirect

# the index function is called when root is visited
def index(request):
	context = {
		"email" : "blog@gmail.com",
		"name" : "mike"
	}
	return render(request, "blogs/index.html", context)


def new(request):
	response = "placeholder to display a new form to create a new blog"
	return HttpResponse(response)

def create(request):
	if request.method == "POST":
		print "*"*50
		print request.POST
		print request.POST['name']
		print request.POST['desc']
		request.session['name'] = "test"  # more on session below
		print "*"*50
		return redirect("/blogs")
	else:
		return redirect("/blogs")

def show(request,blog_id):
	response = "placeholder to display blog {}".format(blog_id)
	return HttpResponse(response)

def edit(request,blog_id):
	response = "placeholder to edit blog {}".format(blog_id)
	return HttpResponse(response)

def destroy(request,blog_id):
	return redirect ('/blogs')