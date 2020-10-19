package com.zaycevImaginaryCompany.task.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.zaycevImaginaryCompany.task.domain.User;
import com.zaycevImaginaryCompany.task.security.SecurityService;
import com.zaycevImaginaryCompany.task.service.UserService;

@Controller
public class UserController
{
	@Autowired
	private UserService uService;
	
	@Autowired
	private SecurityService securityService;
	
	@GetMapping("/")
	public String goToStartingPage(Model model)
	{
		String username = securityService.getLoggedInUsername();
		if (username != null)
		{
			model.addAttribute("userLogged", "userLogged");
		}
		return "startpage";
	}

	@GetMapping("/register")
	public String goToRegisterUserPage(Model model)
	{
		model.addAttribute("user", new User());
		return "register";
	}

	@PostMapping("/register")
	public String registerUser(@Valid User user, BindingResult bindingResult, Model model, HttpServletRequest request)
	{
		if (bindingResult.hasErrors())
		{
			return "register";
		}
		if (!uService.save(user))
		{
			model.addAttribute("usernameExistsErrorMessage", "");
			return "register";
		}
		
		securityService.autoLogin(user.getUsername(), user.getPassword());
		model.addAttribute("user", user);
		
		return "userAccounts";
	}
	
	@GetMapping("/login")
	public String goToLoginUserPage(Model model)
	{
		model.addAttribute("user", new User());
		return "login";
	}
	
}
