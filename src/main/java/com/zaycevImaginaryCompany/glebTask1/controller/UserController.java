package com.zaycevImaginaryCompany.glebTask1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController
{
	@GetMapping("/")
	public String goToStartingPage(Model model)
	{
		model.addAttribute("name", "Vaaaaaadim");
		return "startpage";
	}
	
	@GetMapping("/register")
	public String goToRegisterUserPage(Model model)
	{
		return "register";
	}
	
	@GetMapping("/login")
	public String goToAuthentificateUser(Model model)
	{
		return "login";
	}
}
