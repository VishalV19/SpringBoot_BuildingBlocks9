package com.springboot_buildingblocks.controllers;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot_buildingblocks.dtos.UserDtoV1;
import com.springboot_buildingblocks.dtos.UserDtoV2;
import com.springboot_buildingblocks.dtos.UserMmDto;
import com.springboot_buildingblocks.entities.User;
import com.springboot_buildingblocks.exceptions.UserNotFoundException;
import com.springboot_buildingblocks.services.UserService;

import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/Versioning/uri/users")
public class UserUriVersioningController {
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired // Autowird the UserService 
	UserService userService;
	
	
	//URI based Versioning - V1
	
	@GetMapping({"/v1.0/{id}", "/v1.1/{id}"}) // Chatper 86
	public UserDtoV1 getUserById(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException{
		
			Optional<User> userOptional = userService.getUserById(id);
				if(!userOptional.isPresent()) {
					throw new UserNotFoundException("user not found");
				}
				
				User user = userOptional.get();
				UserDtoV1 userDtoV1 = modelMapper.map(user, UserDtoV1.class);
				return userDtoV1;
	
	}
	
	
	//URI based Versioning - V2
	
	@GetMapping("/v2.0/{id}") // Chatper 86
	public UserDtoV2 getUserById2(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException{
		
			Optional<User> userOptional = userService.getUserById(id);
				if(!userOptional.isPresent()) {
					throw new UserNotFoundException("user not found");
				}
				
				User user = userOptional.get();
				UserDtoV2 userDtoV2 = modelMapper.map(user, UserDtoV2.class);
				return userDtoV2;
	
	}

}
