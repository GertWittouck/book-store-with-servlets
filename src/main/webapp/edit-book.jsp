<%--
  Created by IntelliJ IDEA.
  User: gertwittouck
  Date: 05/02/2024
  Time: 22:35
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.gwi.servletandjsp.constants.Action" %>
<%@ page import="org.gwi.servletandjsp.dao.Book" %>
<html>
<head>
    <title>Edit book</title>
</head>
<body>
<h2>Edit new book</h2>
<div>
    <form action="book?iban=${param.get("existing-iban")}" method="post">
        <label for="iban">IBAN:</label>
        <input id="iban" type="text" name="iban" disabled value="${param.get("existing-iban")}"/><br>
        <label for="title">TITLE:</label>
        <input id="title" type="text" name="title" value="${param.get("existing-title")}"/><br>
        <label for="author">AUTHOR</label>
        <input id="author" type="text" name="author" value="${param.get("existing-author")}"><br>
        <button type="submit" name="action" value="${Action.EDIT}">EDIT</button>
    </form>
</div>
</body>
</html>
