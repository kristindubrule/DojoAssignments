<%--
  Created by IntelliJ IDEA.
  User: kristinf
  Date: 3/9/18
  Time: 3:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="static/css/styles.css" media="screen" title="no title"  charset="utf-8">

<html>
<head>
    <title>Roster</title>
</head>
<body>
${message}
<p>Prototype Roster</p>
<a href="/teams">New Team</a>
    <c:if test="${not empty teams}">
        <table>
            <c:forEach items="${teams.getTeams()}" var="team">
                <tr><td>${team.name}</td><td>${team.playerCount()}</td>
                    <td>
                        <a href="/teamdetails?id=<c:out value="${team.getID()}"/>">Details</a>
                        <a href="/delete?id=${team.getID()}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</body>
</html>
