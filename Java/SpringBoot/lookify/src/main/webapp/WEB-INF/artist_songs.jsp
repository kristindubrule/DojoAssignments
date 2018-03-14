<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" href="css/styles.css" media="screen" title="no title"  charset="utf-8">

<p>Songs by artist ${search}</p>
<form action="/search" method="get">
    <input type="text" name="search" value="${search}">
    <input type="submit" name="submit" value="New Search">
</form>

<a href="/dashboard">Dashboard</a>

${errors}

<table>
    <c:forEach items="${songs}" var="song">
        <tr><td><a href="/songs/${song.id}">${song.title}</a></td><td>${song.artist}</td><td>${song.rating}</td>
            <td><a href="/songs/delete/${song.id}">Delete</a></td></tr>
    </c:forEach>
</table>
