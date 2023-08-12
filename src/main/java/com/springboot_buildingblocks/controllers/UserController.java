package com.springboot_buildingblocks.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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

import com.springboot_buildingblocks.entities.User;
import com.springboot_buildingblocks.exceptions.UserExitsException;
import com.springboot_buildingblocks.exceptions.UserNameNotFoundException;
import com.springboot_buildingblocks.exceptions.UserNotFoundException;
import com.springboot_buildingblocks.services.UserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

//Controller
@RestController
@Validated
@RequestMapping(value = "/users")
public class UserController {

	@Autowired // Autowird the UserService 
	UserService userService;
	
	
	//getAllUsers method
	@GetMapping
	public List<User> getAllusers(){
		return userService.getAllUsers();
		
	}
	
	// -----------createUser method ------------------------
	// @RequestBody Annotation
	// @PostMapping Annotation
	
//	@PostMapping("/users")
//	public User createUser(@RequestBody User user) {
//		return userService.createUser(user);
//	}
	
/*	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		try {
			return userService.createUser(user);
		}catch(UserExitsException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
				
	} */
	
	
	@PostMapping // Chapter 37
	public ResponseEntity<Void> createUser(@Valid @RequestBody User user, UriComponentsBuilder builder) {
		try {
			userService.createUser(user);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}catch(UserExitsException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
				
	}
	
	//--------------- getUserById method --------------------
	
/*	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@PathVariable("id") Long id){
		return userService.getUserById(id);
		
	} */
	
/*	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@PathVariable("id") Long id){
		try {
			return userService.getUserById(id);
		}catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
		
	} */
	
	@GetMapping("/{id}") // Chatper 46
	public Optional<User> getUserById(@PathVariable("id") @Min(1) Long id){
		try {
			return userService.getUserById(id);
		}catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
		
	}
	
	
	//---------------------------------------------------------------------------
	
	//updateUserById
/*	@PutMapping("/users/{id}")
	public User updateUserById(@PathVariable("id") Long id, @RequestBody User user) {
		return userService.updateUserByid(user, id);
	} */
	
	
	@PutMapping("/{id}")
	public User updateUserById(@PathVariable("id") Long id, @RequestBody User user) throws Exception{
		try {
			return userService.updateUserByid(user, id);
		}catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
	
	}
	
	
	// ---------------------------------------------------------------------
	
	//deleteById
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable("id") Long Id){
		userService.deleteUserById(Id);
	} 
	
	
	
	
	
	
	// ------------------------------------------------------------------
	

	//getUserByUsername
/*	@GetMapping("/users/byusername/{username}")
	public User getUserByUsername(@PathVariable("username") String username) {
		return userService.getUserByUsername(username);
	} */
	
	
	//Chapter - 45 -: Implement UserNotFoundException in GEH
	@GetMapping("/byusername/{username}")
	public User getUserByUsername(@PathVariable("username") String username) throws UserNameNotFoundException {
		User user = userService.getUserByUsername(username);
		if(user == null) {
			throw new UserNameNotFoundException("username: '" +username+ "'not found in user repository");
			
		}
		return user;
	}
}
