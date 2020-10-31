package com.zaycevImaginaryCompany.task.domain;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AccountTests
{
	@Test
	@DisplayName("Setters and getters are working")
	public void testAccountSettersAndGettersAreWorking()
	{
		User owner = new User("Patrik", "John", "JohnnyGuitar", "1", new HashSet<>());
		long accountNumber = 123456789;
		int amount = 1000;
		
		Account acc = new Account(owner, accountNumber, amount);
		acc.setAmount(amount);
		
		assertThat(owner, is(acc.getOwner()));
		assertThat(accountNumber, is(acc.getAccountNumber()));
		assertThat(amount, is(acc.getAmount()));
	}
	
	@Test
	@DisplayName("Account can be constructed")
	public void testAccountConstructorIsWorking()
	{
		User owner = new User("Patrik", "John", "JohnnyGuitar", "1", new HashSet<>());
		long accountNumber = 123456789;
		int amount = 1000;
		
		Account acc = new Account(owner, accountNumber, amount);
		
		assertThat(owner, is(acc.getOwner()));
		assertThat(accountNumber, is(acc.getAccountNumber()));
		assertThat(amount, is(acc.getAmount()));
	}
	
	@Test
	@DisplayName("Account constructor adds account to user.accounts")
	public void constructorAddsAccountToUser()
	{
		User owner = new User("Patrik", "John", "JohnnyGuitar", "1", new HashSet<>());
		
		assertEquals(0, owner.getAccounts().size());
		
		new Account(owner, 1L, 0);
		new Account(owner, 2L, 0);
		
		assertEquals(2, owner.getAccounts().size());
	}
}
