package com.springboot_buildingblocks.hellow;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
public class HelloWorldController {
	
	@Autowired
	private ResourceBundleMessageSource messageSource; // Java predefined class

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
	
	@GetMapping("/helloworld-int") // Chapter - 69
	public String getMessagesInI18NFormat(@RequestHeader(name = "Accept-Language", required = false) String locale) {
		return messageSource.getMessage("label.hellow", null, new Locale(locale));
	}
	
	@GetMapping("/helloworld-int2") // Chapter - 69
	public String getMessagesInI18NFormat2(){
		return messageSource.getMessage("label.hellow", null, LocaleContextHolder.getLocale());
	}
}
