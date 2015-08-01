from django.test import TestCase
from .models import Person

"""
This test is just a stub to get started testing in Django at all per https://docs.djangoproject.com/en/1.8/intro/tutorial05/
"""


class PersonMethodTests(TestCase):

    def test_name_required(self):
        """
        display_name should be a required field. Can I require "display_name" in a constructor? Maybe not according to http://stackoverflow.com/questions/6465686/how-to-implement-a-required-property-in-python . Asked at http://irclog.greptilian.com/sourcefu/2015-08-01#i_130969 . No doubt the database field could have a non-null constraint but I'm wondering about the object being instantiated.
        """
        inputName1 = "name1"
        person1 = Person(display_name=inputName1)
        self.assertEqual(person1.display_name, inputName1)

        # person2 = Person()
        # print(person2.__dict__)
        # self.assertTrue(person2.display_name != '')
