package com.virus.services;

import com.virus.entity.Contact;
import com.virus.model.ContactModel;
import java.util.List;
//import org.springframework.stereotype.Repository;

public interface ContactService {

    public abstract ContactModel addContact(ContactModel contactModel);

    public abstract List<ContactModel> listAllContacts();

    public abstract Contact findContactById(int id);

    public abstract void removeContact(int id);

    public abstract ContactModel findContactByIdModel(int id);
}
