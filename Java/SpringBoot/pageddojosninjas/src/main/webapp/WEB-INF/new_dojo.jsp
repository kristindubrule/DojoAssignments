<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" href="css/styles.css" media="screen" title="no title"  charset="utf-8">

<div class="error">${errors}</div>

<form:form method="POST" action="/dojos/new" modelAttribute="dojo">
    <form:label path="name">Name
        <form:errors path="name"/>
        <form:input path="name"/></form:label>
    <input type="submit" name="submit" value="Create"/>
</form:form>