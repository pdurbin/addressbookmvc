from django.conf.urls import patterns, include, url

urlpatterns = patterns('contacts.views',

    url(r'^$', 'index', name='index'),
    url(r'^(?P<contact_id>[0-9]+)/$', 'detail', name='detail'),
    url(r'^add/$', 'add', name='add'),
    url(r'^edit-contact-success/(?P<contact_id>[0-9]+)/$', 'edit_contact', name='edit_contact'),
    url(r'^edit-contact/(?P<contact_id>[0-9]+)/$', 'view_edit_success', name='view_edit_success'),
     url(r'^add/success/(?P<contact_id>[0-9]+)/$', 'view_add_success', name='view_add_success'),

)

"""
urlpatterns = [
    url(r'^$', views.index, name='index'),
    url(r'^(?P<contact_id>[0-9]+)/$', views.detail, name='detail'),
    url(r'^add/$', views.add, name='add'),
    url(r'^edit/$', views.edit, name='edit'),
]
"""