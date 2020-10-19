package com.zaycevImaginaryCompany.task.domain;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

public class AccountTests
{
	@Test
	public void testAccountSettersAndGettersAreWorking()
	{
		User owner = new User("Patrik", "John", "JohnnyGuitar", "1");
		long accountNumber = 123456789;
		int amount = 1000;
		
		Account acc = new Account(accountNumber, owner);
		acc.setAmount(amount);
		
		assertThat(owner, is(acc.getOwner()));
		assertThat(accountNumber, is(acc.getAccountNumber()));
		assertThat(amount, is(acc.getAmount()));
	}
	
	@Test
	public void testAccountConstructorIsWorking()
	{
		User owner = new User("Patrik", "John", "JohnnyGuitar", "1");
		long accountNumber = 123456789;
		int amount = 1000;
		
		Account acc = new Account(accountNumber, owner, amount);
		
		assertThat(owner, is(acc.getOwner()));
		assertThat(accountNumber, is(acc.getAccountNumber()));
		assertThat(amount, is(acc.getAmount()));
	}
	
}
