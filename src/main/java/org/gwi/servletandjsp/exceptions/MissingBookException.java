package org.gwi.servletandjsp.exceptions;

public class MissingBookException extends RuntimeException {
    public MissingBookException(String iban) {
        super("No book found for IBAN %s".formatted(iban));
    }
}
