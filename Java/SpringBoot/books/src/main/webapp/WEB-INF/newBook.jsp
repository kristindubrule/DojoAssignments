<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" href="css/styles.css" media="screen" title="no title"  charset="utf-8">

<form:form method="POST" action="/books/new" modelAttribute="book">
    <form:hidden path="id"/>
    <form:label path="title">Title
        <form:errors path="title"/>
        <form:input path="title"/></form:label>

    <form:label path="description">Description
        <form:errors path="description"/>
        <form:textarea path="description"/></form:label>

    <form:label path="language">Language
        <form:errors path="language"/>
        <form:input path="language"/></form:label>

    <form:label path="numberOfPages">Pages
        <form:errors path="numberOfPages"/>
        <form:input type="number" path="numberOfPages"/></form:label>

    <input type="submit" value="Submit"/>
</form:form>