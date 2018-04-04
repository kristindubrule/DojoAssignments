<!DOCTYPE html>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    <script type="text/javascript" src='http://code.jquery.com/jquery-1.10.2.min.js'></script>
    <script src="//maps.googleapis.com/maps/api/js?key=${apiKey}" type="text/javascript"></script>
    <link rel="stylesheet" href="/css/styles.css" media="screen" title="no title"  charset="utf-8">
</head>
<script>
    var map;

    function initialize() {
        // Try HTML5 geolocation.
        $.get("/maps/test", function (data) {
            $(".result").html(data);
            var mapOptions = {
                center: new google.maps.LatLng(data.lat, data.lon),
                zoom: 13,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            map = new google.maps.Map(
                document.getElementById("map"), mapOptions);
            var marker = new google.maps.Marker({
                position: map.getCenter(),
                map: map
            });
            map.setCenter(mapOptions.center);
        });
    }
    google.maps.event.addDomListener(window, "load", initialize);

    $(document).ready(function(){
        $('button').click(function(){
            console.log($("input[name=search]").val());
            $.ajax({
                url: "/maps/search/",
                type: "get",
                data: {search: $("input[name=search]").val()},
                success: function (data) {
                    console.log($("input[name=search]"));
                    var restaurantStr = "";
                    var restaurants = data.restaurants;
                    for (var i = 0; i < restaurants.length; i++) {
                        restaurantStr += "<tr><td>" + restaurants[i].name + "</td></tr>";
                    }
                    $("#restaurants").html(restaurantStr);
                }
            });
        })
    });
</script>
<body>
<div class="map-container">
    <div id="map">
    </div>
</div>
<div id="search">
    <input type="text" name="search"/>
    <button>Search</button>
</div>
<div class="restaurant-container">
    <table id="restaurants">
        <c:forEach items="restaurants" var="restaurant">
            ${restaurant}
        </c:forEach>
    </table>
</div>
</body>