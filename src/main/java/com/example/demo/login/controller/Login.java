package com.example.demo.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Login {

	 @RequestMapping("/login")
	    public String loginPage() {
		 
		 return "login";
	    	
	    }
	 @RequestMapping("/logout-success")
	    public String logout() {
	    	return "logout";
	    }
}
