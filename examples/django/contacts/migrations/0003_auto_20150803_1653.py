# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations
import datetime
from django.utils.timezone import utc


class Migration(migrations.Migration):

    dependencies = [
        ('contacts', '0002_auto_20150803_1651'),
    ]

    operations = [
        migrations.AddField(
            model_name='person',
            name='created',
            field=models.DateTimeField(default=datetime.datetime(2015, 8, 3, 16, 53, 29, 942164, tzinfo=utc), auto_now_add=True),
            preserve_default=False,
        ),
        migrations.AddField(
            model_name='person',
            name='edited',
            field=models.DateTimeField(default=datetime.datetime(2015, 8, 3, 16, 53, 34, 325932, tzinfo=utc), auto_now=True),
            preserve_default=False,
        ),
    ]
