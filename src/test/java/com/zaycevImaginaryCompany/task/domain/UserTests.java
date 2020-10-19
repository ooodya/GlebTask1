package com.zaycevImaginaryCompany.task.domain;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class UserTests
{
	public void testUserSettersAndGettersAreWorking()
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
	public void testUserCanAddSeveralAccounts()
	{
		User user = new User();
		Account acc = new Account(1L, user);
		Account acc1 = new Account(2L, user);
		
		user.addAccount(acc);
		user.addAccount(acc1);
		
		assertEquals(2, user.getAccounts().size());
	}
		
}
