<%--
  Created by IntelliJ IDEA.
  User: kristinf
  Date: 3/7/18
  Time: 2:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Checkerboard</title>
    <link rel="stylesheet" href="static/css/styles.css" media="screen" title="no title"  charset="utf-8">
</head>
<body>
    <% int height = Integer.valueOf(request.getParameter("height")); %>
    <% int width = Integer.valueOf(request.getParameter("width")); %>

    <% for(int i = 0; i < height; i++) { %>
        <% for(int j = 0; j < width; j++) { %>
            <% if ((i+j)%2 == 0) { %> <div class="red square"></div>
            <% } else { %> <div class="black square"></div>
            <% } %>
        <% } %>
        <br/>
    <% } %>
</body>
</html>
