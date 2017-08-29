package ru.bsa.tinkoff.services.contact.exception;

public class ContactNotFoundException extends Exception {

    private static final long serialVersionUID = -8546966030804276512L;

    public ContactNotFoundException(final String message) {
        super(message);
    }
}
