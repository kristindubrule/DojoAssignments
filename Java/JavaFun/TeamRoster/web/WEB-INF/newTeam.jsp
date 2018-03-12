<%--
  Created by IntelliJ IDEA.
  User: kristinf
  Date: 3/9/18
  Time: 3:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Team</title>
</head>
<body>
Create a new team

<form action="/teams" method="post">
    Team Name <input type="text" name="name">
    <input type="submit" name="submit" value="Create">
</form>
</body>
</html>
