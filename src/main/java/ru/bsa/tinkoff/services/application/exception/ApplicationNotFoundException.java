package ru.bsa.tinkoff.services.application.exception;

import java.io.Serializable;

public class ApplicationNotFoundException extends Exception {
    public ApplicationNotFoundException(final String message) {
        super(message);
    }
}
