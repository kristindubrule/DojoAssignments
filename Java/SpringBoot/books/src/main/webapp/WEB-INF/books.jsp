<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="css/styles.css" media="screen" title="no title"  charset="utf-8">

<table>
<c:forEach items="${books}" var="book" varStatus="loop">
    <tr>
        <td>${book.title}</td>
        <td>${book.description}</td>
        <td>${book.language}</td>
        <td>${book.numberOfPages}</td>
        <td><a href="/books/delete/${book.id}">Delete</a></td>
        <td><a href="/books/edit/${book.id}">Edit</a></td>
    </tr>
</c:forEach>
</table>