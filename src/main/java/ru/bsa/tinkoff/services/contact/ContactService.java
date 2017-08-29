package ru.bsa.tinkoff.services.contact;

import ru.bsa.tinkoff.services.contact.exception.ContactAlreadyExistsException;
import ru.bsa.tinkoff.services.contact.exception.ContactNotFoundException;
import ru.bsa.tinkoff.services.contact.model.Contact;

import javax.annotation.Nonnull;

public interface ContactService {


    @Nonnull
    Contact addContact(final int contactId) throws ContactAlreadyExistsException;

    @Nonnull
    Contact findContact(final int contactId) throws ContactNotFoundException;
}
