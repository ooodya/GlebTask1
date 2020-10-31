package com.zaycevImaginaryCompany.task.controller;

import com.zaycevImaginaryCompany.task.domain.AccountDTO;
import com.zaycevImaginaryCompany.task.domain.UserDTO;
import com.zaycevImaginaryCompany.task.service.AccountCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zaycevImaginaryCompany.task.service.AccountCRUDService;
import com.zaycevImaginaryCompany.task.service.UserCRUDService;

@Controller
public class UserAccountsController
{
	@Autowired
	private AccountCRUDService accountCRUDService;
	
	@Autowired
	private UserCRUDService userCRUDService;

	@Autowired
	private AccountCreator accountCreator;

	@GetMapping("/userAccounts")
	public String getUserAccounts(@RequestParam String username, Model model)
	{
		final UserDTO userDTO = userCRUDService.findByUsername(username).orElse(null);
		model.addAttribute("userDTO", userDTO);
		return "userAccounts";
	}

	@PostMapping("/chooseAccount")
	public String chooseAccount(@RequestParam long accountNumber, Model model)
	{
		AccountDTO accountDTO = accountCRUDService.findByAccountNumber(accountNumber).orElseGet(AccountDTO::new);
		model.addAttribute("accountDTO", accountDTO);
		return "account";
	}
	
	@PostMapping("/addAccount")
	public String addAccountToUser(@RequestParam String username, Model model)
	{
		final UserDTO updatedUserDTO = accountCreator.createAccount(username);
		model.addAttribute("userDTO", updatedUserDTO);
		return "userAccounts";
	}

}
