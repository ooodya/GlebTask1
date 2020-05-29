package com.zaycevImaginaryCompany.glebTask1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.zaycevImaginaryCompany.glebTask1.domain.Account;
import com.zaycevImaginaryCompany.glebTask1.domain.User;
import com.zaycevImaginaryCompany.glebTask1.service.AccountService;
import com.zaycevImaginaryCompany.glebTask1.service.UserService;

@Controller
public class UserAccsController
{
	@Autowired
	private AccountService aService;
	
	@Autowired
	private UserService uService;
	
//	@PostMapping("/userAddAccount")
//	public String addAccountToUser(Model model)
//	{
//		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		
//		return "userAccounts";
//	}
	//
//	@GetMapping("/userAddAccount")
//	public String showAddAccountToUser(Model model)
//	{
//		return "startpage";
//	}
	
	@GetMapping("/userAccounts")
	public String getUserAccounts(Model model)
	{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User dbUser = uService.findByUsername(user.getUsername()).orElse(null);
		model.addAttribute("user", dbUser);
		return "userAccounts";
	}
	
	@PostMapping("/userAccounts")
	public String addAccountToUser(Model model)
	{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User dbUser = uService.findByUsername(user.getUsername()).orElse(null);
		if (dbUser != null)
		{
			aService.save(new Account(dbUser, 1000, 10000000));
			model.addAttribute("user", dbUser);
		}
		return "userAccounts";
	}
}
