package com.springboot_buildingblocks.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot_buildingblocks.entities.Order;
import com.springboot_buildingblocks.entities.User;
import com.springboot_buildingblocks.exceptions.UserNotFoundException;
import com.springboot_buildingblocks.repositories.OrderRepository;
import com.springboot_buildingblocks.repositories.UserRepository;

@RestController
@RequestMapping(value = "/users")
public class OrderController {

	@Autowired 
	private UserRepository userRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@GetMapping(value = "/{userid}/orders") // Chapter 54
	List<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException{
		Optional<User> userOptional = userRepository.findById(userid);
		if(!userOptional.isPresent())
			throw new UserNotFoundException("user not found");
		return userOptional.get().getOrders();
	}
	
	//createOrder
	
	@PostMapping(value = ("{userid}/orders"))  // Chapter 55 
	public Order createOrder(@PathVariable Long userid, @RequestBody Order order) throws UserNotFoundException {
		Optional<User> userOptional = userRepository.findById(userid);
		
		if(!userOptional.isPresent())
			throw new UserNotFoundException("user not found");
		User user = userOptional.get();
		order.setUser(user);
		return orderRepository.save(order);
	}
}
