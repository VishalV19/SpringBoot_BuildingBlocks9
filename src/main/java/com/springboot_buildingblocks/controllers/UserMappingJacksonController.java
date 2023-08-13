package com.springboot_buildingblocks.controllers;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.springboot_buildingblocks.entities.User;
import com.springboot_buildingblocks.exceptions.UserNotFoundException;
import com.springboot_buildingblocks.services.UserService;

import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/jacksonfilter/users")
@Validated
public class UserMappingJacksonController {

	@Autowired // Autowird the UserService
	UserService userService;

	@GetMapping("/{id}") // Chatper 72 - fields with hashset
	// MappingJacksonValue -> Java pre defined class
	public MappingJacksonValue getUserById(@PathVariable("id") @Min(1) Long id) {

		try {
			Optional<User> userOptional = userService.getUserById(id);
			User user = userOptional.get();

			Set<String> fields = new HashSet<String>();
			fields.add("id");
			fields.add("username");
			fields.add("ssn");

			FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter",
					SimpleBeanPropertyFilter.filterOutAllExcept(fields));

			MappingJacksonValue mapper = new MappingJacksonValue(user);
			mapper.setFilters(filterProvider);
			return mapper;

		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}

	}

	@GetMapping("/param/{id}") // Chatper 73 - fields with @RequestParam
	// MappingJacksonValue -> Java pre defined class
	public MappingJacksonValue getUserById2(@PathVariable("id") @Min(1) Long id, @RequestParam Set<String> fields) {

		try {
			Optional<User> userOptional = userService.getUserById(id);
			User user = userOptional.get();
			FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter",
					SimpleBeanPropertyFilter.filterOutAllExcept(fields));

			MappingJacksonValue mapper = new MappingJacksonValue(user);
			mapper.setFilters(filterProvider);
			return mapper;

		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}

	}

}
