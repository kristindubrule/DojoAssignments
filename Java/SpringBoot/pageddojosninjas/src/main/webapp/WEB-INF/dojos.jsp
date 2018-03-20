<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" href="css/styles.css" media="screen" title="no title"  charset="utf-8">


<div id="ninjas">
    <h1>Dojos and Ninjas</h1>

    // for loop to create all the links
    <c:forEach begin="1" end="${totalPages}" var="index">
        <a href="/ninjas/pages/${index}">${index}</a>
    </c:forEach>
    <table class="table">
        <thead>
        <th>Dojo Name</th>
        <th>Ninja First Name</th>
        <th>Ninja Last Name</th>
        </thead>
        <tbody>
        // we have to call the .content method to actually get the ninjas inside the Page iterable.
        <c:forEach var="ninja" items="${ninjas.content}">
            <tr>
                <td>${ninja.dojo.name}</td>
                <td><c:out value="${ninja.firstName}"></c:out></td>
                <td><c:out value="${ninja.lastName}"></c:out></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>