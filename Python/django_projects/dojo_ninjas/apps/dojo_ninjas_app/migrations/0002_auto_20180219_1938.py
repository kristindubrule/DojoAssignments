# -*- coding: utf-8 -*-
# Generated by Django 1.10 on 2018-02-19 19:38
from __future__ import unicode_literals

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('dojo_ninjas_app', '0001_initial'),
    ]

    operations = [
        migrations.RenameField(
            model_name='ninja',
            old_name='dojo_id',
            new_name='dojo',
        ),
    ]
