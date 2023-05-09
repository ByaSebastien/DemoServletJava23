<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
</head>
<body>
    <h1>Hello ${connectedUser.username}!</h1>
    <% for(int i = 1; i<= 6; i++){ %>
        <h<%=i%>>Mon titre</h<%=i%>>
    <% } %>
    <br/>
    <a href="register">register</a>
    <a href="login">login</a>
</body>
</html>