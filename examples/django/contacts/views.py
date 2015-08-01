from django.http import HttpResponse, HttpResponseRedirect, Http404
from django.template import RequestContext, loader
from django.shortcuts import render
from .models import Person


def index(request):
    contacts_list = Person.objects.order_by('-display_name')[:999999]
    template = loader.get_template('contacts/index.html')
    context = RequestContext(request, {
        'contacts_list': contacts_list,
    })
    return HttpResponse(template.render(context))


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


def edit(request):
    contact_id = request.POST['id']
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
