package com.zaycevImaginaryCompany.glebTask1.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.zaycevImaginaryCompany.glebTask1.config.ServiceTestConfig;
import com.zaycevImaginaryCompany.glebTask1.domain.User;
import com.zaycevImaginaryCompany.glebTask1.repository.UserRepository;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ServiceTestConfig.class})
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
public class UserServiceTests
{
	private String username1 = "Glebao";
	private String firstname1 = "Gleb";
	private String lastname1 = "Zaborovskiy";
	
	private String username2 = "MelkiyMultik";
	private String firstname2 = "Julia";
	private String lastname2 = "Zaytseva";
	
	private String username3 = "Chevek";
	private String firstname3 = "Vadim";
	private String lastname3 = "Zaytsev";
	
	private User user1 = new User(lastname1, firstname1, username1);
	private User user2 = new User(lastname2, firstname2, username2);
	private User user3 = new User(lastname3, firstname3, username3);
	
	@Autowired
	private UserService uService;
	
	@BeforeAll
	void init()
	{
		uService.save(user1);
		uService.save(user2);
		uService.save(user3);
	}
		
	@Test
	void findByUsernameReturnCorrectValue()
	{
		Optional<User> user = uService.findByUserName(username1);
		
		assertEquals(Optional.of(user1), user);
	}
	
	@Test
	void findByLastnameAndFirstnameReturnCorrectValue()
	{
		List<User> users = uService.findByLastnameAndFirstname(lastname2, firstname2);
		List<User> expectedUsers = Collections.singletonList(user2);
		
		assertThat(users, is(expectedUsers));
	}
}
