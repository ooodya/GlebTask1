package com.zaycevImaginaryCompany.task.controller;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zaycevImaginaryCompany.task.domain.Account;
import com.zaycevImaginaryCompany.task.domain.User;
import com.zaycevImaginaryCompany.task.service.AccountService;
import com.zaycevImaginaryCompany.task.service.UserService;

@Controller
public class UserAccsController
{
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private UserService userService;
	

	@GetMapping("/userAccounts")
	public String getUserAccounts(Model model)
	{
		//String username = securityService.getLoggedInUsername();
		/*User dbUser = userService.findByUsername(username).orElse(null);
		model.addAttribute("user", dbUser);*/
		return "userAccounts";
	}
	
	@PostMapping("/chooseAccount")
	public String chooseAccount(@RequestParam String chosenAccount, Model model)
	{
		/*long accNumber = Long.parseLong(chosenAccount);
		Account account = accountService.findByAccountNumber(accNumber).orElseGet(Account::new);
		model.addAttribute("account", account);*/
		return "account";
	}
	
	@PostMapping("/addAccount")
	public String addAccountToUser(Model model)
	{
		//String username = securityService.getLoggedInUsername();
		/*User dbUser = userService.findByUsername(username).orElse(null);
		if (dbUser != null)
		{
			if (dbUser.getAccounts().size() < 5)
			{
				long accNum = generateAccountNumber();
				accountService.save(new Account(dbUser, accNum, 0));
			}
			else
			{
				model.addAttribute("tooMuchAccounts", "You already have enough accounts");
			}
			model.addAttribute("user", dbUser);
		}*/
		return "userAccounts";
	}
	
	private long generateAccountNumber()
	{
		long accNum = ThreadLocalRandom.current().nextLong(1000000L, 9999999L);
		while (accountService.findByAccountNumber(accNum).isPresent())
		{
			accNum = ThreadLocalRandom.current().nextLong(1000000L, 9999999L);
		}
		
		return accNum;
	}
}
