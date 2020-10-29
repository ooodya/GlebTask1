package com.zaycevImaginaryCompany.task.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.zaycevImaginaryCompany.task.domain.UserDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.zaycevImaginaryCompany.task.domain.Account;
import com.zaycevImaginaryCompany.task.domain.AccountDTO;
import com.zaycevImaginaryCompany.task.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;

@SpringBootTest
public class AccountMapperTests
{
	@Autowired
	private AccountMapper accountMapper;
	
	@Test
	@DisplayName("Account should be correctly mapped to AccountDTO")
	public void canBeMappedToDTO()
	{
		User owner = new User("Patrik", "John", "JohnnyGuitar", "1", new HashSet<>());
		long accountNumber = 123456789;
		int amount = 1000;
		
		Account acc = new Account(owner, accountNumber, amount);
		
		AccountDTO accDTO = accountMapper.accountToDTO(acc, new CycleAvoidingMappingContext());
		
		assertEquals(accountNumber, accDTO.getAccountNumber());
		assertEquals(amount, accDTO.getAmount());
	}
	
	@Test
	@DisplayName("AccountDTO should be correctly mapped to Account")
	public void canBeMappedFromAccountToDTO()
	{
		long accountNumber = 123456789;
		int amount = 1000;
		
		AccountDTO accDTO = new AccountDTO(new UserDTO(), accountNumber, amount);
		
		Account acc = accountMapper.DTOtoAccount(accDTO, new CycleAvoidingMappingContext());
		
		assertEquals(accountNumber, acc.getAccountNumber());
		assertEquals(amount, acc.getAmount());
	}
}
