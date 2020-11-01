package com.zaycevImaginaryCompany.task.controller;

import com.zaycevImaginaryCompany.task.dto.AccountDTO;
import com.zaycevImaginaryCompany.task.dto.UserDTO;
import com.zaycevImaginaryCompany.task.service.AccountCreator;
import com.zaycevImaginaryCompany.task.service.BusinessUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zaycevImaginaryCompany.task.service.AccountCRUDService;

@Controller
public class UserAccountsController
{
	@Autowired
	private AccountCRUDService accountCRUDService;
	
	@Autowired
	private AccountCreator accountCreator;

	@Autowired
	private BusinessUserService businessUserService;

	@GetMapping("/userAccounts")
	public String getUserAccounts(Model model)
	{
		final UserDTO userDTO = businessUserService.findLoggedUser();
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
