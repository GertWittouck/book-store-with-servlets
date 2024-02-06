package org.gwi.servletandjsp.repository;

import org.gwi.servletandjsp.dao.Book;

import java.util.Collection;
import java.util.Optional;

public interface BookRepository {

    Book create(Book newBook);

    Book update(Book bookToUpdate);

    void delete(String iban);

    Optional<Book> findByIban(String iban);

    Collection<Book> findAll();
}
