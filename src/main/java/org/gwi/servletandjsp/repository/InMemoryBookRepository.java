package org.gwi.servletandjsp.repository;

import org.gwi.servletandjsp.dao.Book;
import org.gwi.servletandjsp.exceptions.DuplicateBookException;
import org.gwi.servletandjsp.exceptions.MissingBookException;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Optional.ofNullable;

public class InMemoryBookRepository implements BookRepository {

    private final Map<String, Book> bookCollection;

    public InMemoryBookRepository() {
        this.bookCollection = new HashMap<>();
    }

    @Override
    public Book create(Book newBook) {
        if (bookCollection.containsKey(newBook.iban())) {
            throw new DuplicateBookException(newBook.iban());
        }
        bookCollection.put(newBook.iban(), newBook);
        return bookCollection.get(newBook.iban());
    }

    @Override
    public Book update(Book bookToUpdate) {
        if (!bookCollection.containsKey(bookToUpdate.iban())) {
            throw new MissingBookException(bookToUpdate.iban());
        }
        return bookCollection.compute(bookToUpdate.iban(),  (iban, book) -> bookToUpdate);
    }

    @Override
    public void delete(String iban) {
        findByIban(iban)
            .ifPresent(book -> this.bookCollection.remove(book.iban()));
    }

    @Override
    public Optional<Book> findByIban(String iban) {
        return ofNullable(bookCollection.get(iban));
    }

    @Override
    public Collection<Book> findAll() {
        return List.copyOf(bookCollection.values());
    }
}
