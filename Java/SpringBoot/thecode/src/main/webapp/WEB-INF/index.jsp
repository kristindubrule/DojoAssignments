<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="css/styles.css" media="screen" title="no title"  charset="utf-8">

<head>
    <title>Secret Code</title>
</head>

<body>
    <div class="wrong">${incorrect}</div>
    <form action="/checkcode" method="post">
        <p>What is the code?</p>
        <input type="text" name="code" value="${code}">
        <input type="submit" name="submit" value="Try Code">
    </form>
</body>