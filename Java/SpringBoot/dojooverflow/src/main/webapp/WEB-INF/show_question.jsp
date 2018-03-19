<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" href="/css/styles.css" media="screen" title="no title"  charset="utf-8">

<h2>${answer.question.content}</h2>

Tags:
<c:forEach items="${answer.question.tags}" var="tag">
    <div class="tag">${tag.subject}</div>
</c:forEach>

<table>
    <th>Answers</th>
    <c:forEach items="${answer.question.answers}" var="otheranswer" varStatus="loop">
        <tr>
            <td>${otheranswer.content}</td>
        </tr>
    </c:forEach>
</table>

<form:form method="POST" action="/question/add_answer" modelAttribute="answer">
    <%--Why can't I use just question here?--%>
    <form:hidden path="question.id" value="${answer.question.id}"/>
    <div class="element">
        <form:label path="content">Answer
            <form:errors path="content"/>
            <form:input path="content"/></form:label>
    </div>
    <input type="submit" name="submit" value="Answer"/>
</form:form>

