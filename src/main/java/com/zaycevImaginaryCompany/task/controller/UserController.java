package com.zaycevImaginaryCompany.task.controller;

import javax.validation.Valid;

import com.zaycevImaginaryCompany.task.domain.User;
import com.zaycevImaginaryCompany.task.domain.UserDTO;
import com.zaycevImaginaryCompany.task.exceptions.UserAlreadyExistsExseption;
import com.zaycevImaginaryCompany.task.security.SecurityService;
import com.zaycevImaginaryCompany.task.service.UserCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@PropertySource("classpath:messages.properties")
public class UserController
{
	@Autowired
	private UserCreator userCreator;

	@Autowired
	private SecurityService securityService;

	@Value("${validation.user.username.alreadyExists}")
	private String usernameExistsErrorMessage;

	@GetMapping("/")
	public String goToStartingPage(Model model)
	{
		String username = securityService.getLoggedUsername();
		if (username != null)
		{
			model.addAttribute("userLogged", "userLogged");
		}
		return "startpage";
	}

	@GetMapping("/register")
	public String goToRegisterUserPage(Model model)
	{
		model.addAttribute("userDTO", new UserDTO());
		return "register";
	}

	@PostMapping("/register")
	public String registerUser(@Valid UserDTO userDTO, BindingResult bindingResult, Model model)
	{
		if (bindingResult.hasErrors())
		{
			return "register";
		}

		return registerUser(userDTO, model);
	}
	
	@GetMapping("/login")
	public String goToLoginUserPage(Model model)
	{
		return "login";
	}

	private String registerUser(UserDTO userDTO, Model model)
	{
		try
		{
			userCreator.createUser(userDTO);
		}
		catch (UserAlreadyExistsExseption e)
		{
			model.addAttribute("usernameExistsErrorMessage", usernameExistsErrorMessage);
			model.addAttribute("userDTO", userDTO);
			return "register";
		}

		model.addAttribute("userDTO", userDTO);
		return "userAccounts";
	}
	
}
