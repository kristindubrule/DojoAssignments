# -*- coding: utf-8 -*-
# Generated by Django 1.10 on 2018-02-21 22:58
from __future__ import unicode_literals

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('courses_app', '0004_auto_20180221_2248'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='description',
            name='course',
        ),
        migrations.AddField(
            model_name='course',
            name='description',
            field=models.ForeignKey(default=None, on_delete=django.db.models.deletion.CASCADE, related_name='course', to='courses_app.Description'),
        ),
    ]
