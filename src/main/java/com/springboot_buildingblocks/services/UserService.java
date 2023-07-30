package com.springboot_buildingblocks.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot_buildingblocks.entities.User;
import com.springboot_buildingblocks.repositories.UserRepository;

//service

@Service
public class UserService {
	
	// Autowired UserRepositry
	@Autowired
	UserRepository userRepository;
	
	//getAllUser method
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	//createUser method
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	// getUserById method
	
	public Optional<User> getUserById(Long id) {
		Optional<User> user = userRepository.findById(id);
		return user;
	}
	
	//updateUserById method
	
	public User updateUserByid(User user, Long id){
		user.setId(id);
		return userRepository.save(user);
	}
	
	//deleteById
	
	public void deleteUserById(Long Id) {
		if(userRepository.findById(Id).isPresent()) {
			userRepository.deleteById(Id);
		}
	}
	
	// findByUsername method
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	
	
}
