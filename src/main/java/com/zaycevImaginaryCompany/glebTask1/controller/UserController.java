package com.zaycevImaginaryCompany.glebTask1.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.zaycevImaginaryCompany.glebTask1.domain.User;

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
		model.addAttribute("user", new User());
		return "register";
	}
	
	@GetMapping("/login")
	public String goToLoginUserPage(Model model)
	{
		return "login";
	}
	
	@PostMapping("/register")
	public String loginUser(@Valid User user, BindingResult bindingResult)
	{
		System.out.println(user);
		if (bindingResult.hasErrors()) 
		{
			System.out.println("Errors number = " + bindingResult.getErrorCount());
		}
		return "register";
	}
}
