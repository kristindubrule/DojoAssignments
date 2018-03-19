<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" href="/css/styles.css" media="screen" title="no title"  charset="utf-8">

<h2>Questions Dashboard</h2>

<table>
    <th>Question</th><th>Tags</th>
    <c:forEach items="${questions}" var="question">
        <tr>
            <td><a href="/question/${question.id}">${question.content}</a></td>
            <td>${question.tagString()}</td>
        </tr>
    </c:forEach>
</table>

<a href="/question/new">New Question</a>