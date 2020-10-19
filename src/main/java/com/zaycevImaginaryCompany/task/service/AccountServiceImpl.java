package com.zaycevImaginaryCompany.task.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Account> findByAccountNumber(long accountNumber)
	{
		return accountRepository.findByAccountNumber(accountNumber);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Account> findAll()
	{
		List<Account> accounts = new ArrayList<>();
		accountRepository.findAll().forEach(accounts::add);
		return accounts;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Account> findById(Long id)
	{
		return accountRepository.findById(id);
	}

	@Override
	public boolean save(Account account)
	{
		Optional<User> owner = userRepository.findByUsername(account.getOwner().getUsername());
		if (!owner.isPresent())
		{
			userRepository.save(account.getOwner());
		}
		
		Account dbAccount = accountRepository.findByAccountNumber(account.getAccountNumber()).orElse(null);
		if (dbAccount != null)
		{
			return false;
		}
		accountRepository.save(account);
		return true;
	}

	@Override
	public void delete(Account account)
	{
		accountRepository.delete(account);
	}

}
