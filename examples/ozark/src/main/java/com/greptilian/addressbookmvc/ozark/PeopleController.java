package com.greptilian.addressbookmvc.ozark;

import com.greptilian.addressbookmvc.model.AddressBookServiceBean;
import com.greptilian.addressbookmvc.model.Person;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.mvc.Controller;
import javax.mvc.Models;
import javax.mvc.View;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("contacts")
public class PeopleController {

    @Inject
    private Models models;

    @EJB
    AddressBookServiceBean addressBookService;

    @GET
    @Controller
    @Produces("text/html")
    @View("list.mustache")
    public void list(@QueryParam("contact") String contact) {
        List<String> contacts = new ArrayList<>();
        List<Person> allPeople = addressBookService.findAll();
        for (Person person : allPeople) {
            contacts.add(person.getDisplayName());
        }
        models.put("contacts", contacts);
    }

    @POST
    @Controller
    @Produces("text/html")
    @View("save.mustache")
    public void save(@FormParam("name") String displayName) {
        Person person = new Person();
        person.setDisplayName(displayName);
        Person savedPerson = addressBookService.save(person);
        models.put("user", displayName + savedPerson.getId());
    }
}
