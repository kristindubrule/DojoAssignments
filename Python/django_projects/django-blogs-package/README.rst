=====
Blogs
=====

Quick start
-----------

1. Add "blogs" to your INSTALLED_APPS setting like this::

    INSTALLED_APPS = [
        ...
        'polls',
    ]

2. Include the polls URLconf in your project urls.py like this::

    path('polls/', include('polls.urls')),

3. Run `python manage.py migrate` to create the polls models.

4. Start the development server and visit http://127.0.0.1:8000/blogs/create
   to create a blog.

5. Visit http://127.0.0.1:8000/blogs/ to participate in the poll.
