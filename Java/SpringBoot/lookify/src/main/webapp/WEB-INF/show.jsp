<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" href="css/styles.css" media="screen" title="no title"  charset="utf-8">

<a href="/dashboard">Dashboard</a>

<table>
    <tr><td>${song.title}</td></tr>
    <tr><td>${song.artist}</td></tr>
    <tr><td>${song.rating}</td></tr>
</table>

<a href="/songs/delete/${song.id}">Delete</a>
