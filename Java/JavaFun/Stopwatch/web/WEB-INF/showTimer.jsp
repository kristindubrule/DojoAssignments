<%--
  Created by IntelliJ IDEA.
  User: kristinf
  Date: 3/8/18
  Time: 2:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" href="static/css/styles.css" media="screen" title="no title"  charset="utf-8">

<html>
<head>
    <title>Timer</title>
</head>
<body>
    <form action="/stopwatch" method="post">
        <input type="submit" name="submit" value="Start">
        <input type="submit" name="submit" value="Stop">
        <input type="submit" name="submit" value="Reset">
        <br/>
        <c:if test="${not empty timers}">
            <div class="time">Start time:
                ${timers.get(0).getStartTime()}</div>
            <div class="time">Current time: ${timers.get(0).currentTime()}</div>
            <div class="time">Running Time:
                <c:if test="${not empty curtimer}">${curtimer.calculateTimeMinutes()}m
                    ${timer.calculateTimeSeconds()}s
                </c:if>
            </div>
            <c:if test="${fn:length(timers) gt 0}">
                <table>
                <tr><td>Start</td><td>Stop</td><td>Total</td></tr>
                <c:forEach items="${timers}" var="t">
                    <tr><td>${t.getStartTime()}</td><td>${t.getEndTime()}</td><td>${t.calculateTimeMinutes()}m ${t.calculateTimeSeconds()}s</td></tr>
                </c:forEach>
                </table>
            </c:if>
        </c:if>
    </form>
</body>
</html>
