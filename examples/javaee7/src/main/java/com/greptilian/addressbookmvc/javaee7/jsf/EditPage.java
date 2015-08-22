package com.greptilian.addressbookmvc.javaee7.jsf;

import com.greptilian.addressbookmvc.javaee7.AddressBookServiceBean;
import com.greptilian.addressbookmvc.javaee7.Person;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@SuppressWarnings("initialization.fields.uninitialized")
@ViewScoped
@Named
public class EditPage implements Serializable {

    @EJB
    AddressBookServiceBean addressBookService;

    long idToEdit;
    Person personToEdit;
    String displayName;
    String phoneNumber;

    public void init() {
        personToEdit = addressBookService.find(idToEdit);
        if (personToEdit != null) {
            displayName = personToEdit.getDisplayName();
            phoneNumber = personToEdit.getPhoneNumber();
        }
    }

    public void save() {
        personToEdit.setDisplayName(displayName);
        personToEdit.setPhoneNumber(phoneNumber);
        addressBookService.save(personToEdit);
    }

    public long getIdToEdit() {
        return idToEdit;
    }

    public void setIdToEdit(long idToEdit) {
        this.idToEdit = idToEdit;
    }

    public Person getPersonToEdit() {
        return personToEdit;
    }

    public void setPersonToEdit(Person personToEdit) {
        this.personToEdit = personToEdit;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
