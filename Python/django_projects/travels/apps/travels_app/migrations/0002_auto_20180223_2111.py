# -*- coding: utf-8 -*-
# Generated by Django 1.10 on 2018-02-23 21:11
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('travels_app', '0001_initial'),
    ]

    operations = [
        migrations.AlterField(
            model_name='trip',
            name='plan',
            field=models.CharField(max_length=255),
        ),
    ]
