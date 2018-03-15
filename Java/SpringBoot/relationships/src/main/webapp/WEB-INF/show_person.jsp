<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" href="css/styles.css" media="screen" title="no title"  charset="utf-8">

<a href="/persons/new">New Person</a>
<a href="/licenses/new">New License</a>

<table>
    <tr><td>First Name: ${person.firstName}</td></tr>
    <tr><td>Last Name: ${person.lastName}</td></tr>
    <tr><td>License: ${person.license.number}</td></tr>
    <tr><td>License State: ${person.license.state}</td></tr>
    <tr><td>License Expiration: ${person.license.expiration_date}</td></tr>
    <tr><td>Person Create: ${person.createdAt}</td></tr>
    <tr><td>Person Update: ${person.updatedAt}</td></tr>
</table>