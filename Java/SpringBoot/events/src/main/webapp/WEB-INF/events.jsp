<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel="stylesheet" href="/css/styles.css" media="screen" title="no title"  charset="utf-8">


<form:form action="${pageContext.request.contextPath}/logout" method="POST">
    <input type="submit" value="Logout" />
</form:form>

Events

${errors}

<h2>Events in your state:</h2>
<c:if test="${fn:length(localEvents) > 0}">
    <table>
</c:if>
<c:forEach items="${localEvents}" var="event">
    <tr><td><a href="/events/${event.id}">${event.name}</a></td><td>${event.eventDate}</td><td>${event.locationCity}</td><td>${event.locationState}</td><td>${event.host.firstName}</td>
    <td>
        <c:choose>
            <c:when test="${event.host.id == currentUser.id}">
                <a href="/events/edit/${event.id}">Edit</a> <a href="/events/delete/${event.id}">Delete</a></td>
            </c:when>
            <c:otherwise>
                <c:choose>
                    <c:when test="${userEvents.containsKey(event.id)}">
                        <a href="/events/leave/${event.id}">Cancel</a>
                    </c:when>
                    <c:otherwise>
                        <a href="/events/join/${event.id}">Join</a>
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>
    </tr>
</c:forEach>
<c:if test="${fn:length(localEvents) > 0}">
    </table>
</c:if>

<h2>Events in other states</h2>
<c:if test="${fn:length(otherEvents) > 0}">
    <table>
</c:if>
<c:forEach items="${otherEvents}" var="event">
    <tr><td><a href="/events/${event.id}">${event.name}</a></td><td>${event.eventDate}</td><td>${event.locationCity}</td><td>${event.locationState}</td><td>${event.host.firstName}</td>
       <td>
        <c:choose>
            <c:when test="${event.host.id == currentUser.id}">
                <a href="/events/edit/${event.id}">Edit</a> <a href="/events/delete/${event.id}">Delete</a>
            </c:when>
            <c:otherwise>
                <c:choose>
                    <c:when test="${userEvents.containsKey(event.id)}">
                        <a href="/events/leave/${event.id}">Cancel</a>
                    </c:when>
                    <c:otherwise>
                        <a href="/events/join/${event.id}">Join</a>
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>
       </td>
    </tr>
</c:forEach>
<c:if test="${fn:length(otherEvents) > 0}">
    </table>
</c:if>

<form:form method="POST" action="/events/new" modelAttribute="event">
    <form:hidden path="host.id" value="${host.id}"/>

<div class="element">
    <form:label path="name">Name
        <form:errors path="name"/>
        <form:input path="name"/></form:label>
</div>
    <div class="element">

    <form:label path="eventDate">Date
        <form:errors path="eventDate"/>
        <form:input type="date" path="eventDate"/></form:label>
    </div>
    <div class="element">

    <form:label path="locationCity">Location
        <form:errors path="locationCity"/>
        <form:input path="locationCity"/></form:label>

        <form:errors path="locationState"/>
        <form:select path="locationState">
            <form:option value=""/>
            <c:forEach items="${stateList}" var="state">
                <form:option value="${state}" label="${state}"/>
            </c:forEach>
        </form:select>
    </div>

    <input type="submit" name="submit" value="Create"/>
</form:form>