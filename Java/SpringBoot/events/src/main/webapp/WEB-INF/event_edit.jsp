<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" href="/css/styles.css" media="screen" title="no title"  charset="utf-8">

<form:form action="${pageContext.request.contextPath}/logout" method="POST">
    <input type="submit" value="Logout" />
</form:form>

Edit Event ${event}

<form:form method="POST" action="/events/update" modelAttribute="event">
    <form:hidden path="id"/>

    <form:label path="name">Name
        <form:errors path="name"/>
        <form:input path="name"/></form:label>

    <form:label path="eventDate">Date
        <form:errors path="eventDate"/>
        <form:input type="date" path="eventDate"/></form:label>

    <form:label path="locationCity">Location
        <form:errors path="locationCity"/>
        <form:input path="locationCity"/></form:label>

    <form:label path="locationState">State</form:label>
    <form:select path="locationState">
        <form:option value="NONE" label="--- Select ---"/>
        <c:forEach items="${stateList}" var="state">
            <c:choose>
                <c:when test="${event.locationState == state}">
                    <form:option value="${state}" label="${state}" selected="selected"/>
                </c:when>
                <c:otherwise>
                    <form:option value="${state}" label="${state}"/>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </form:select>
    <input type="submit" name="submit" value="Edit"/>
</form:form>