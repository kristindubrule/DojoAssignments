<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" href="/css/styles.css" media="screen" title="no title"  charset="utf-8">

<form action="/employee/findEmployees" method="post">
    Find employees for: <input type="text" name="managerFirstName"> <input type="text" name="managerLastName">
    <input type="submit" name="submit" value="Submit">
</form>

<form action="/employee/findManager" method="post">
    Find manager for: <input type="text" name="employeeFirstName"> <input type="text" name="employeeLastName">
    <input type="submit" name="submit" value="Submit">
</form>

<c:forEach items="${manager.employees}" var="employee">
    <p>${employee.firstName} ${employee.lastName}</p>
</c:forEach>

<p>${employee.manager.firstName} ${employee.manager.lastName}</p>
