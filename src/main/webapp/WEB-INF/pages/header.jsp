<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
  <h1>Demo Servlet</h1>
  <nav>
    <a href="/DemoServletJava23/">Home</a>
    <a href="book">Book</a>
    <c:if test="${connectedUser == null}">
      <a href="login">Login</a>
    </c:if>
    <c:if test="${connectedUser != null}">
      <a href="logout">Logout</a>
    </c:if>
  </nav>
</header>
<hr>

