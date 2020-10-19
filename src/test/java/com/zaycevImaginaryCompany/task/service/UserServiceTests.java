package com.zaycevImaginaryCompany.task.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

import com.zaycevImaginaryCompany.task.domain.Account;
import com.zaycevImaginaryCompany.task.domain.User;
import com.zaycevImaginaryCompany.task.service.UserService;


@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = {ServiceTestConfig.class})
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
public class UserServiceTests
{
	private String username1 = "Glebao";
	private String firstname1 = "Gleb";
	private String lastname1 = "Zaborovskiy";
	private String password1 = "1";
	
	private String username2 = "MelkiyMultik";
	private String firstname2 = "Julia";
	private String lastname2 = "Zaytseva";
	private String password2 = "2";
	
	private String username3 = "Chevek";
	private String firstname3 = "Vadim";
	private String lastname3 = "Zaytsev";
	private String password3 = "3";
	
	private User user1 = new User(lastname1, firstname1, username1, password1);
	private User user2 = new User(lastname2, firstname2, username2, password2);
	private User user3 = new User(lastname3, firstname3, username3, password3);
	
	private int amount1 = 1000;
	private long accountNumber1 = 0505;
	
	private int amount2 = 5000;
	private long accountNumber2 = 6060;
	
	private Account acc1 = new Account(accountNumber1, user3, amount1);
	private Account acc2 = new Account(accountNumber2, user3, amount2);
	
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
		Optional<User> user = uService.findByUsername(username1);
		
		assertEquals(Optional.of(user1), user);
	}
	
	@Test
	void findByLastnameAndFirstnameReturnCorrectValue()
	{
		List<User> users = uService.findByLastnameAndFirstname(lastname2, firstname2);
		List<User> expectedUsers = Collections.singletonList(user2);
		
		assertThat(users, is(expectedUsers));
	}
	
	@Test
	void testThatSavingUsertAlsoSavesAccounts()
	{
		Optional<User> user = uService.findByUsername(username3);
		Optional<Set<Account>> accs = user.map(User::getAccounts);
		
		Set<Account> myAccs = new HashSet<>();
		myAccs.add(acc1);
		myAccs.add(acc2);
		assertEquals(Optional.of(myAccs), accs);
	}
}
