package com.springboot_buildingblocks.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.springboot_buildingblocks.entities.User;
import com.springboot_buildingblocks.entities.UserExitsException;
import com.springboot_buildingblocks.exceptions.UserNotFoundException;
import com.springboot_buildingblocks.repositories.UserRepository;

//service

@Service
public class UserService {
	
	// Autowired UserRepositry
	@Autowired
	UserRepository userRepository;
	
	// ----------------- getAllUser method -----------------------
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	// ----------------------------------------------------
	
	//createUser method
	public User createUser(User user) throws UserExitsException {
		// if User exit using userName
		User exitingUser = userRepository.findByUsername(user.getUsername());
		if(exitingUser != null) {
			throw new UserExitsException("User already exits in repository");
		}
				
		return userRepository.save(user);
	}
	
	//--------------- getUserById method----------------
	
/*	public Optional<User> getUserById(Long id) {
		Optional<User> user = userRepository.findById(id);
		return user;
	} */
	
	public Optional<User> getUserById(Long id) throws UserNotFoundException{
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("User not found is user repository");
		}
		
		return user;
	}
	
// ------------------------------------------------------------	
	//updateUserById method
	
/*	public User updateUserByid(User user, Long id){
		user.setId(id);
		return userRepository.save(user);
	} */
	
	
	public User updateUserByid(User user, Long id) throws UserNotFoundException{
		Optional<User> Optionaluser = userRepository.findById(id);
		if(!Optionaluser.isPresent()) {
			throw new UserNotFoundException("User not found is user repository, provide the corret user Id");
		}
		
		user.setId(id);
		return userRepository.save(user);
	}
	
	
	//----------------------------------------------------------
	
	//deleteById
	
	public void deleteUserById(Long id){ 
		
		Optional<User> Optionaluser = userRepository.findById(id);
		if(!Optionaluser.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User not found is user repository, provide the corret user Id");
		}	
		userRepository.deleteById(id);
	}
	
	
	// findByUsername method
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	
	
}
