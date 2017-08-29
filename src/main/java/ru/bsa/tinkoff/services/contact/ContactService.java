package ru.bsa.tinkoff.services.contact;


import ru.bsa.tinkoff.services.contact.exception.ContactAlreadyExistsException;
import ru.bsa.tinkoff.services.contact.exception.ContactNotFoundException;
import ru.bsa.tinkoff.services.contact.model.Contact;

public interface ContactService {


    Contact addContact(final int contactId) throws ContactAlreadyExistsException;

    Contact findContact(final int contactId) throws ContactNotFoundException;
}
