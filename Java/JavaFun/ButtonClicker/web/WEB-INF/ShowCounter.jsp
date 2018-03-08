<%--
  Created by IntelliJ IDEA.
  User: kristinf
  Date: 3/8/18
  Time: 11:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Counter</title>
</head>
<body>
    <form action="Counter">
        <input type="submit" name="submit" value="Submit">
    </form>
    <form action="Reset">
        <input type="submit" name="submit" value="Reset">
    </form>
    <c:out value="${counter}"/>
</body>
</html>
