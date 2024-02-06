package org.gwi.servletandjsp.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(HelloServlet.class.getName());

    private String message;

    @Override
    public void init() {
        message = "Hello World!";
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");

        String newMessage = request.getParameter("message");
        LOG.log(Level.INFO, "Query String: {}", newMessage);

        // Hello
        try (PrintWriter out = response.getWriter()) {
            out.println("<html><body>");
            out.println("<h1>" + message(newMessage) + "</h1>");
            out.println("</body></html>");
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Failed to create response due to {}", e.getMessage());
        }
    }

    private String message(String value) {
        return Optional.ofNullable(value)
            .orElse(message);
    }

    @Override
    public void destroy() {
        LOG.log(Level.FINE, "No actions required during destroy");
    }
}