package com.stacksimplify.restservcies.springbootbuildingblocks.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservcies.springbootbuildingblocks.entities.Order;
import com.stacksimplify.restservcies.springbootbuildingblocks.entities.User;
import com.stacksimplify.restservcies.springbootbuildingblocks.exceptions.UserNotFoundException;
import com.stacksimplify.restservcies.springbootbuildingblocks.repositories.OrderRepository;
import com.stacksimplify.restservcies.springbootbuildingblocks.repositories.UserRepository;

@RestController
@RequestMapping(value = "/users")
public class OrderController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	//get All orders for a user
	@GetMapping("/{userid}/orders")
	public List<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException {
		
		Optional<User> userOptional = userRepository.findById(userid);
		if(!userOptional.isPresent())
			throw new UserNotFoundException("User Not Found");
		return userOptional.get().getOrders();

	}

	//Create Order
	@PostMapping("/{userid}/orders")
	public Order createOrder(@PathVariable Long userid, @RequestBody Order order) throws UserNotFoundException {
		Optional<User> userOptional = userRepository.findById(userid);
		if(!userOptional.isPresent())
			throw new UserNotFoundException("User Not Found");
		User user = userOptional.get();
		order.setUser(user);
		return orderRepository.save(order);
	}

	//get order for a orderid
	@GetMapping("{userid}/orders/{orderid}")
	public Order getOrderByOrderId(@PathVariable Long userid,@PathVariable Long orderid) throws UserNotFoundException  {
		
		Optional <User> userOptional = userRepository.findById(userid);
		if(!userOptional.isPresent())
			throw new UserNotFoundException("User Not Found");

		for (Order t_order : userOptional.get().getOrders() ) {
			if (t_order.getOrderid().equals(orderid) ) {
			System.out.println(t_order.getOrderid());
			return t_order;
			}
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND,  "Order ID:"+ orderid.toString());
	}
}
