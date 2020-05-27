package com.zaycevImaginaryCompany.glebTask1.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.zaycevImaginaryCompany.glebTask1.domain.Account;

public interface AccountRepository extends CrudRepository<Account, Long>
{
	Optional<Account> findByAccountNumber(long accountNumber);
}
