<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" href="/css/styles.css" media="screen" title="no title"  charset="utf-8">

<h1>New Product</h1>
${errors}
<form:form method="POST" action="/product/new" modelAttribute="product">
    <div class="element">
        <form:label path="name">Name
            <form:errors path="name"/>
            <form:input path="name"/></form:label>
    </div>
    <div class="element">
        <form:label path="description">Description
            <form:errors path="description"/>
            <form:input path="description"/></form:label>
    </div>
    <div class="element">
        <form:label path="price">Price
            <form:errors path="price"/>
            <form:input path="price"/></form:label>
    </div>

    <input type="submit" name="submit" value="Create"/>
</form:form>