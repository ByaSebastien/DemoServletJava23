<%--
  Created by IntelliJ IDEA.
  User: byase
  Date: 09-05-23
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>
</head>
<body>
    <h1>Register</h1>
    <form method="post" action="register">
        <label for="username">Username : </label>
        <input type="text" id="username" name="username" value="${username}">
        <label for="email">Email : </label>
        <input type="text" id="email" name="email" value="${email}">
        <label for="password">Password : </label>
        <input type="password" id="password" name="password">
        <label for="confirmPassword">Confirm password : </label>
        <input type="password" id="confirmPassword" name="confirmPassword">
        <span style="color: red">${errorMessage}</span>
        <button type="submit">Register</button>
    </form>
</body>
</html>
