package ru.bsa.tinkoff.services.contact;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bsa.tinkoff.services.contact.dao.ContactDao;
import ru.bsa.tinkoff.services.contact.exception.ContactAlreadyExistsException;
import ru.bsa.tinkoff.services.contact.exception.ContactNotFoundException;
import ru.bsa.tinkoff.services.contact.model.Contact;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactService.class);


    private final ContactDao contactDao;

    @Inject
    public ContactServiceImpl(final ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    @Transactional
    @Nonnull
    @Override
    public Contact addContact(final int contactId) throws ContactAlreadyExistsException {

        if (contactDao.findContact(contactId).isPresent()) {
            throw new ContactAlreadyExistsException("Contact " + contactId + " already exists");
        }

        Contact contact = contactDao.insert(contactId);

        LOGGER.info("Contact added {}", contact);

        return contact;

    }

    @Transactional
    @Nonnull
    @Override
    public Contact findContact(final int contactId) throws ContactNotFoundException {
        Optional<Contact> contact = contactDao.findContact(contactId);

        if (contact.isPresent()) {
            return contact.get();
        } else {
            throw new ContactNotFoundException("Contact with id = " + contactId + " not found");
        }
    }
}
