package com.springboot_buildingblocks.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot_buildingblocks.entities.Order;
import com.springboot_buildingblocks.entities.User;
import com.springboot_buildingblocks.exceptions.UserNotFoundException;
import com.springboot_buildingblocks.repositories.OrderRepository;
import com.springboot_buildingblocks.repositories.UserRepository;

@RestController
@RequestMapping(value = "/hateoas/users")
public class OrderHateaosController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository oderRepository;
	

	@GetMapping(value = "/{userid}/orders") // Chapter 62
	List<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException{
		Optional<User> userOptional = userRepository.findById(userid);
		if(!userOptional.isPresent())
			throw new UserNotFoundException("user not found");
		return userOptional.get().getOrders();
	}
	
	
	
}
