<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" href="css/styles.css" media="screen" title="no title"  charset="utf-8">

<h1>${dojo.name} Location Ninjas</h1>

<table>
    <c:forEach items="${dojo.ninjas}" var="ninja">
        <tr>
            <td>${ninja.firstName}</td>
            <td>${ninja.lastName}</td>
            <td>${ninja.age}</td>
        </tr>
    </c:forEach>
</table>
