package ru.bsa.tinkoff.services.application.exception;

public class ApplicationNotFoundException extends Exception {
    public ApplicationNotFoundException(final String message) {
        super(message);
    }
}
