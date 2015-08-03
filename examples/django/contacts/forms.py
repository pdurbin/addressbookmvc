from .models import Person
from django.forms import ModelForm

class PersonForm(ModelForm):
    class Meta:
        model = Person
        fields = ('display_name', 'phone_number')
