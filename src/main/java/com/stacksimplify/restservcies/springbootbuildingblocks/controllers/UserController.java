package com.stacksimplify.restservcies.springbootbuildingblocks.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.stacksimplify.restservcies.springbootbuildingblocks.entities.User;
import com.stacksimplify.restservcies.springbootbuildingblocks.exceptions.UserExistsException;
import com.stacksimplify.restservcies.springbootbuildingblocks.exceptions.UserNameNotFoundException;
import com.stacksimplify.restservcies.springbootbuildingblocks.exceptions.UserNotFoundException;
import com.stacksimplify.restservcies.springbootbuildingblocks.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

//Controller
@Api(tags = "User Management RESTful Services", value = "UserController", description = "Controller for User Management Service")
@RestController
@Validated
@RequestMapping(value = "users")
public class UserController {
	
	//Autowire the UserServcie
	@Autowired
	private UserService userService;
	
	//getAllUsers Method
	@ApiOperation(value = "Create a new user")
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE) //define output format
	public List<User> getAllUsers() {
		
		return userService.getAllUsers();
	}
	
	//Create User
	//@RequestBody Annotation
	//@PostMapping Annotation
	@ApiOperation(value = "Create a new user")
	@PostMapping
	public ResponseEntity<Void> createUser(@ApiParam("User information for a new user to be created.")@Valid @RequestBody User user, UriComponentsBuilder builder) {
		try {
			userService.createUser(user);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("/users{id}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		} catch (UserExistsException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
	}
	
	//getUserById
	@GetMapping("/{id}")
	public User getUserById(@PathVariable("id")  @Min(1) Long id) {
		
		try {
			Optional<User> userOptional = userService.getUserById(id);
			return userOptional.get();
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
		
	}
	
	//updateUserById
	@PutMapping("/{id}")
	public User updateUserById(@PathVariable("id") Long id, @RequestBody User user) {
		try {
			return userService.updateUserById(id, user);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
	}
	
	//deleteUserById
	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable("id") Long id) {
		userService.deleteUserById(id);
	}
	
	//getUserByUsername
	@GetMapping("/byusername/{username}")
	public User getUserByUsername(@PathVariable("username") String username) throws UserNameNotFoundException {
		User user = userService.getUserByUsername(username);
		if (user == null)
			throw new UserNameNotFoundException("Username: '" + username + "' not found in User repository");
		return user;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
