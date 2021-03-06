<!DOCTYPE html>
<html>
<head>
    <title>Dojo Survey</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>

<form action="/" method="post">

    <table>
        <tr>
            <td>
                <label for="name">Your Name</label>
            </td>
            <td>
                <input type="text" name="name">
            </td>
        </tr>
        <tr>
            <td>
                <label for="location">Dojo Location</label>
            </td>
            <td>
                <select name="location">
                    <option value="valley">Silicon Valley</option>
                    <option value="la">Los Angeles</option>
                    <option value="seattle">Seattle</option>
                    <option value="dallas">Dallas</option>
                </select>
            </td>
        </tr>
        <tr>
            <td><label for="language">Favorite Language</label></td>
            <td>
                <select name="language">
                    <option value="python">Python</option>
                    <option value="csharp">C#</option>
                    <option value="java">Java</option>
                </select>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <p>Comment (optional)</p>
                <p><textarea name="comments" rows="4" cols="50"></textarea></p>
            </td>
        </tr>
    </table>
    <input type="submit" name="submit" value="Submit">
</form>

</body>
</html>
