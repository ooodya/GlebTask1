package com.zaycevImaginaryCompany.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zaycevImaginaryCompany.task.domain.Account;
import com.zaycevImaginaryCompany.task.service.AccountService;
import com.zaycevImaginaryCompany.task.service.AccountTransferService;

@Controller
public class AccountController
{
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AccountTransferService transferService;
	
	@PostMapping("/addAmount")
	public String addAmount(@RequestParam String amount, @RequestParam String accountNumber,  Model model)
	{
		long accNumber = 0;
		if (!accountNumber.isEmpty())
		{
			accNumber = Long.parseLong(accountNumber);
		}
		int iAmount = 0;
		if (!amount.isEmpty())
		{
			iAmount = Integer.parseInt(amount);
			if (iAmount < 0)
			{
				iAmount = -iAmount;
			}
		}
			
		Account account = accountService.findByAccountNumber(accNumber).orElse(null);
		account.setAmount(account.getAmount() + iAmount);
		accountService.save(account);
		
		model.addAttribute("account", account);
		
		return "account";
	}
	
	@PostMapping("/transferMoney")
	public String transferMoney(@RequestParam String accountNumber, @RequestParam String transferAmount, @RequestParam String destinationAccountNumber,  Model model)
	{
		long accNumber = 0;
		if (!accountNumber.isEmpty())
		{
			accNumber = Long.parseLong(accountNumber);
		}
		Account account = accountService.findByAccountNumber(accNumber).orElse(null);
		model.addAttribute("account", account);
		
		long destAccNum = 0; 
		if (!destinationAccountNumber.isEmpty())
		{
			destAccNum = Long.parseLong(destinationAccountNumber);
		}
		else
		{
			model.addAttribute("transferDestinationAccountError", "");
			return "account";
		}
		
		int amountToTransfer = 0;
		if (!transferAmount.isEmpty())
		{
			amountToTransfer = Integer.parseInt(transferAmount);
		}
		else
		{
			model.addAttribute("transferAmountError", "");
			return "account";
		}
		
		if (transferService.transfer(destAccNum, accNumber, amountToTransfer))
		{
			model.addAttribute("transferSuccess", "");
			account = accountService.findByAccountNumber(accNumber).orElse(null);
			model.addAttribute("account", account);
		}
		else
		{
			model.addAttribute("transferFail", "");
		}
		
		return "account";
	}
}
