<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="bstorm.be.demoservletjava23.domain.dtos.BookDTO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: byase
  Date: 10-05-23
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book</title>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <h2>Book!!!</h2>
    <table>
        <thead>
            <tr>
                <th>Titre</th>
                <th>Description</th>
            </tr>
        </thead>
        <tbody>
<%--            <% for (BookDTO b : (List<BookDTO>)request.getAttribute("books")){ %>--%>
<%--            <tr>--%>
<%--                <td><%=b.getTitle()%></td>--%>
<%--                <td><%=b.getDescription()%></td>--%>
<%--            </tr>--%>
<%--            <% } %>--%>
            <c:forEach items="${books}" var="book">
                <tr>
                    <td>${book.title}</td>
                    <td>${book.description}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
