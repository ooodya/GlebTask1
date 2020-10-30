package com.zaycevImaginaryCompany.task.service;

import java.util.List;
import java.util.Optional;

import com.zaycevImaginaryCompany.task.domain.Account;
import com.zaycevImaginaryCompany.task.domain.AccountDTO;

public interface AccountService
{
	Optional<AccountDTO> findByAccountNumber(long accountNumber);
	
	List<AccountDTO> findAll();

	Optional<AccountDTO> findById(Long id);

	void create(AccountDTO accountDTO);

	void update(AccountDTO accountDTO);

	void delete(AccountDTO accountDTO);
}
