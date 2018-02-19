#!/bin/bash

TEMPLATE_PATH=/Users/kristinf/Desktop/DojoAssignments/django_project_templates

echo $1 $2

django-admin startproject $1
cd $1
mkdir apps
cd apps
touch __init__.py
python2 ../manage.py startapp $2
pwd


cd ../$1 # Out of apps, into main project folder
pwd
rm urls.py # Replace project-level urls file
cp $TEMPLATE_PATH/project_urls.py urls.py

cd ../apps/$2
cp $TEMPLATE_PATH/app_urls.py urls.py
cp $TEMPLATE_PATH/views.py .

mkdir templates
mkdir templates/$2
cp $TEMPLATE_PATH/index.html templates/$2

mkdir static
mkdir static/$2
mkdir static/$2/css
mkdir static/$2/js
cp $TEMPLATE_PATH/css/main.css static/$2/css

echo To Do
echo -- Add app apps.$2 to settings.py file in parent directory
echo -- Add app $2 to the urls.py file in the project directory
echo -- Add app $2 to the views.py file in the $2 directory
echo -- Add app $2 to the index.html file in the $2 directory
echo -- cd $1
echo -- python2 manage.py makemigrations
echo -- python2 manage.py migrate
echo -- python2 manage.py runserver
