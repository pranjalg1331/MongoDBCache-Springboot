package com.example.demo.service;


import com.example.demo.data.domain.DistanceEntity;
import com.example.demo.data.repository.DistanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DistanceService {

    @Autowired
    private DistanceRepository distanceRepository;

   public  Optional<DistanceEntity> getDistanceFromMongo(String origin,String destination){
        return distanceRepository.findByOriginAndDestination(origin,destination);


    }
}
