package com.blauhill.mongodb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blauhill.mongodb.model.Property;
import com.blauhill.mongodb.repository.PropertyRepository;

@Service
@Transactional
public class PropertyService {

	@Autowired
	private PropertyRepository propertyRepository;
	
	public List<Property> listAll() {
		return propertyRepository.findAll();
	}
	
	public void save(Property property) {
		propertyRepository.save(property);
	}
	
	public Property get(String id) {
		return propertyRepository.findById(id).get();
	}
	
	public void delete(String id) {
		propertyRepository.deleteById(id);
	}
}
