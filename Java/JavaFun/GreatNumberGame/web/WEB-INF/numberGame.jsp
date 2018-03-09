<%--
  Created by IntelliJ IDEA.
  User: kristinf
  Date: 3/9/18
  Time: 1:38 PM
  To change this template use File | Settings | File Templates.
--%>
<link rel="stylesheet" href="static/css/styles.css" media="screen" title="no title"  charset="utf-8">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Great Number Game</title>
</head>
<body>
    <div id="main">
    <h2>Welcome to the Great Number Game!</h2>

    <h4>I am thinking of a number between 1 and 100</h4>
    <h4>Take a guess!</h4>

    <c:if test="${not empty direction}">
        <div class="guess ${direction}">
            ${message}
        <c:if test="${direction == 'match'}">
            <form action = "/reset">
                <input type="submit" name="submit" value="Play Again?">
            </form>
        </c:if>
        </div>
    </c:if>
    <form action="/guess" method="post">
        <input type = "text" name="guess" value="${guessed}"><br/>
        <input type="submit" name="submit" value="Submit">
    </form>
    </div>
</body>
</html>
