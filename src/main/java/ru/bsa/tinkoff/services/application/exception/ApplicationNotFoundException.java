package ru.bsa.tinkoff.services.application.exception;

public class ApplicationNotFoundException extends Exception {

    private static final long serialVersionUID = 8202311802881677362L;

    public ApplicationNotFoundException(final String message) {
        super(message);
    }
}
