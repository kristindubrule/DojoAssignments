<%--
  Created by IntelliJ IDEA.
  User: kristinf
  Date: 3/8/18
  Time: 1:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Random Word Generator</title>
</head>
<body>
    You have generated a word <c:out value="${counter}"/> times.

    <div class="box"><c:out value="${word}"/></div>

    <form action="/Random">
        <input type="submit" name="submit" value="Generate">
    </form>

        The last time you generated a word was:
        <div class="box"><c:out value="${lastdate}"/></div>
</body>
</html>
