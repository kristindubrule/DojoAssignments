<%--
  Created by IntelliJ IDEA.
  User: kristinf
  Date: 3/7/18
  Time: 5:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="/static/css/styles.css" media="screen" title="no title"  charset="utf-8">

<html>
<head>
    <title><c:out value="${pet}"/></title>
</head>
<body>
    <div class="displaypet">
        <p>You created a <c:out value="${animal.getBreed()}"/>!</p>
        <c:out value="${animal.showAffection()}"/>
    </div>
</body>
</html>
