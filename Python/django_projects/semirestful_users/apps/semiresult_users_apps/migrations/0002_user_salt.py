# -*- coding: utf-8 -*-
# Generated by Django 1.10 on 2018-02-21 19:04
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('semiresult_users_apps', '0001_initial'),
    ]

    operations = [
        migrations.AddField(
            model_name='user',
            name='salt',
            field=models.CharField(default='00000000', max_length=100),
        ),
    ]
