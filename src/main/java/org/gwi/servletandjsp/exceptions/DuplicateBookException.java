package org.gwi.servletandjsp.exceptions;

public class DuplicateBookException extends RuntimeException {
    public DuplicateBookException(String iban) {
        super("We have already a book stored with IBAN %s".formatted(iban));
    }
}
