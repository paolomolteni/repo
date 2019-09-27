package it.paolomolteni.loginapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
	
	/**
	 * 
	 */
	@Autowired
	private MyUserService userService;

	@GetMapping(path = "/")
	public String index() {
	    return "external";
	}
	     
	@GetMapping(path = "/internal")
	public String customers(Model model) {
		System.out.println(userService.getUserLogged().toString());
		return "internal";
	}
	
}
