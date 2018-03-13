<!DOCTYPE html>
<html>
<head>
    <title>Results</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>

<h3>Submitted Info</h3>
<div id="results">
    <table>
        <tr><td><span class="label">Name</span></td><td>${surveyresponse.getName()}</td></tr>
        <tr><td><span class="label">Favorite Language</span></td><td> ${surveyresponse.getLanguage()}</td></tr>
        <tr><td><span class="label">Dojo Location</span></td><td> ${surveyresponse.getLocation()}</td></tr>
        <tr><td><span class="label">Comments</span></td><td>${surveyresponse.getComments()}</td></tr>
    </table>
</div>
</body>
</html>
