<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="/css/styles.css" media="screen" title="no title"  charset="utf-8">

<form:form action="${pageContext.request.contextPath}/logout" method="POST">
    <input type="submit" value="Logout" />
</form:form>

${errors}

<h2>${event.name}</h2>

Host: ${event.host.firstName}<br/>
Date: ${event.eventDate}<br/>
Location: ${event.locationCity}, ${event.locationState}<br/>
People who are attending: ${event.attendees.size()}<br/>
<br/>
<c:if test="${fn:length(event.attendees) > 0}">
    <table>
    <tr><td>Name</td><td>Location</td></tr>
</c:if>
<c:forEach items="${event.attendees}" var="attendee">
    <tr><td>${attendee.firstName} ${attendee.lastName}</td><td>${attendee.location}</td></tr>
</c:forEach>
<c:if test="${fn:length(event.attendees) > 0}">
    </table>
</c:if>
<br/>
<h2>Message Wall</h2><br/>

<div id="wall">
    <c:forEach items="${event.messages}" var="message">
        ${message.author.firstName} ${message.author.lastName} says: ${message.content}<br/>
    </c:forEach>
</div>
<br/><br/>
<form:form method="POST" action="/events/message" modelAttribute="message">
    <form:hidden path="event.id" value="${event.id}"/>
    <form:hidden path="author.id" value="${author.id}"/>

    <form:label path="content">Content
        <form:errors path="content"/>
        <form:textarea path="content"/></form:label>

    <input type="submit" name="submit" value="Submit"/>
</form:form>