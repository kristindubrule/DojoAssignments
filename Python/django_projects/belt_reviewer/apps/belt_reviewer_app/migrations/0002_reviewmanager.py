# -*- coding: utf-8 -*-
# Generated by Django 1.10 on 2018-02-22 01:47
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('belt_reviewer_app', '0001_initial'),
    ]

    operations = [
        migrations.CreateModel(
            name='ReviewManager',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
            ],
        ),
    ]
