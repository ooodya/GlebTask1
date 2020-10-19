package com.zaycevImaginaryCompany.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zaycevImaginaryCompany.task.domain.Account;

@Service
@Transactional
public class AccountTransferServiceImpl implements AccountTransferService
{
	@Autowired
	private AccountService accountService;
	
	@Override
	public boolean transfer(long destinationAccNumber, long sourceAccNumber, int amount)
	{
		if (amount < 0)
		{
			return false;
		}
		
		Account sourceAccount = accountService.findByAccountNumber(sourceAccNumber).orElse(null);
		Account destinationAccount = accountService.findByAccountNumber(destinationAccNumber).orElse(null);
		
		if (sourceAccount != null && destinationAccount != null)
		{
			if (sourceAccount.getAmount() < amount)
			{
				return false;
			}
			sourceAccount.setAmount(sourceAccount.getAmount() - amount);
			destinationAccount.setAmount(destinationAccount.getAmount() + amount);
			return true;
		}
		
		return false;
	}

}
