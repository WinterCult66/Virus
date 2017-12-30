package com.virus.component;

// @author Kevin
import com.virus.entity.Contact;
import com.virus.model.ContactModel;
import org.springframework.stereotype.Component;

@Component("contactConverter")
public class ContactConverter {

    public Contact convertContactModel2Contact(ContactModel contactModel) {
        Contact contact = new Contact();
        contact.setCity(contactModel.getCity());
        contact.setLastName(contactModel.getLastName());
        contact.setFirstName(contactModel.getFirstName());
        contact.setTelephone(contactModel.getTelephone());
        contact.setId(contactModel.getId());
        return contact;
    }

    public ContactModel convertContact2ContactModel(Contact contact) {

        ContactModel  contactModel= new  ContactModel();
        
        contactModel.setCity(contact.getCity());
        contactModel.setLastName(contact.getLastName());
        contactModel.setFirstName(contact.getFirstName());
        contactModel.setTelephone(contact.getTelephone());
        contactModel.setId(contact.getId());
        return contactModel;
    }
}
