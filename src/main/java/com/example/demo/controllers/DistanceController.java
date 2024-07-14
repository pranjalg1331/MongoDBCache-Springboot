package com.example.demo.controllers;

import com.example.demo.data.domain.DistanceEntity;
import com.example.demo.service.DistanceService;
import com.example.demo.service.GoogleMapsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class DistanceController {

    @Autowired
    private GoogleMapsService googleMapsService;
    @Autowired
    private DistanceService distanceService;

    @GetMapping("/distance")
    public DistanceEntity getDistance(@RequestParam String origin, @RequestParam String destination) {

        Optional<DistanceEntity> distanceEntity= distanceService.getDistanceFromMongo(origin,destination);
        if(distanceEntity.isPresent()){
            return distanceEntity.get();
        }else{
            return googleMapsService.getDistance(origin,destination);
        }

    }
}
