<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="css/styles.css" media="screen" title="no title"  charset="utf-8">

<table>
<c:forEach items="${languages}" var="language" varStatus="loop">
    <tr>
        <td><a href="/languages/${loop.index}">${language.name}</a></td>
        <td>${language.creator}</td>
        <td>${language.currentVersion}</td>
        <td><a href="/languages/delete/${loop.index}">Delete</a></td>
        <td><a href="/languages/update/${loop.index}">Edit</a></td>
    </tr>
</c:forEach>

    <a href="/languages/new">Add New Language</a>
</table>