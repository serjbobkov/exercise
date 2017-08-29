package ru.bsa.tinkoff.services.contact.model;

import java.io.Serializable;

public class Contact implements Serializable {


    private static final long serialVersionUID = -6705441617941413187L;

    private final int contactId;

    public Contact(final int accountId) {
        this.contactId = accountId;
    }

    public int getContactId() {
        return contactId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Contact contact = (Contact) o;

        return contactId == contact.contactId;
    }

    @Override
    public int hashCode() {
        return contactId;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "contactId=" + contactId
                + '}';
    }
}
