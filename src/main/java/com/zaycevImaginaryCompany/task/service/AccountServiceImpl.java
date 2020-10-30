package com.zaycevImaginaryCompany.task.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.zaycevImaginaryCompany.task.domain.AccountDTO;
import com.zaycevImaginaryCompany.task.exceptions.AccountNotFoundException;
import org.hibernate.collection.internal.PersistentSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zaycevImaginaryCompany.task.domain.Account;
import com.zaycevImaginaryCompany.task.domain.User;
import com.zaycevImaginaryCompany.task.repository.AccountRepository;
import com.zaycevImaginaryCompany.task.repository.UserRepository;

@Service
@Transactional
public class AccountServiceImpl implements AccountService
{
	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AccountMapper accountMapper;

	@Override
	@Transactional(readOnly = true)
	public Optional<AccountDTO> findByAccountNumber(long accountNumber)
	{
		final Optional<Account> foundAcc = accountRepository.findByAccountNumber(accountNumber);
		return foundAcc.map(accountMapper::accountToDTO);
	}

	@Override
	@Transactional(readOnly = true)
	public List<AccountDTO> findAll()
	{
		List<Account> accounts = new ArrayList<>();
		accountRepository.findAll().forEach(accounts::add);
		return accountMapper.accountsToDTOs(accounts);
	}

	@Override
	public void create(AccountDTO accountDTO)
	{
		Account account = accountMapper.DTOtoAccount(accountDTO);
		Optional<User> owner = userRepository.findByUsername(account.getOwner().getUsername());
		owner.ifPresent(account::setOwner);

		accountRepository.save(account);
	}

	@Override
	public void update(AccountDTO accountDTO)
	{
		final Optional<Account> account = accountRepository.findByAccountNumber(accountDTO.getAccountNumber());
		if (account.isEmpty())
		{
			throw new AccountNotFoundException(accountDTO.getAccountNumber());
		}
		accountMapper.updateAccountFromDTO(accountDTO, account.get());
		accountRepository.save(account.get());
	}

	@Override
	public void delete(AccountDTO accountDTO)
	{
		final Optional<Account> accountOptional = accountRepository.findByAccountNumber(accountDTO.getAccountNumber());
		if (accountOptional.isPresent())
		{
			final Account account = accountOptional.get();

			account.deleteOwner();
			accountRepository.delete(account);
		}
	}

}
