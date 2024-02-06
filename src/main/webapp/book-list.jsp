<%--
  Created by IntelliJ IDEA.
  User: gertwittouck
  Date: 05/02/2024
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page import="java.util.Collection"%>
<%@ page import="org.gwi.servletandjsp.dao.Book"%>
<%@ page import="org.gwi.servletandjsp.constants.Action" %>
<!DOCTYPE html>
<html xml:lang="en">
<head>
    <title>Book Store - Overview</title>
</head>
<body>
    <h1>Book Collection</h1>
    <div>
        <table>
            <thead>
                <tr>
                    <td>IBAN</td>
                    <td>TITLE</td>
                    <td>AUTHOR</td>
                    <td></td>
                    <td></td>
                <tr/>
            <thead/>
            <c:forEach items="${books}" var="book">
                <tr>
                    <td><c:out value="${book.iban()}"/></td>
                    <td><c:out value="${book.title()}"/></td>
                    <td><c:out value="${book.author()}"/></td>
                    <td><a href="edit-book.jsp?existing-iban=${book.iban()}&existing-title=${book.title()}&existing-author=${book.author()}">Edit</a></td>
                    <td>
                        <form name="delete-book" action="book?iban=${book.iban()}" method="post">
                            <button type="submit" name="action" value="${Action.DELETE}">Remove</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <br>
    <a href="add-book.jsp">Add new book</a>
</body>
</html>
