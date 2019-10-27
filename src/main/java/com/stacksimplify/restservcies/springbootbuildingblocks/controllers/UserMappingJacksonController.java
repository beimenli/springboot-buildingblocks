package com.stacksimplify.restservcies.springbootbuildingblocks.controllers;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.stacksimplify.restservcies.springbootbuildingblocks.entities.User;
import com.stacksimplify.restservcies.springbootbuildingblocks.exceptions.UserNotFoundException;
import com.stacksimplify.restservcies.springbootbuildingblocks.services.UserService;

@RestController
@RequestMapping(value="/jacksonfiler/users")
@Validated
public class UserMappingJacksonController {
	
	//Autowire the UserServcie (copied from UserController.java)
	@Autowired
	private UserService userService;

	//getUserById (copied from UserController.java)
	@GetMapping("/{id}")
	public MappingJacksonValue getUserById(@PathVariable("id")  @Min(1) Long id) {
		
		try {
			Optional<User> userOptional = userService.getUserById(id);
			User user = userOptional.get();
			
			Set<String> fields = new HashSet<String>();
			fields.add("userid");
			fields.add("username");
			fields.add("ssn");
			fields.add("orders");
			
			FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter", 
					SimpleBeanPropertyFilter.filterOutAllExcept(fields));
			MappingJacksonValue mapper = new MappingJacksonValue(user);
			
			mapper.setFilters(filterProvider);
			return mapper;
			
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
		
	}

}
