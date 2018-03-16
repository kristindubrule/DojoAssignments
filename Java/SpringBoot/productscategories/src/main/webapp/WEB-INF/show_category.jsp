<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" href="/css/styles.css" media="screen" title="no title"  charset="utf-8">

<h1>${category.name}</h1>

<h3>Products</h3>

<ul>
    <c:forEach items="${category.products}" var="product">
        <li>${product.name}</li>
    </c:forEach>
</ul>

<p>Add Product:</p>
<form method="POST" action="/category/${category.id}/product">
    <select name="productId">
        <c:forEach items="${products}" var="product">
            <option value="${product.id}">${product.name}</option>
        </c:forEach>
    </select>
    <input type="submit" name="submit" value="Add"/>
</form>
