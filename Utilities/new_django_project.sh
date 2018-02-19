#!/bin/bash

UTILITY_PATH=/Users/kristinf/Desktop/DojoAssignments/Utilities
TEMPLATE_PATH=$UTILITY_PATH/django_project_templates

echo $1 $2

django-admin startproject $1
cd $1/$1 # project/project
rm urls.py # Replace project-level urls file
cp $TEMPLATE_PATH/project_urls.py urls.py

cd ..
mkdir apps
cd apps
touch __init__.py

echo To Do

$UTILITY_PATH/new_django_app.sh $2

echo -- cd $1
echo -- python2 manage.py makemigrations
echo -- python2 manage.py migrate
echo -- python2 manage.py runserver
