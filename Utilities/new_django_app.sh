#!/bin/bash

UTILITY_PATH=/Users/kristinf/Desktop/DojoAssignments/Utilities
TEMPLATE_PATH=$UTILITY_PATH/django_project_templates

python2 ../manage.py startapp $1 # from apps

cd $1
cp $TEMPLATE_PATH/app_urls.py urls.py
cp $TEMPLATE_PATH/views.py .

mkdir templates
mkdir templates/$1
cp $TEMPLATE_PATH/index.html templates/$1

mkdir static
mkdir static/$1
mkdir static/$1/css
mkdir static/$1/js
cp $TEMPLATE_PATH/css/main.css static/$1/css

echo -- Add app apps.$1 to settings.py file in parent directory
echo -- Add app $1 to the urls.py file in the project directory
echo -- Add app $1 to the views.py file in the $2 directory
echo -- Add app $1 to the index.html file in the $2 directory
