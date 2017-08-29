package ru.bsa.tinkoff.services.contact.dao;

import org.jooq.impl.DefaultDSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.bsa.tinkoff.jooq.tables.records.ContactsRecord;
import ru.bsa.tinkoff.services.contact.model.Contact;

import static ru.bsa.tinkoff.jooq.tables.Contacts.CONTACTS;

import javax.inject.Inject;
import java.util.Optional;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class ContactDao {
    private final DefaultDSLContext jooq;

    @Inject
    public ContactDao(final DefaultDSLContext jooq) {
        this.jooq = jooq;
    }

    public Contact insert(final int contactId) {
        jooq.insertInto(CONTACTS).set(CONTACTS.CONTACT_ID, contactId).execute();
        return new Contact(contactId);
    }

    public Optional<Contact> findContact(final int contactId) {
        return jooq.selectFrom(CONTACTS)
                .where(CONTACTS.CONTACT_ID.eq(contactId))
                .fetchOptional(r -> new Contact(r.get(CONTACTS.CONTACT_ID)));
    }
}
