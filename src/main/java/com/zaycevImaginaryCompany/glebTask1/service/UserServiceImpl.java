package com.zaycevImaginaryCompany.glebTask1.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zaycevImaginaryCompany.glebTask1.domain.Account;
import com.zaycevImaginaryCompany.glebTask1.domain.User;
import com.zaycevImaginaryCompany.glebTask1.repository.AccountRepository;
import com.zaycevImaginaryCompany.glebTask1.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<User> findAll()
	{
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> findByLastname(String lastname)
	{
		List<User> users = userRepository.findByLastname(lastname);
		return users;
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> findByLastnameAndFirstname(String lastname, String firstname)
	{
		List<User> users = userRepository.findByLastnameAndFirstname(lastname, firstname);
		return users;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> findByUsername(String username)
	{
		Optional<User> user = userRepository.findByUsername(username);
		return user;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> findById(Long id)
	{
		Optional<User> user = userRepository.findById(id);
		return user;
	}

	@Override
	public User save(User user)
	{
		User retVal = userRepository.save(user);
		
		for (Account acc : user.getAccounts())
		{
			Optional<Account> persistedAcc = accountRepository.findByAccountNumber(acc.getAccountNumber());
			if (!persistedAcc.isPresent())
			{
				accountRepository.save(acc);
			}
		}
		return retVal;
	}

	@Override
	public void delete(User user)
	{
		userRepository.delete(user);
	}

}
