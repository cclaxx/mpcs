package it.polimi.awt.mpcs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@RequestMapping
	public String userHomePage(){
		return "homeUser";
	}
	@RequestMapping(value = "/unregister", method = RequestMethod.GET)
	public String getUnRegistration(){
		return "un-register";
	}
	@RequestMapping(value = "/unregister", method = RequestMethod.POST)
	public String submitUnRegistration(){
		return "home";
	}
	@RequestMapping("/logout")	
	public String logout(){
		return "home";
	}
}
