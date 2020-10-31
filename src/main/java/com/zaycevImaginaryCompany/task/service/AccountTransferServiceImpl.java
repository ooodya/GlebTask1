package com.zaycevImaginaryCompany.task.service;

import com.zaycevImaginaryCompany.task.domain.AccountDTO;
import com.zaycevImaginaryCompany.task.exceptions.AccountNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AccountTransferServiceImpl implements AccountTransferService
{
	@Autowired
	private AccountCRUDService accountCRUDService;
	
	@Override
	public Optional<AccountDTO> transfer(long destinationAccNumber, long sourceAccNumber, int amount)
	{
		if (amount < 0)
		{
			return Optional.empty();
		}

		final Optional<AccountDTO> sourceAccountDTO = accountCRUDService.findByAccountNumber(sourceAccNumber);
		final Optional<AccountDTO> destinationAccountDTO = accountCRUDService.findByAccountNumber(destinationAccNumber);

		if (sourceAccountDTO.isEmpty())
		{
			throw new AccountNotFoundException(sourceAccNumber);
		}
		if (destinationAccountDTO.isEmpty())
		{
			throw new AccountNotFoundException(destinationAccNumber);
		}

		AccountDTO sourceDTO = sourceAccountDTO.get();
		AccountDTO destinationDTO = destinationAccountDTO.get();

		if (sourceDTO.getAmount() < amount)
		{
			return Optional.empty();
		}

		sourceDTO.setAmount(sourceDTO.getAmount() - amount);
		destinationDTO.setAmount(destinationDTO.getAmount() + amount);

		accountCRUDService.update(sourceDTO);
		accountCRUDService.update(destinationDTO);

		return Optional.of(sourceDTO);
	}

	@Override
	public Optional<AccountDTO> addMoney(long accountNumber, int amount)
	{
		if (amount < 0)
		{
			return Optional.empty();
		}

		final Optional<AccountDTO> accountDTOOptional = accountCRUDService.findByAccountNumber(accountNumber);

		if (accountDTOOptional.isEmpty())
		{
			throw new AccountNotFoundException(accountNumber);
		}

		AccountDTO accountDTO = accountDTOOptional.get();
		accountDTO.setAmount(accountDTO.getAmount() + amount);

		accountCRUDService.update(accountDTO);

		return Optional.of(accountDTO);
	}

}
