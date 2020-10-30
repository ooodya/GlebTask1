package com.zaycevImaginaryCompany.task.service;

import com.zaycevImaginaryCompany.task.domain.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

		final Optional<AccountDTO> sourceAccountDTO = accountService.findByAccountNumber(sourceAccNumber);
		final Optional<AccountDTO> destinationAccountDTO = accountService.findByAccountNumber(destinationAccNumber);

		if (sourceAccountDTO.isEmpty() || destinationAccountDTO.isEmpty())
		{
			return false;
		}

		AccountDTO sourceDTO = sourceAccountDTO.get();
		AccountDTO destinationDTO = destinationAccountDTO.get();

		if (sourceDTO.getAmount() < amount)
		{
			return false;
		}

		sourceDTO.setAmount(sourceDTO.getAmount() - amount);
		destinationDTO.setAmount(destinationDTO.getAmount() + amount);

		accountService.update(sourceDTO);
		accountService.update(destinationDTO);

		return true;
	}

}
