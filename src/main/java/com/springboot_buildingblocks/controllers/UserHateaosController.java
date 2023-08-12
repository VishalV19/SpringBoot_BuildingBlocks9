package com.springboot_buildingblocks.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.BasicLinkBuilder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.springboot_buildingblocks.entities.User;
import com.springboot_buildingblocks.exceptions.UserNotFoundException;
import com.springboot_buildingblocks.repositories.UserRepository;
import com.springboot_buildingblocks.services.UserService;

import jakarta.validation.constraints.Min;
import org.springframework.hateoas.Link;


@RestController
@RequestMapping(value = "/hateoas/users")
@Validated
public class UserHateaosController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
//GetUsersById
	
/*	@GetMapping("/{id}") // Chatper 62
	public Optional<User> getUserById(@PathVariable("id") @Min(1) Long id){
		try {
			return userService.getUserById(id);
		}catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
		
	} */
	
	
/*	@GetMapping("/{id}") // Chatper 63 Need to work on it 
	public Optional<User> getUserById(@PathVariable("id") @Min(1) Long id){
		try {
			Optional<User> userOptional = userService.getUserById(id);
			User user = userOptional.get();
			Long userid = user.getId();
			Link selflink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(id).withSelfRel(); 
			user.add(selflink);
			Resource<User> finalResource = new Resource<User>user;
		}catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
		
		
	} */
	
	//---------------------------------------------------------
	
	//getAllUsers method --> Chapter 64 
	
	// Not working due to hateoas dependency issue
	
	/*	@GetMapping
		public List<User> getAllusers(){
		List<User> allUser =  userService.getAllUsers();
			for (User user : allUser) {
				//SelfLink
				Long id = user.getId();
				Link selfLink = ControllerLinkBuilder.(this.getClass()).slash(id).withSelfRel();
				user.add(selfLink);
				
			}
			Resource<User> finalResource = new Resource<User>(allUser);
			return finalResources;
		} */
	
	
}
