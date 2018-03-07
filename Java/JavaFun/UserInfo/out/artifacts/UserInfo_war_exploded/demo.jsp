<%--
  Created by IntelliJ IDEA.
  User: kristinf
  Date: 3/7/18
  Time: 1:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.Date"%>
<html>
<head>
    <title>Demo JSP</title>
</head>
<body>
    <%!
        public int add(int a, int b) {
            return a+b;
        }
    %>
    <%
        int i = 7;
        int j = 4;
    %>

    <h3><%= add(i,j) %></h3>

    <% for(int index = 0; index < 5; index++) { %>
        <h1><%= index %></h1>
    <% } %>

    <p>The time is: <%= new Date() %></p>
</body>
</html>
