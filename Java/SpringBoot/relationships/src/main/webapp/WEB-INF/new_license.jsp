<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" href="css/styles.css" media="screen" title="no title"  charset="utf-8">

<form:form method="POST" action="/licenses/new" modelAttribute="license">
    <form:label path="person">Person
        <form:errors path="person"/>
        <form:select path="person">
            <form:option value="NONE" label="--- Select ---"/>
            <c:forEach items="${persons}" var="person">
                <form:option value="${person.id}">${person.firstName} ${person.lastName}</form:option>
            </c:forEach>
            </form:select>
            </form:label><br/>

    <form:label path="state">State
        <form:errors path="state"/>
        <form:input path="state"/></form:label><br/>

    <form:label path="expiration_date">Expiration Date
        <form:errors path="expiration_date"/>
        <form:input type="date" path="expiration_date"/></form:label><br/>

    <input type="submit" name="submit" value="Create">
</form:form>