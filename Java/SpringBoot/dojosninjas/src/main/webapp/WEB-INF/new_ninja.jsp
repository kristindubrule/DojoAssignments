<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" href="css/styles.css" media="screen" title="no title"  charset="utf-8">

<form:form method="POST" action="/ninjas/new" modelAttribute="ninja">
    <div class="element">
        <form:label path="dojo">Dojo
            <form:select path="dojo">
                <form:option value="NONE" label="--- Select ---"/>
                <c:forEach items="${dojos}" var="dojo">
                    <form:option value="${dojo.id}">${dojo.name}</form:option>
                </c:forEach>
            </form:select>
        </form:label>
    </div>

    <div class="element">
        <form:label path="firstName">First Name
            <form:errors path="firstName"/>
            <form:input path="firstName"/></form:label>
    </div>
    <div class="element">
        <form:label path="lastName">Last Name
            <form:errors path="lastName"/>
            <form:input path="lastName"/></form:label>
    </div>
    <div class="element">
        <form:label path="age">Age
            <form:errors path="age"/>
            <form:input path="age"/></form:label>
    </div>
    <input type="submit" name="submit" value="Create"/>
</form:form>
