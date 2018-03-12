<%--
  Created by IntelliJ IDEA.
  User: kristinf
  Date: 3/9/18
  Time: 4:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${team.getName()} New Player</title>
</head>
<body>
Add a player to team ${team.getName()} ${team.getID()}

<form action="/addplayer?teamID=${team.getID()}" method="post">
    First Name <input type="text" name="firstName">
    Last Name <input type="text" name="lastName">
    Age <input type="number" name="age">
    <input type="submit" name="submit" value="Add">
</form>
</body>
</html>
