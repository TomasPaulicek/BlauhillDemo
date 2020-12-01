package com.blauhill.mongodb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blauhill.mongodb.model.Property;
import com.blauhill.mongodb.model.Regression;
import com.blauhill.mongodb.repository.PropertyRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class PropertyController {

  @Autowired
  PropertyRepository propertyRepository;

  @GetMapping("/properties")
  public ResponseEntity<List<Property>> getAllProperties() {
    try {
      List<Property> properties = new ArrayList<Property>();

      propertyRepository.findAll().forEach(properties::add);

      if (properties.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(properties, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/properties/{id}")
  public ResponseEntity<Property> getPropertyById(@PathVariable("id") String id) {
    Optional<Property> propertyData = propertyRepository.findById(id);

    if (propertyData.isPresent()) {
      return new ResponseEntity<>(propertyData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/properties")
  public ResponseEntity<Property> createProperty(@RequestBody Property property) {
    try {
      Property _property = propertyRepository.save(new Property(property.getGrLivArea(), property.getSalePrice()));
      return new ResponseEntity<>(_property, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/properties/{id}")
  public ResponseEntity<Property> updateProperty(@PathVariable("id") String id, @RequestBody Property property) {
    Optional<Property> propertyData = propertyRepository.findById(id);

    if (propertyData.isPresent()) {
      Property _property = propertyData.get();
      _property.setGrLivArea(property.getGrLivArea());
      _property.setSalePrice(property.getSalePrice());
      return new ResponseEntity<>(propertyRepository.save(_property), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/properties/{id}")
  public ResponseEntity<HttpStatus> deleteProperty(@PathVariable("id") String id) {
    try {
      propertyRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/properties")
  public ResponseEntity<HttpStatus> deleteAllProperties() {
    try {
      propertyRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/regression")
  public ResponseEntity<Regression> calculateRegression() {
    try {
    	
      List<Property> properties = propertyRepository.findAll();
            
      if (properties.isEmpty()) {
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      
      Regression regression = new Regression(properties);
      
      return new ResponseEntity<Regression>(regression, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
