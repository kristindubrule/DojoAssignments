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
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Maps {
    @RequestMapping(value= {"/", "/home"})
    public String home(Model model) throws ApiException, InterruptedException, IOException {
        String apiKey = System.getenv("GOOGLE_API_KEY");
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

    public LatLng getCurrentLocation() {
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

        return curLocation.location;
    }

    @RequestMapping(value = "/maps/test", method=RequestMethod.GET,
            produces="application/json")
    @ResponseBody
    public HashMap<String,Object> testGet(Model model) {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(System.getenv("GOOGLE_API_KEY"))
                .build();
        GeolocationPayload payload = new GeolocationPayload();

        LatLng curLocation = getCurrentLocation();

//        // Get restaurants nearby
//        PlacesSearchResponse searchResp = null;
//        NearbySearchRequest search = new NearbySearchRequest(context).type(PlaceType.RESTAURANT)
//                .radius(8000).location(curLocation);
//        try {
//            searchResp = search.await();
//        } catch (Exception e) {
//            System.out.println(e);
//        }

//        String pageToken = searchResp.nextPageToken;
//        while (pageToken != null) {
//            for (PlacesSearchResult psr : searchResp.results) {
//                System.out.println(psr.name);
//            }
//            try {
//                Thread.sleep(1000);
//                NearbySearchRequest nextSearch = new NearbySearchRequest(context).pageToken(pageToken);
//                searchResp = nextSearch.pageToken(pageToken).await();
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//            pageToken = searchResp.nextPageToken;
//        }

//        // Find directions between first restaurant & current location
//        DirectionsApiRequest directionsApiRequest = new DirectionsApiRequest(context).mode(TravelMode.TRANSIT).origin(curLocation)
//                .destination(searchResp.results[0].geometry.location);
//        DirectionsResult dirResult = null;
//        try {
//            dirResult = directionsApiRequest.await();
//        } catch (Exception e) {
//            System.out.println(e);
//        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("lat",curLocation.lat);
        map.put("lon",curLocation.lng);
        return map;
    }

    @RequestMapping(value="/maps/search", method=RequestMethod.GET,
            produces="application/json")
    @ResponseBody
    public HashMap<String,Object> search(@RequestParam("search") String searchParam) {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(System.getenv("GOOGLE_API_KEY"))
                .build();
        GeolocationPayload payload = new GeolocationPayload();

        LatLng curLocation = getCurrentLocation();

        // Get restaurants nearby
        PlacesSearchResponse searchResp = null;
        NearbySearchRequest search = new NearbySearchRequest(context).type(PlaceType.RESTAURANT)
                .radius(8000).location(curLocation).keyword(searchParam);
        try {
            searchResp = search.await();
        } catch (Exception e) {
            System.out.println(e);
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("restaurants",searchResp.results);
        return map;
    }

    public ArrayList<PlacesSearchResult> filterLocations (PlacesSearchResult[] results, LatLng curLocation) {
        ArrayList<PlacesSearchResult> filteredResults = new ArrayList<>();
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(System.getenv("GOOGLE_API_KEY"))
                .build();
        int counter = 0;
        for (PlacesSearchResult result : results) {
            if (counter < 4) {
                // Find directions between first restaurant & current location
                DirectionsApiRequest directionsApiRequest = new DirectionsApiRequest(context).mode(TravelMode.TRANSIT).origin(curLocation)
                        .destination(result.geometry.location);
                DirectionsResult dirResult = null;
                try {
                    dirResult = directionsApiRequest.await();

                } catch (Exception e) {
                    System.out.println(e);
                }
                counter++;
            }
        }
        return filteredResults;
    }
}
