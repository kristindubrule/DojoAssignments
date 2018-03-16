<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" href="/css/styles.css" media="screen" title="no title"  charset="utf-8">

<h1>${product.name}</h1>

<h3>Categories</h3>

<ul>
    <c:forEach items="${product.categories}" var="category">
        <li>${category.name}</li>
    </c:forEach>
</ul>

<p>Add Category:</p>
<form method="POST" action="/product/${product.id}/category">
        <select name="categoryId">
            <c:forEach items="${categories}" var="category">
                <option value="${category.id}">${category.name}</option>
            </c:forEach>
        </select>
    <input type="submit" name="submit" value="Add"/>
</form>
