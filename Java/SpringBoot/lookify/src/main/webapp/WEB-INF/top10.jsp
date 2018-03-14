<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" href="css/styles.css" media="screen" title="no title"  charset="utf-8">

<p>Top Ten Songs:</p>
<a href="/dashboard">Dashboard</a>

${errors}

<table>
    <c:forEach items="${songs}" var="song">
        <tr>
            <td>${song.rating} - <a href="/songs/${song.id}">${song.title}</a> - ${song.artist}</td>
    </c:forEach>
</table>
