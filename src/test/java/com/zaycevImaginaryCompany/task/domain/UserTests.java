package com.zaycevImaginaryCompany.task.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class UserTests
{
	@Test
	@DisplayName("Setters and getters are working")
	public void settersAndGettersAreWorking()
	{
		User user = new User();
		String firstname = "John";
		String lastname = "Patrik";
		String username = "JohnnyGuitar";
		String password = "1";
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setUsername(username);
		user.setPassword(password);
		
		assertEquals(firstname, user.getFirstname());
		assertEquals(lastname, user.getLastname());
		assertEquals(username, user.getUsername());
		assertEquals(password, user.getPassword());
	}
	
	@Test
	@DisplayName("User can have several accounts")
	public void canAddSeveralAccounts()
	{
		User user = new User();
		Account acc1 = new Account(user, 1L, 0);
		Account acc2 = new Account(user, 2L, 0);

		user.addAccount(acc1);
		user.addAccount(acc2);

		assertEquals(2, user.getAccounts().size());
	}
	
	@Test
	@DisplayName("Adding account without owner also sets owner to account")
	public void canAddOwnerToAccountWhenAddingAccount()
	{
		Account acc = new Account();		
		User user = new User();
		
		user.addAccount(acc);
		
		assertNotNull(acc.getOwner());
	}
		
}
