package com.zaycevImaginaryCompany.glebTask1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.zaycevImaginaryCompany.glebTask1.domain.User;
import com.zaycevImaginaryCompany.glebTask1.service.UserService;

@Controller
public class UserController
{
	@Autowired
	private UserService uService;

	@Value("${validation.user.username.alreadyExists}")
	private String usernameExistsErrorMessage;

	@GetMapping("/")
	public String goToStartingPage(Model model)
	{
		model.addAttribute("name", "Vaaaaaadim");
		return "startpage";
	}

	@GetMapping("/register")
	public String goToRegisterUserPage(Model model)
	{
		System.out.println(usernameExistsErrorMessage);
		model.addAttribute("user", new User());
		return "register";
	}

	@GetMapping("/login")
	public String goToLoginUserPage(Model model)
	{
		return "login";
	}

	@PostMapping("/register")
	public String loginUser(@Valid User user, BindingResult bindingResult, Model model)
	{
		if (bindingResult.hasErrors())
		{
			return "register";
		}
		try
		{
			uService.save(user);
		}
		catch (DataAccessException e)
		{
			model.addAttribute("usernameExistsErrorMessage", usernameExistsErrorMessage);
		}
		return "register";
	}
}
