package com.zaycevImaginaryCompany.task.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zaycevImaginaryCompany.task.domain.Account;
import com.zaycevImaginaryCompany.task.domain.User;
import com.zaycevImaginaryCompany.task.repository.AccountRepository;
import com.zaycevImaginaryCompany.task.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

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
	public boolean save(User user)
	{
		User userFromDB = userRepository.findByUsername(user.getUsername()).orElse(null);
		if (userFromDB != null)
		{
			return false;
		}

		User userForSaving = new User(user.getLastname(), user.getFirstname(), user.getUsername(), user.getPassword());
		userForSaving.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRepository.save(userForSaving);

		for (Account acc : user.getAccounts())
		{
			Optional<Account> persistedAcc = accountRepository.findByAccountNumber(acc.getAccountNumber());
			if (!persistedAcc.isPresent())
			{
				acc.addOwner(userForSaving);
				accountRepository.save(acc);
			}
		}
		
		return true;
	}

	@Override
	public void delete(User user)
	{
		userRepository.delete(user);
	}
}
