<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" href="/css/styles.css" media="screen" title="no title"  charset="utf-8">

<h2>What is your question?</h2>

<form:form method="POST" action="/question/new" modelAttribute="question">
<div class="element">
    <form:label path="content">Question
        <form:errors path="content"/>
        <form:input path="content"/></form:label>
</div>
    <div class="element">
        <form:label path="tags">Tags
            <form:errors path="tags"/>
            <form:input path="tags" value="${question.tagString()}"/></form:label>
    </div>
<input type="submit" name="submit" value="Submit"/>
</form:form>

