package ru.bsa.tinkoff.services.contact.exception;

public class ContactNotFoundException extends Exception {

    public ContactNotFoundException(final String message) {
        super(message);
    }
}
