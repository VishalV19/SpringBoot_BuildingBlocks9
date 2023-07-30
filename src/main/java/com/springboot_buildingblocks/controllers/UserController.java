package com.springboot_buildingblocks.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot_buildingblocks.entities.User;
import com.springboot_buildingblocks.services.UserService;

//Controller
@RestController
public class UserController {

	@Autowired // Autowird the UserService 
	UserService userService;
	
	
	//getAllUsers method
	@GetMapping("/users")
	public List<User> getAllusers(){
		return userService.getAllUsers();
		
	}
	
	//createUser method
	// @RequestBody Annotation
	// @PostMapping Annotation
	
	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	//getUserById method
	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@PathVariable("id") Long id){
		return userService.getUserById(id);
		
	}
	
	//updateUserById
	@PutMapping("/users/{id}")
	public User updateUserById(@PathVariable("id") Long id, @RequestBody User user) {
		return userService.updateUserByid(user, id);
		
		
	}
	
	//deleteById
	@DeleteMapping("/users/{id}")
	public void deleteById(@PathVariable("id") Long Id) {
		userService.deleteUserById(Id);
	}
	
	//getUserByUsername
	@GetMapping("/users/byusername/{username}")
	public User getUserByUsername(@PathVariable("username") String username) {
		return userService.getUserByUsername(username);
	}
	
}
