package com.virus.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.virus.component.ContactConverter;
import com.virus.entity.Contact;
import com.virus.model.ContactModel;
import com.virus.repository.ContactRepository;
import com.virus.services.ContactService;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ContactConverter contactConverter;

    @Override
    public ContactModel addContact(ContactModel contactModel) {
        Contact contact = contactRepository.save(contactConverter.convertContactModel2Contact(contactModel));
        return contactConverter.convertContact2ContactModel(contact);
    }

    @Override
    public List<ContactModel> listAllContacts() {
        List<Contact> contacts = contactRepository.findAll();
        List<ContactModel> contactsModel = new ArrayList<ContactModel>();
        for (Contact contact : contacts) {
            contactsModel.add(contactConverter.convertContact2ContactModel(contact));
        }
        return contactsModel;
    }

    @Override
    public Contact findContactById(int id) {
        return contactRepository.findById(id);
    }

    @Override
    public void removeContact(int id) {
        Contact contact = contactRepository.findById(id);
        if (null != contact) {
            contactRepository.delete(contact);
        }
    }

    @Override
    public ContactModel findContactByIdModel(int id) {
        return contactConverter.convertContact2ContactModel(findContactById(id));

    }

}
