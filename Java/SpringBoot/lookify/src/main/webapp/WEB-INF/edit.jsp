<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" href="css/styles.css" media="screen" title="no title"  charset="utf-8">

<form:form method="POST" action="/songs/edit" modelAttribute="song">
    <form:hidden path="id"/>
    <div class="element">
        <form:label path="title">Title
            <form:errors path="title"/>
            <form:input path="title"/></form:label>
    </div>
    <div class="element">
        <form:label path="artist">Artist
            <form:errors path="artist"/>
            <form:input path="artist"/></form:label>
    </div>
    <div class="element">
        <form:label path="rating">Rating
            <form:errors path="rating"/>
            <form:select path="rating">
                <form:option value="1" label="1">1</form:option>
                <form:option value="2" label="2">2</form:option>
                <form:option value="3" label="3">3</form:option>
                <form:option value="4" label="4">4</form:option>
                <form:option value="5" label="5">5</form:option>
                <form:option value="6" label="6">6</form:option>
                <form:option value="7" label="7">7</form:option>
                <form:option value="8" label="8">8</form:option>
                <form:option value="9" label="9">9</form:option>
                <form:option value="10" label="10">10</form:option>
            </form:select>
        </form:label>
    </div>
    <input type="submit" name="submit" value="Add Song">
</form:form>