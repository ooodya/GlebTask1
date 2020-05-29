package com.zaycevImaginaryCompany.glebTask1.service;

import java.util.List;
import java.util.Optional;

import com.zaycevImaginaryCompany.glebTask1.domain.Account;

public interface AccountService
{
	Optional<Account> findByAccountNumber(long accountNumber);
	
	List<Account> findAll();

	Optional<Account> findById(Long id);

	boolean save(Account account);

	void delete(Account account);
}
