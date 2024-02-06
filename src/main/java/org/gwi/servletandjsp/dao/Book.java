package org.gwi.servletandjsp.dao;

public record Book(String iban, String title, String author) {

    public String summary() {
        return "IBAN: %s - Title: %s - Author: %s".formatted(
            iban(),
            title(),
            author()
        );
    }
}
