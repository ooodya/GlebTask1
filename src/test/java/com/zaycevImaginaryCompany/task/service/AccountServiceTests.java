package com.zaycevImaginaryCompany.task.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.zaycevImaginaryCompany.task.domain.Account;
import com.zaycevImaginaryCompany.task.domain.User;
import com.zaycevImaginaryCompany.task.service.AccountService;

@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = {ServiceTestConfig.class})
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
public class AccountServiceTests
{
	@Autowired
	private AccountService accService;
	
	private User owner1 = new User("Patrik", "John", "JohnnyGuitar", "11");
	
	private int amount1 = 1000;
	private long accountNumber1 = 12345;
	
	private int amount2 = 5000;
	private long accountNumber2 = 67890;
	
	private Account acc1 = new Account(accountNumber1, owner1, amount1);
	private Account acc2 = new Account(accountNumber2, owner1, amount2);
	
	@BeforeAll
	void init()
	{
		accService.save(acc1);
		accService.save(acc2);
	}
	
	@Test
	void findByAccountNumberReturnCorrectValue()
	{
		Optional<Account> account = accService.findByAccountNumber(accountNumber1);
		
		assertEquals(Optional.of(acc1), account);
	}
	
	@Test
	void testThatSavingAccountAlsoSavesOwner()
	{
		Optional<Account> account = accService.findByAccountNumber(accountNumber1);
		Optional<User> owner = account.map(Account::getOwner);
		assertEquals(Optional.of(owner1), owner);
	}
}