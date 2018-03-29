<!DOCTYPE html>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    <script type="text/javascript" src='http://code.jquery.com/jquery-1.10.2.min.js'></script>
    <script src="//maps.googleapis.com/maps/api/js?key=${apiKey}" type="text/javascript"></script>
    <link rel="stylesheet" href="/css/styles.css" media="screen" title="no title"  charset="utf-8">
        <%--<meta name="viewport" content="initial-scale=1.0, user-scalable=no">--%>
        <%--<meta charset="utf-8">--%>
        <%--<style>--%>
            <%--/* Always set the map height explicitly to define the size of the div--%>
             <%--* element that contains the map. */--%>
            <%--#map {--%>
                <%--height: 100%;--%>
            <%--}--%>
            <%--/* Optional: Makes the sample page fill the window. */--%>
            <%--html, body {--%>
                <%--height: 100%;--%>
                <%--margin: 0;--%>
                <%--padding: 0;--%>
            <%--}--%>
        <%--</style>--%>
</head>
<script>
    // Note: This example requires that you consent to location sharing when
    // prompted by your browser. If you see the error "The Geolocation service
    // failed.", it means you probably did not give permission for the browser to
    // locate you.
    function initialize() {
        // Try HTML5 geolocation.
        $.get("/maps/test", function (data) {
            $(".result").html(data);
            var mapOptions = {
                center: new google.maps.LatLng(data.lat, data.lon),
                zoom: 13,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            var map = new google.maps.Map(
                document.getElementById("map"), mapOptions);
            var marker = new google.maps.Marker({
                position: map.getCenter(),
                map: map
            });

            for (var i = 0; i < data.restaurants.length; i++) {
                console.log(data.restaurants[i]);
            }

            map.setCenter(mapOptions.center);
        });
    }
    google.maps.event.addDomListener(window, "load", initialize);
</script>
<div class="map-container">
    <div id="map">
    </div>
</div>
<div class="restaurants">

</div>