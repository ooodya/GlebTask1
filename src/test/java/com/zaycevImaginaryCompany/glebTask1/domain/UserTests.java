package com.zaycevImaginaryCompany.glebTask1.domain;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class UserTests
{
	@Test
	void testUserSettersAndGettersAreWorking()
	{
		User user = new User();
		String firstname = "John";
		String lastname = "Patrik";
		String username = "JohnnyGuitar";
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setUsername(username);
		
		assertThat(firstname, is(user.getFirstname()));
		assertThat(lastname, is(user.getLastname()));
		assertThat(username, is(user.getUsername()));
	}
}
