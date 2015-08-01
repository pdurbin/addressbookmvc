from django.contrib import admin
from .models import Person


class PersonAdmin(admin.ModelAdmin):
    fields = ['display_name', 'phone_number']
    list_display = ('display_name', 'phone_number')

admin.site.register(Person, PersonAdmin)
