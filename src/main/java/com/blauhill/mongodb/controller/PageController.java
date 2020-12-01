package com.blauhill.mongodb.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.blauhill.mongodb.model.Property;
import com.blauhill.mongodb.model.Regression;
import com.blauhill.mongodb.repository.PropertyRepository;
import com.blauhill.mongodb.service.PropertyService;

@CrossOrigin(origins = "http://localhost:8081")
@Controller
public class PageController {
	
	@Autowired
	PropertyRepository propertyRepository;
	
	@Autowired
	PropertyService service;

	@RequestMapping("/")
	public String viewHomePage(Map<String, Object> model) {
		List<Property> properties = propertyRepository.findAll();
		Regression regression = new Regression(properties);
        model.put("properties", properties);
        model.put("slope", regression.slope);
        model.put("intercept", regression.intercept);
		return "index";
	}
		
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProperty(@ModelAttribute("property") Property property) {
		service.save(property);
		return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditPropertyPage(@PathVariable(name = "id") String id) {
		ModelAndView mav = new ModelAndView("edit_property");
		Property property = service.get(id);
		mav.addObject("property", property);
		return mav;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteProperty(@PathVariable(name = "id") String id) {
		service.delete(id);
		return "redirect:/";		
	}
	
}
