package com.zaycevImaginaryCompany.glebTask1.domain;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class AccountTests
{
	@Test
	void testAccountSettersAndGettersAreWorking()
	{
		User owner = new User("Patrik", "John", "JohnnyGuitar");
		long accountNumber = 123456789;
		int amount = 1000;
		
		Account acc = new Account();
		acc.setAccountNumber(accountNumber);
		acc.addOwner(owner);
		acc.setAmount(amount);
		
		assertThat(owner, is(acc.getOwner()));
		assertThat(accountNumber, is(acc.getAccountNumber()));
		assertThat(amount, is(acc.getAmount()));
	}
	
	@Test
	void testAccountConstructorIsWorking()
	{
		User owner = new User("Patrik", "John", "JohnnyGuitar");
		long accountNumber = 123456789;
		int amount = 1000;
		
		Account acc = new Account(owner, amount, accountNumber);
		
		assertThat(owner, is(acc.getOwner()));
		assertThat(accountNumber, is(acc.getAccountNumber()));
		assertThat(amount, is(acc.getAmount()));
	}
}
