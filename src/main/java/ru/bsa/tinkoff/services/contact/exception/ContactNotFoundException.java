package ru.bsa.tinkoff.services.contact.exception;

import java.io.Serializable;

public class ContactNotFoundException extends Exception {

    public ContactNotFoundException(final String message) {
        super(message);
    }
}
