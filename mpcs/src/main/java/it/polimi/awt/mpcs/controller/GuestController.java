package it.polimi.awt.mpcs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GuestController {
	
	@RequestMapping(value = { "/", "/index" })
	public String homePage(){
		return "home";
	}
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String getRegistration(){
		return "register";
	}
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String submitRegistration(){
		return null;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLogin(){
		return "login";
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String submitLogin(){
		return null;
	}
}
