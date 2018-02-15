from flask import Flask, request, redirect, render_template, session, flash, url_for
from mysqlconnection import MySQLConnector
from datetime import datetime, date, timedelta
import re
import os, binascii, md5

EMAIL_REGEX = re.compile(r'^[a-zA-Z0-9.+_-]+@[a-zA-Z0-9._-]+\.[a-zA-Z]+$')
NAME_REGEX = re.compile(r'^[a-zA-Z]{2,}$')
PASSWORD_REGEX = re.compile(r'^(?=.*[A-Z])(?=.*\d)[A-Za-z\d\S]{8,}$')

app = Flask(__name__)
mysql = MySQLConnector(app,'friendsdb')

@app.route('/users')
def index():
	query = "SELECT * from friends order by id DESC"
	users = mysql.query_db(query)
	return render_template("index.html", users=users)

@app.route('/users/new')
def new():
	return render_template("new.html")

@app.route('/users/<user_id>/edit')
def edit(user_id):
	query = "SELECT * from friends where id=:id"
	data = {
		"id": user_id
	}
	user = mysql.query_db(query,data)
	return render_template("edit.html", user=user[0])

@app.route('/users/<user_id>/')
def show(user_id):
	query = "SELECT * from friends where id=:id"
	data = {
		"id": user_id
	}
	user = mysql.query_db(query,data)
	return render_template("show.html",user=user[0])

@app.route('/users/create', methods=["POST"])
def create():
	query = "INSERT INTO friends (first_name, last_name, birthdate, created_at, updated_at) " \
		"values (:first_name, :last_name, :birthdate, NOW(), NOW())"
	data = {
		"first_name": request.form['first_name'],
		"last_name": request.form['last_name'],
		"birthdate": request.form['birthdate']
	}
	user = mysql.query_db(query,data)		
	return redirect(url_for('show', user_id=user))

@app.route('/users/<user_id>/destroy')
def destroy(user_id):
	query = "delete from friends where id=:id"
	data = {
		"id": user_id,
	}
	user = mysql.query_db(query,data)
	return redirect("/users")

@app.route('/users/<user_id>', methods=["POST"])
def update(user_id):
	query = "UPDATE friends set first_name=:first_name, last_name=:last_name, birthdate=:birthdate, updated_at=NOW() "\
	"where id=:id"
	data = {
		"id": user_id,
		"first_name": request.form['first_name'],
		"last_name": request.form['last_name'],
		"birthdate": request.form['birthdate']
	}
	user = mysql.query_db(query,data)
	return redirect(url_for('show', user_id=user_id))

app.run(debug=True)