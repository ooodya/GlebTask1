package com.zaycevImaginaryCompany.glebTask1.controller;

import java.util.concurrent.ThreadLocalRandom;

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
	
	@GetMapping("/userAccounts")
	public String getUserAccounts(Model model)
	{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User dbUser = uService.findByUsername(user.getUsername()).orElse(null);
		model.addAttribute("user", dbUser);
		return "userAccounts";
	}
	
	@PostMapping("/addAccount")
	public String addAccountToUser(Model model)
	{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User dbUser = uService.findByUsername(user.getUsername()).orElse(null);
		if (dbUser != null)
		{
			long accNum = generateAccountNumber();
			aService.save(new Account(accNum, dbUser));
			model.addAttribute("user", dbUser);
		}
		return "userAccounts";
	}
	
	private long generateAccountNumber()
	{
		long accNum = ThreadLocalRandom.current().nextLong(1000000L, 9999999L);
		while (aService.findByAccountNumber(accNum).isPresent())
		{
			accNum = ThreadLocalRandom.current().nextLong(1000000L, 9999999L);
		}
		
		return accNum;
	}
}
