package com.zaycevImaginaryCompany.glebTask1.controller;

import java.util.HashSet;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.zaycevImaginaryCompany.glebTask1.domain.User;
import com.zaycevImaginaryCompany.glebTask1.security.SecurityService;
import com.zaycevImaginaryCompany.glebTask1.service.UserService;

@Controller
public class UserController
{
	@Autowired
	private UserService uService;
	
	@Autowired
	private SecurityService securityService;

	@Value("${validation.user.username.alreadyExists}")
	private String usernameExistsErrorMessage;
	
	@GetMapping("/")
	public String goToStartingPage(Model model)
	{
		return "startpage";
	}

	@GetMapping("/register")
	public String goToRegisterUserPage(Model model)
	{
		model.addAttribute("user", new User());
		return "register";
	}

	@PostMapping("/register")
	public String registerUser(@Valid User user, BindingResult bindingResult, Model model)
	{
		if (bindingResult.hasErrors())
		{
			return "register";
		}
		if (!uService.save(user))
		{
			model.addAttribute("usernameExistsErrorMessage", usernameExistsErrorMessage);
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
