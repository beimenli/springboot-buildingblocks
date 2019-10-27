package com.stacksimplify.restservcies.springbootbuildingblocks.controllers;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;
import com.stacksimplify.restservcies.springbootbuildingblocks.entities.User;
import com.stacksimplify.restservcies.springbootbuildingblocks.entities.Views;
import com.stacksimplify.restservcies.springbootbuildingblocks.exceptions.UserNotFoundException;
import com.stacksimplify.restservcies.springbootbuildingblocks.services.UserService;

//Controller
@RestController
@Validated
@RequestMapping(value = "/jsonview/users")
public class UserJsonViewController {
	
	//Autowire the UserServcie
	@Autowired
	private UserService userService;
	
	//getUserById - External
	@JsonView(Views.External.class)
	@GetMapping("/external/{id}")
	public Optional<User> getUserById(@PathVariable("id")  @Min(1) Long id) {
		
		try {
			return userService.getUserById(id);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
		
	}
	//getUserById - Internal
	@JsonView(Views.Internal.class)
	@GetMapping("/internal/{id}")
	public Optional<User> getUserById2(@PathVariable("id")  @Min(1) Long id) {
		
		try {
			return userService.getUserById(id);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
		
	}
}
