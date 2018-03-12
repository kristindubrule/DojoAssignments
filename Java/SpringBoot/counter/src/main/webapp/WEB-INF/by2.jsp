<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<h2>Welcome user! This page increments the count by 2 each visit.</h2>

<a href="/Counter">Counter</a>

<form action="/reset">
    <input type="submit" name="submit" value="Reset">
</form>