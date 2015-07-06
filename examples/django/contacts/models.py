from django.db import models


class Person(models.Model):
    display_name = models.CharField(max_length=200)
    phone_number = models.CharField(max_length=200)

    def __str__(self):
        return self.display_name
