<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" href="css/styles.css" media="screen" title="no title"  charset="utf-8">

<form:form method="POST" action="/persons/new" modelAttribute="person">
    <form:label path="firstName">First Name
        <form:errors path="firstName"/>
        <form:input path="firstName"/></form:label><br/>

    <form:label path="lastName">Last Name
        <form:errors path="lastName"/>
        <form:input path="lastName"/></form:label><br/>

    <input type="submit" name="submit" value="Create">
</form:form>