package ru.bsa.tinkoff.services.contact.exception;

public class ContactAlreadyExistsException extends Exception {
    public ContactAlreadyExistsException(final String message) {
        super(message);
    }
}
