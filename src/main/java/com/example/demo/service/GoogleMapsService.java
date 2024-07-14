package com.example.demo.service;

import com.example.demo.data.domain.DistanceEntity;
import com.example.demo.data.repository.DistanceRepository;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

import java.util.Map;

@Service
public class GoogleMapsService {

    @Autowired
    private RestTemplate restTemplate;

    private JSONObject jsonObject ;

    @Autowired
    private DistanceRepository distanceRepository;

//    public GoogleMapsService(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }

    public DistanceEntity getDistance(String origin,String destination) {
        if (origin == null || origin.isEmpty() || destination == null || destination.isEmpty()) {
            throw new IllegalArgumentException("Origin and destination must not be null or empty");
        }
        String url = String.format(
                "https://maps.googleapis.com/maps/api/distancematrix/json?origins=%s&destinations=%s&key=%s",
                origin, destination, "AIzaSyCWcvgvKYyNdJa3MQp9DBr0gGHAeIMCxcw"
        );
        try{
            String hello= restTemplate.getForObject(url, String.class);
            JSONObject obj= new JSONObject(hello);
            JSONArray rows = obj.getJSONArray("rows");
            JSONObject elements = rows.getJSONObject(0).getJSONArray("elements").getJSONObject(0);
            JSONObject distance = elements.getJSONObject("distance");
            int distanceValue = distance.getInt("value");
            DistanceEntity distanceEntity=new DistanceEntity(origin,destination,distanceValue);
            distanceRepository.save(distanceEntity);
            return distanceEntity;
        }catch(Exception e){
            throw new RuntimeException();
        }


    }
}
