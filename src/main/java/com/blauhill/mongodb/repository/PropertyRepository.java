package com.blauhill.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.blauhill.mongodb.model.Property;

public interface PropertyRepository extends MongoRepository<Property, String> {

}
