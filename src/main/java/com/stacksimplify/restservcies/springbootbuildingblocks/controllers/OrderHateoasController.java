package com.stacksimplify.restservcies.springbootbuildingblocks.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservcies.springbootbuildingblocks.entities.Order;
import com.stacksimplify.restservcies.springbootbuildingblocks.entities.User;
import com.stacksimplify.restservcies.springbootbuildingblocks.exceptions.UserNotFoundException;
import com.stacksimplify.restservcies.springbootbuildingblocks.repositories.OrderRepository;
import com.stacksimplify.restservcies.springbootbuildingblocks.repositories.UserRepository;

@RestController
@RequestMapping(value = "/hateoas/users")
public class OrderHateoasController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	//get All orders for a user
	@GetMapping("/{userid}/orders")
	public Resources<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException {
		
		Optional<User> userOptional = userRepository.findById(userid);
		if(!userOptional.isPresent())
			throw new UserNotFoundException("User Not Found");
		List<Order> allorders =  userOptional.get().getOrders();
		Resources<Order> finalResources = new Resources<Order>(allorders);
		return finalResources;
	}

}
