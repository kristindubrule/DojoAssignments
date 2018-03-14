<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" href="css/styles.css" media="screen" title="no title"  charset="utf-8">

<div id="wrapper">
<div class="error">${errors}</div>

<table>
<c:forEach items="${languages}" var="language" varStatus="loop">
    <tr>
        <td><a href="/languages/${language.id}">${language.name}</a></td>
        <td>${language.creator}</td>
        <td>${language.currentVersion}</td>
        <td><a href="/languages/delete/${language.id}">Delete</a></td>
        <td><a href="/languages/update/${language.id}">Edit</a></td>
    </tr>
</c:forEach>
</table>

<form:form method="POST" action="/languages/new" modelAttribute="language">
    <div class="element">
    <form:label path="name">Title
        <form:errors path="name"/>
        <form:input path="name"/></form:label>
    </div>
    <div class="element">
    <form:label path="creator">Creator
        <form:errors path="creator"/>
        <form:input path="creator"/></form:label>
    </div>
    <div class="element">
    <form:label path="currentVersion">Current Version
        <form:errors path="currentVersion"/>
        <form:input path="currentVersion"/></form:label>
    </div>
    <input type="submit" value="Submit"/>
</form:form>
</div>