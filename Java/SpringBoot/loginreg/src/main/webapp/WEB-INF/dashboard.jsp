<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Dashboard</title>
</head>
<body>

<h1>Welcome ${current_user.firstName}</h1>

<c:if test="${fn:length(users) > 0}">
    <table>
        <tr><td>Name</td><td>Email</td><td>Action</td></tr>
</c:if>
<c:forEach items="${users}" var="user">
    <tr><td>${user.firstName} ${user.lastName}</td><td>${user.username}</td>
        <td>
            <c:choose>
                <c:when test="${admins[user.id]}">
                    admin
                </c:when>
                <c:otherwise>
                    <a href="/delete/${user.id}">delete</a>
                    &nbsp;
                    <a href="/make-admin/${user.id}">make-admin</a>
                </c:otherwise>
            </c:choose>
        </td>
    </tr>
</c:forEach>
<c:if test="${fn:length(users) > 0}">
    </table>
</c:if>
<form id="logoutForm" method="POST" action="/logout">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="submit" value="Logout!" />
</form>
</body>
</html>
