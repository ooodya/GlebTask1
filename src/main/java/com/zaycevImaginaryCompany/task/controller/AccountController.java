package com.zaycevImaginaryCompany.task.controller;

import com.zaycevImaginaryCompany.task.domain.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zaycevImaginaryCompany.task.service.AccountService;
import com.zaycevImaginaryCompany.task.service.AccountTransferService;

import java.util.Optional;

@Controller
public class AccountController
{
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AccountTransferService transferService;
	
	@PostMapping("/addAmount")
	public String addAmount(@RequestParam int amountToAdd, @ModelAttribute AccountDTO accountDTO, Model model)
	{
		final Optional<AccountDTO> accountDTOOptional = transferService.addMoney(accountDTO.getAccountNumber(), amountToAdd);
		if (accountDTOOptional.isEmpty())
		{
			model.addAttribute("addingAmountError", "");
			model.addAttribute("accountDTO", accountDTO);
		}
		else
		{
			model.addAttribute("accountDTO", accountDTOOptional.get());
		}
			
		return "account";
	}
	
	@PostMapping("/transferMoney")
	public String transferMoney(@ModelAttribute AccountDTO sourceAccountDTO, @RequestParam long destinationAccountNumber, @RequestParam int transferAmount, Model model)
	{
		final Optional<AccountDTO> sourceAccountDTOAfterTransferOptional = transferService.transfer(destinationAccountNumber, sourceAccountDTO.getAccountNumber(), transferAmount);
		if (sourceAccountDTOAfterTransferOptional.isPresent())
		{
			model.addAttribute("transferSuccess", "");
			model.addAttribute("accountDTO", sourceAccountDTOAfterTransferOptional.get());
		}
		else
		{
			model.addAttribute("transferFail", "");
			model.addAttribute("accountDTO", sourceAccountDTO);
		}
		
		return "account";
	}
}
