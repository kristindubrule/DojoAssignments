<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" href="css/styles.css" media="screen" title="no title"  charset="utf-8">

<form:form method="POST" action="/languages/update/${id}" modelAttribute="language">
    <form:label path="name">Title
        <form:errors path="name"/>
        <form:input path="name"/></form:label>

    <form:label path="creator">Creator
        <form:errors path="creator"/>
        <form:textarea path="creator"/></form:label>

    <form:label path="currentVersion">Current Version
        <form:errors path="currentVersion"/>
        <form:input path="currentVersion"/></form:label>

    <input type="submit" value="Submit"/>
</form:form>