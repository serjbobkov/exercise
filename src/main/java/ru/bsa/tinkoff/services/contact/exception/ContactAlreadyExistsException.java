package ru.bsa.tinkoff.services.contact.exception;

import java.io.Serializable;

public class ContactAlreadyExistsException extends Exception {
    public ContactAlreadyExistsException(final String message) {
        super(message);
    }
}
