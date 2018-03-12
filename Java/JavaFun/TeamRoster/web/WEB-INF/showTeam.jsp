<%--
  Created by IntelliJ IDEA.
  User: kristinf
  Date: 3/9/18
  Time: 3:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Show Team</title>
</head>
<body>
    <c:if test="${not empty team}">
        ${team.name}
        <a href="/addplayer?teamID=${team.getID()}">New Ninja</a>
        <c:if test="${fn:length(team.players) gt 0}">
            <table>
                <tr><td>First Name</td><td>Last Name</td><td>Age</td><td>Actions</td></tr>
                <c:forEach items="${team.getPlayers()}" var="player">
                    <tr><td>${player.getFirstName()}</td><td>${player.getLastName()}</td><td>${player.getAge()}</td>
                        <td><a href="/deleteplayer?id=${player.getID()}&teamID=${team.getID()}">Delete</a></td></tr>

                </c:forEach>
            </table>

        </c:if>

    </c:if>
</body>
</html>
