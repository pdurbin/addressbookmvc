from django.http import HttpResponse, HttpResponseRedirect, Http404
from django.template import RequestContext, loader
from django.shortcuts import render
from django.core.urlresolvers import reverse
from django.shortcuts import render_to_response
from .forms import PersonForm
from .models import Person

ERR_FOUND = 'ERR_FOUND'

def get_common_page_vars():
    return dict(page_title='Django AddressBookMVC Example')


def view_add_success(request, contact_id):

    try:
        new_person = Person.objects.get(pk=contact_id)
    except Person.DoesNotExist:
        raise Http404('Person not found')

    return index(request, new_person)

def index(request, new_person=None):

    d = get_common_page_vars()

    if request.POST:        # Has the form been submitted?
        f = PersonForm(request.POST)
        if f.is_valid():    # Is it valid?
            person_obj = f.save()
            success_url = reverse('contacts:view_add_success',
                                  kwargs=dict(contact_id=person_obj.id))
            return HttpResponseRedirect(success_url)
        else:
            d.update({ERR_FOUND : True})
    else:
        f = PersonForm()

    # Retrieve current contacts
    #
    contacts_list = Person.objects.order_by('-display_name') #[:999999]

    # Add contacts and form to dictionary
    #
    d.update({'contacts_list' :  contacts_list,
              'person_form' : f,
              'new_person' : new_person,
              })

    return render_to_response('contacts/index.html',
                            d,
                            context_instance=RequestContext(request))


def add(request):
    name = request.POST['display_name']
    if (name):
        person = Person(display_name=name)
        person.save()
    return HttpResponseRedirect('/contacts')


def detail(request, contact_id):
    try:
        person = Person.objects.get(pk=contact_id)
    except Person.DoesNotExist:
        raise Http404("Person does not exist")
    return render(request, 'contacts/detail.html', {'person': person})


def view_edit_success(request, contact_id):

    try:
        new_person = Person.objects.get(pk=contact_id)
    except Person.DoesNotExist:
        raise Http404('Person not found')

    return edit_contact(request, contact_id, just_edited=True)

def edit_contact(request, contact_id, just_edited=False):

    try:
        existing_person = Person.objects.get(pk=contact_id)
    except Person.DoesNotExist:
        raise Http404('Person not found')

    d = dict(page_title='Edit %s' % existing_person.display_name)


    if request.POST:        # Has the form been submitted?
        f = PersonForm(request.POST, instance=existing_person)
        if f.is_valid():    # Is it valid?
            person_obj = f.save()
            success_url = reverse('contacts:view_edit_success',
                                  kwargs=dict(contact_id=person_obj.id))
            return HttpResponseRedirect(success_url)
        else:
            d.update({ERR_FOUND : True})
    else:
        f = PersonForm(instance=existing_person)


    # Add contacts and form to dictionary
    #
    d.update({'person_form' : f,
              'existing_person' : existing_person,
              'just_edited' : just_edited
              })

    return render_to_response('contacts/edit_contact.html',
                            d,
                            context_instance=RequestContext(request))


    """
    # contact_id = request.POST['id']
    try:
        person = Person.objects.get(pk=contact_id)
        name = request.POST['display_name']
        if (name):
            person.display_name = name
        # FIXME: can't blank out phone number
        phone = request.POST['phone_number']
        if (phone):
            person.phone_number = phone
        person.save()
    except Person.DoesNotExist:
        raise Http404("Person does not exist")
    return HttpResponseRedirect('/contacts')
    """
