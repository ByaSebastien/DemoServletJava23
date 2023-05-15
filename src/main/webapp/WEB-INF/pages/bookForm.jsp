<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles/style.css">
    <title>bookForm</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<form method="post" action="addBook">
    <fieldset>
        <label for="title">Titre : </label>
        <input type="text" id="title" name="title" value="${title}">
        <label for="description">Description : </label>
        <input type="text" id="description" name="description" value="${description}">
        <select id="authorId" name="authorId">
            <option value="" selected></option>
            <c:forEach items="${authors}" var="author">
                <option value="${author.id}">${author.lastname} ${author.firstname} ${author.pseudo}</option>
            </c:forEach>
        </select>
        <button type="button" name="+" id="switchButton" onclick="switchFormType()"> ➕ </button>
    </fieldset>
    <br>
    <fieldset class="hidden" id="authorForm">
        <label for="firstname">Prenom : </label>
        <input type="text" id="firstname" name="firstname" value="${firstname}">
        <label for="lastname">Nom : </label>
        <input type="text" id="lastname" name="lastname" value="${lastname}">
        <label for="pseudo">Pseudo : </label>
        <input type="text" id="pseudo" name="pseudo" value="${pseudo}" }>
    </fieldset>
    <button type="submit">Ajouter</button>
    <input type="checkbox" name="wantToAddAuthor" id="wantToAddAuthor" hidden="hidden">
</form>
<script src="scripts/script.js"></script>
</body>
</html>
