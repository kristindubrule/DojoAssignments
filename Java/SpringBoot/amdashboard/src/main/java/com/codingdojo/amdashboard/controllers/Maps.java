package com.codingdojo.amdashboard.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.*;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.http.HttpResponse;
import java.io.IOException;
import java.util.HashMap;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class Maps {
    @RequestMapping(value= {"", "home"})
    public String home(Model model) throws ApiException, InterruptedException, IOException {
        String apiKey = System.getenv("GOOGLE_API_KEY");
//        HttpClient httpClient = HttpClientBuilder.create().build();
//
//        HttpPost request = new HttpPost("https://www.googleapis.com/geolocation/v1/geolocate?key="+apiKey);
//
//        HttpResponse response = null;
//        try {
//            response = httpClient.execute(request);
//        } catch (IOException e) {
//            System.out.println(e);
//        }
//        System.out.println(response.getStatusLine().getStatusCode());

        GeoApiContext context = new GeoApiContext.Builder()
        .apiKey(apiKey)
        .build();

        GeolocationPayload payload = new GeolocationPayload();

        //GeolocationResult[] results = GeolocationApi.geolocate(context, );

        model.addAttribute("apiKey",apiKey);
        return "index";
    }

    @RequestMapping("address")
    public String testAddress(Model model) {
        //        String payload = "data={" +
//                "key: "+apiKey+
//                "}";
//        StringEntity entity = new StringEntity(payload,
//                ContentType.APPLICATION_FORM_URLENCODED);
//        request.setEntity(entity);

//        GeoApiContext context = new GeoApiContext.Builder()
//                .apiKey(apiKey)
//                .build();
//        GeocodingResult[] results =  GeocodingApi.geocode(context,
//                "1600 Amphitheatre Parkway Mountain View, CA 94043").await();
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        System.out.println(gson.toJson(results[0].addressComponents));
        return "index";
    }

    @RequestMapping(value = "/maps/test", method=RequestMethod.GET,
            produces="application/json")
    @ResponseBody
    public HashMap<String,Object> testGet(Model model) {

        // Get current location
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(System.getenv("GOOGLE_API_KEY"))
                .build();
        GeolocationPayload payload = new GeolocationPayload();


        GeolocationResult curLocation = null;
        try {
            curLocation = GeolocationApi.geolocate(context, payload).await();
        } catch (Exception e) {
            System.out.println(e);
        }

        // Get restaurants nearby
        PlacesSearchResponse searchResp = null;
        NearbySearchRequest search = new NearbySearchRequest(context).type(PlaceType.RESTAURANT)
                .radius(1100).location(curLocation.location);
        try {
            searchResp = search.await();
        } catch (Exception e) {
            System.out.println(e);
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("lat",curLocation.location.lat);
        map.put("lon",curLocation.location.lng);
        map.put("restaurants",searchResp.results);
        return map;
    }
}
