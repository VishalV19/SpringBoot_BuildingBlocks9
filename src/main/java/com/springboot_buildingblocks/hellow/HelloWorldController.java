package com.springboot_buildingblocks.hellow;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
public class HelloWorldController {

		// Simple method
	// URI - /helloworld
	// Get
	
	//@RequestMapping(method = RequestMethod.GET, path= "/helloworld")
	@GetMapping("/helloworld1")
	public String helleWorld() {
		
		return "Hellow World";
	}
	
	@GetMapping("/helloworld-bean")
	public UserDetails helloWorld() {
		
		return new UserDetails("Vishal", "Singh", "Boston");
	}
}
