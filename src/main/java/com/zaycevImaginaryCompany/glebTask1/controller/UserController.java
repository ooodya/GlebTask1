package com.zaycevImaginaryCompany.glebTask1.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.zaycevImaginaryCompany.glebTask1.domain.Account;
import com.zaycevImaginaryCompany.glebTask1.domain.User;
import com.zaycevImaginaryCompany.glebTask1.security.UserPass;
import com.zaycevImaginaryCompany.glebTask1.service.UserService;

@Controller
public class UserController
{
	@Autowired
	private UserService uService;

	@Value("${validation.user.username.alreadyExists}")
	private String usernameExistsErrorMessage;
	
	@Value("${validation.user.doesntExist}")
	private String userDoesntExistErrorMessage;

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
		
		model.addAttribute("user", user);
		model.addAttribute("accounts", new HashSet<>());
		return "userAccounts";
	}
	
	@GetMapping("/login")
	public String goToLoginUserPage(Model model)
	{
		model.addAttribute("userPass", new UserPass());
		return "login";
	}
	
	@PostMapping("/login")
	public String loginUser(@Valid UserPass userPass, BindingResult bindingResult, Model model)
	{
		if (bindingResult.hasErrors())
		{
			return "login";
		}
		
		Optional<User> user = uService.findByUsername(userPass.getUsername());
		if (!user.isPresent())
		{
			model.addAttribute("userDoesntExistErrorMessage", userDoesntExistErrorMessage);
			return "login";
		}
		
		model.addAttribute("user", user.get());
		Set<Account> accounts = user.map(User::getAccounts).get();
		model.addAttribute("accounts", accounts);
		
		return "userAccounts";
	}
}
