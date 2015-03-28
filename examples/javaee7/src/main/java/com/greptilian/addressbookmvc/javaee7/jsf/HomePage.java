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

    public List<Person> getAllPeople() {
        return addressBookService.findAll();
    }
}
