package com.zaycevImaginaryCompany.task.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.zaycevImaginaryCompany.task.domain.UserDTO;
import com.zaycevImaginaryCompany.task.exceptions.UserAlreadyExistsExseption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.zaycevImaginaryCompany.task.domain.User;
import com.zaycevImaginaryCompany.task.service.UserService;

import java.net.http.HttpResponse;

@Controller
@PropertySource("classpath:messages.properties")
public class UserController
{
	@Autowired
	private UserService userService;

	@Value("${validation.user.username.alreadyExists}")
	private String usernameExistsErrorMessage;

	@GetMapping("/")
	public String goToStartingPage(Model model)
	{
		/*String username = securityService.getLoggedInUsername();
		if (username != null)
		{
			model.addAttribute("userLogged", "userLogged");
		}*/
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

		userService.create(userDTO);

		//securityService.autoLogin(user.getUsername(), user.getPassword());
		model.addAttribute("userDTO", userDTO);
		
		return "userAccounts";
	}
	
	@GetMapping("/login")
	public String goToLoginUserPage(Model model)
	{
		model.addAttribute("userDTO", new UserDTO());
		return "login";
	}

	@ExceptionHandler(UserAlreadyExistsExseption.class)
	public String UserAlreadyExistsExceptionHandler(Model model)
	{
		model.addAttribute("userDTO", new UserDTO());
		model.addAttribute("usernameExistsErrorMessage", usernameExistsErrorMessage);
		return "register";
	}
	
}
