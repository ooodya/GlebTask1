package com.zaycevImaginaryCompany.glebTask1.domain;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class BankAccountTests
{
	@Test
	void testBankAccountSettersAndGettersAreWorking()
	{
		User owner = new User("Patrik", "John", "JohnnyGuitar");
		long accountNumber = 123456789;
		int amount = 1000;
		
		BankAccount acc = new BankAccount();
		acc.setAccountNumber(accountNumber);
		acc.setOwner(owner);
		acc.setAmount(amount);
		
		assertThat(owner, is(acc.getOwner()));
		assertThat(accountNumber, is(acc.getAccountNumber()));
		assertThat(amount, is(acc.getAmount()));
	}
	
	@Test
	void testBankAccountConstructorIsWorking()
	{
		User owner = new User("Patrik", "John", "JohnnyGuitar");
		long accountNumber = 123456789;
		int amount = 1000;
		
		BankAccount acc = new BankAccount(owner, amount, accountNumber);
		
		assertThat(owner, is(acc.getOwner()));
		assertThat(accountNumber, is(acc.getAccountNumber()));
		assertThat(amount, is(acc.getAmount()));
	}
}
