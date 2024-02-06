package org.gwi.servletandjsp.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.gwi.servletandjsp.constants.Action;
import org.gwi.servletandjsp.dao.Book;
import org.gwi.servletandjsp.repository.BookRepository;
import org.gwi.servletandjsp.repository.InMemoryBookRepository;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "bookServlet", value = "/book")
public class BookServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(BookServlet.class.getName());

    private BookRepository bookRepository;

    @Override
    public void init(ServletConfig config) {
        this.bookRepository = new InMemoryBookRepository();

        LOG.finest("Initializing book store with fixed number of books");
        this.bookRepository.create(new Book("123-456-789", "Artificial Intelligence", "AI"));
        this.bookRepository.create(new Book("123-456-879", "Machine Learning", "ML"));
        this.bookRepository.create(new Book("123-456-987", "Clean Code", "Uncle Bob"));
        LOG.finest("Initializing of book store complete");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            loadBookCollection(req, resp);
        } catch (ServletException | IOException exception) {
            handleException(resp, exception);
        }
    }

    private void loadBookCollection(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.finest("Find all Books");
        Collection<Book> books = bookRepository.findAll();
        Collection<Book> sortedBooks = books.stream().sorted(Comparator.comparing(Book::title)).toList();
        req.setAttribute("books", sortedBooks);
        req.getRequestDispatcher("book-list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Action action = Action.valueOf(req.getParameter("action"));
        switch (action) {
            case ADD -> addBook(req, resp);
            case EDIT -> updateBook(req, resp);
            case DELETE -> deleteBook(req, resp);
        }
    }

    private void addBook(HttpServletRequest request, HttpServletResponse response) {
        try {
            LOG.finest("Add new book");
            bookRepository.create(new Book(
                request.getParameter("new-iban"),
                request.getParameter("new-title"),
                request.getParameter("new-author")));
            loadBookCollection(request, response);
        } catch (ServletException | IOException exception) {
            handleException(response, exception);
        }
    }

    private void updateBook(HttpServletRequest request, HttpServletResponse response) {
        try {
            LOG.finest("Update book");
            bookRepository.update(new Book(
                request.getParameter("iban"),
                request.getParameter("title"),
                request.getParameter("author")));
            loadBookCollection(request, response);
        } catch (ServletException | IOException exception) {
            handleException(response, exception);
        }
    }

    /*
        Calls made from the JSP page only support GET and POST.
        To delete a book, the POST method is with and action of delete is used.
     */
    private void deleteBook(HttpServletRequest request, HttpServletResponse response) {
        try {
            LOG.finest("Delete book");
            bookRepository.delete(request.getParameter("iban"));
            loadBookCollection(request, response);
        } catch (ServletException | IOException exception) {
            handleException(response, exception);
        }
    }

    private static void handleException(HttpServletResponse response, Exception exception) {
        try (PrintWriter writer = response.getWriter()) {
            writer.println("<h1>Failed to Add a new book</h1>");
            writer.println("Error: " + exception.getMessage());
        } catch (IOException ioException) {
            LOG.log(Level.SEVERE, "Failed to add a new book due to {}", ioException.getMessage());
        }
    }

}
