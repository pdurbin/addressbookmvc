package com.greptilian.addressbookmvc.javaee7.jsf;

import com.greptilian.addressbookmvc.javaee7.AddressBookServiceBean;
import com.greptilian.addressbookmvc.javaee7.Person;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@ViewScoped
@Named
public class HomePage implements Serializable {

    @EJB
    AddressBookServiceBean addressBookService;

    String displayNameToAdd;

    public List<Person> getAllPeople() {
        return addressBookService.findAll();
    }

    public void add() {
        Person personToAdd = new Person();
        personToAdd.setDisplayName(displayNameToAdd);
        addressBookService.add(personToAdd);
    }

    public void delete(Person doomed) {
        addressBookService.delete(doomed.getId());
    }

    public String getDisplayNameToAdd() {
        return displayNameToAdd;
    }

    public void setDisplayNameToAdd(String displayNameToAdd) {
        this.displayNameToAdd = displayNameToAdd;
    }

}
