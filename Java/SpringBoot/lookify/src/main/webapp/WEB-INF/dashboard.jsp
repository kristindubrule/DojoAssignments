<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" href="css/styles.css" media="screen" title="no title"  charset="utf-8">

<a href="/songs/new">Add New</a>
<a href="/search/topTen">Top Songs</a>

${errors}

<form action="/search" method="get">
    <input type="text" name="search" value="${search}">
    <input type="submit" name="submit" value="Search Artists">
</form>

<table>
<c:forEach items="${songs}" var="song">
    <tr><td><a href="/songs/${song.id}">${song.title}</a></td><td>${song.artist}</td><td>${song.rating}</td>
    <td><a href="/songs/delete/${song.id}">Delete</a></td></tr>
</c:forEach>
</table>
