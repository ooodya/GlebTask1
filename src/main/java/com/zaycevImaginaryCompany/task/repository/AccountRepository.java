package com.zaycevImaginaryCompany.task.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.zaycevImaginaryCompany.task.domain.Account;

public interface AccountRepository extends CrudRepository<Account, Long>
{
	Optional<Account> findByAccountNumber(long accountNumber);
}
