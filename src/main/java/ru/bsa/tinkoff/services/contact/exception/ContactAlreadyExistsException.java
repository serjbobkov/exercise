package ru.bsa.tinkoff.services.contact.exception;

public class ContactAlreadyExistsException extends Exception {

    private static final long serialVersionUID = -6320154989963595932L;

    public ContactAlreadyExistsException(final String message) {
        super(message);
    }
}
