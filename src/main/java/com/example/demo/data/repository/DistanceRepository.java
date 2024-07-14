package com.example.demo.data.repository;

import com.example.demo.data.domain.DistanceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface DistanceRepository extends MongoRepository<DistanceEntity,Long> {

    @Override
    Optional<DistanceEntity> findById(Long aLong);


    Optional<DistanceEntity> findByOriginAndDestination(String origin,String destination);

}
