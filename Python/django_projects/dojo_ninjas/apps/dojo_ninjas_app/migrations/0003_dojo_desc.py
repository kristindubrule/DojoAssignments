# -*- coding: utf-8 -*-
# Generated by Django 1.10 on 2018-02-19 19:53
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('dojo_ninjas_app', '0002_auto_20180219_1938'),
    ]

    operations = [
        migrations.AddField(
            model_name='dojo',
            name='desc',
            field=models.TextField(null=True),
        ),
    ]