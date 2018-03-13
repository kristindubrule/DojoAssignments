<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Ninja Gold</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <link rel="stylesheet" href="css/styles.css" media="screen" title="no title"  charset="utf-8">
</head>
<body>
<div id="wrapper">
    <div id="mainbox">
        <h1>Earn Gold!</h1>
        <p id="goldcount">Your Gold: ${gold}</p>
        <div id="buildings">
            <div class="building">
                <form action="/process_money" method="post">
                    <h3>Farm</h3>
                    <p>Earns 10-20 golds</p>
                    <input type="hidden" name="building" value="farm" />
                    <input type="submit" value="Find Gold!"/>
                </form>
            </div>
            <div class="building">
                <h3>Cave</h3>
                <p>Earns 5-10 golds</p>
                <form action="/process_money" method="post">
                    <input type="hidden" name="building" value="cave" />
                    <input type="submit" value="Find Gold!"/>
                </form>
            </div>
            <div class="building">
                <h3>House</h3>
                <p>Earns 2-5 golds</p>
                <form action="/process_money" method="post">
                    <input type="hidden" name="building" value="house" />
                    <input type="submit" value="Find Gold!"/>
                </form>
            </div>
            <div class="building">
                <h3>Casino</h3>
                <p>Earns/takes 0-50 golds</p>
                <form action="/process_money" method="post">
                    <input type="hidden" name="building" value="casino" />
                    <input type="submit" value="Find Gold!"/>
                </form>
            </div>
        </div>
        <div id="activity_box">
            <p>Activities</p>
            <div id="activities">
                <p>
                    <c:forEach var="message" items="${history}">
                        <span class="${message.getResult()}">${message.getTime()} - ${message.getText()}</span>
                        </c:forEach>
            </div>
        </div>
        <form action = "/reset" method="post">
            <input type="submit" value="Reset" id="reset">
        </form>
    </div>
</div>
</body>
</html>