package com.zaycevImaginaryCompany.task.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.zaycevImaginaryCompany.task.domain.Account;
import com.zaycevImaginaryCompany.task.domain.User;

@SpringBootTest
public class AccountServiceTests
{
	@Autowired
	private AccountService accService;
	
	@Autowired
	private UserService uService;
	
	private User owner1 = new User("Patrik", "John", "JohnnyGuitar", "11");
	
	private int amount1 = 1000;
	private long accountNumber1 = 12345;
	
	private int amount2 = 5000;
	private long accountNumber2 = 67890;
	
	private Account acc1 = new Account(accountNumber1, owner1, amount1);
	private Account acc2 = new Account(accountNumber2, owner1, amount2);
	
	@BeforeEach
	void init()
	{
		accService.save(acc1);
		accService.save(acc2);
	}
	
	@AfterEach
	void delete()
	{
		uService.delete(owner1);
	}
		
	@Test
	public void findByAccountNumberReturnCorrectValue()
	{
		Optional<Account> account = accService.findByAccountNumber(accountNumber1);
		
		assertEquals(Optional.of(acc1), account);
	}
	
	@Test
	public void testThatSavingAccountAlsoSavesOwner()
	{
		Optional<Account> account = accService.findByAccountNumber(accountNumber1);
		Optional<User> owner = account.map(Account::getOwner);
		assertEquals(Optional.of(owner1), owner);
	}
}
