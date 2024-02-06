<%--
  Created by IntelliJ IDEA.
  User: gertwittouck
  Date: 05/02/2024
  Time: 22:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.gwi.servletandjsp.constants.Action" %>
<html>
<head>
    <title>Add new book</title>
</head>
<body>
    <h2>Add new book</h2>
    <div>
        <form action="book" method="post">
            <label for="add-iban">IBAN:</label>
            <input id="add-iban" type="text" name="new-iban"/><br>
            <label for="add-title">TITLE:</label>
            <input id="add-title" type="text" name="new-title"/><br>
            <label for="add-author">AUTHOR</label>
            <input id="add-author" type="text" name="new-author"><br>
            <button type="submit" name="action" value="${Action.ADD}">ADD</button>
        </form>
    </div>
</body>
</html>
