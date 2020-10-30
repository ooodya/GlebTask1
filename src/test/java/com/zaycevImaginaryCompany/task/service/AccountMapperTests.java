package com.zaycevImaginaryCompany.task.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.zaycevImaginaryCompany.task.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;

@SpringBootTest
public class AccountMapperTests
{
	@Autowired
	private AccountMapper accountMapper;
	
	@Test
	@DisplayName("Account should be correctly mapped to AccountDTO")
	public void canBeMappedToDTO()
	{
		long accountNumber = 123456789;
		int amount = 1000;

		User user = new User();
		String firstname = "John";
		String lastname = "Patrik";
		String username = "JohnnyGuitar";
		String password = "1";
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setUsername(username);
		user.setPassword(password);
		
		Account acc = new Account(user, accountNumber, amount);
		
		AccountDTO accDTO = accountMapper.accountToDTO(acc);
		
		assertEquals(accountNumber, accDTO.getAccountNumber());
		assertEquals(amount, accDTO.getAmount());

		assertEquals(firstname, accDTO.getUserDTOLight().getFirstname());
		assertEquals(lastname, accDTO.getUserDTOLight().getLastname());
		assertEquals(username, accDTO.getUserDTOLight().getUsername());
		assertEquals(password, accDTO.getUserDTOLight().getPassword());
	}
	
	@Test
	@DisplayName("AccountDTO should be correctly mapped to Account")
	public void canBeMappedToAccount()
	{
		long accountNumber = 123456789;
		int amount = 1000;

		UserDTOLight userDTOLight = new UserDTOLight();
		String firstname = "John";
		String lastname = "Patrik";
		String username = "JohnnyGuitar";
		String password = "1";
		userDTOLight.setFirstname(firstname);
		userDTOLight.setLastname(lastname);
		userDTOLight.setUsername(username);
		userDTOLight.setPassword(password);
		
		AccountDTO accDTO = new AccountDTO(userDTOLight, accountNumber, amount);
		
		Account acc = accountMapper.DTOtoAccount(accDTO);
		
		assertEquals(accountNumber, acc.getAccountNumber());
		assertEquals(amount, acc.getAmount());

		assertEquals(firstname, acc.getOwner().getFirstname());
		assertEquals(lastname, acc.getOwner().getLastname());
		assertEquals(username, acc.getOwner().getUsername());
		assertEquals(password, acc.getOwner().getPassword());
	}

	@Test
	@DisplayName("List of Account should be correctly mapped to List of AccountDTO")
	public void canBeMappedToAccountDTOList()
	{
		User user = new User("Vadim", "Zaytsev", "chevek", "1", new HashSet<>());

		Account acc1 = new Account(user, 1L, 100);
		Account acc2 = new Account(user, 2L, 200);

		List<AccountDTO> accountDTOList = accountMapper.accountsToDTOs(List.of(acc1, acc2));

		assertEquals(2, accountDTOList.size());

		assertEquals(1L, accountDTOList.get(0).getAccountNumber());
		assertEquals(100, accountDTOList.get(0).getAmount());
		assertEquals("Vadim", accountDTOList.get(0).getUserDTOLight().getFirstname());

		assertEquals(2L, accountDTOList.get(1).getAccountNumber());
		assertEquals(200, accountDTOList.get(1).getAmount());
		assertEquals("Vadim", accountDTOList.get(1).getUserDTOLight().getFirstname());
	}

	@Test
	@DisplayName("List of AccountDTO should be correctly mapped to List of Account")
	public void canBeMappedToAccountList()
	{
		UserDTOLight userDTOLight = new UserDTOLight("Vadim", "Zaytsev", "chevek", "1");

		AccountDTO accDTO1 = new AccountDTO(userDTOLight, 1L, 100);
		AccountDTO accDTO2 = new AccountDTO(userDTOLight, 2L, 200);

		List<Account> accountList = accountMapper.DTOsToAccounts(List.of(accDTO1, accDTO2));

		assertEquals(2, accountList.size());

		assertEquals(1L, accountList.get(0).getAccountNumber());
		assertEquals(100, accountList.get(0).getAmount());
		assertEquals("Vadim", accountList.get(0).getOwner().getFirstname());

		assertEquals(2L, accountList.get(1).getAccountNumber());
		assertEquals(200, accountList.get(1).getAmount());
		assertEquals("Vadim", accountList.get(1).getOwner().getFirstname());
	}

	@Test
	@DisplayName("Account can be updated from DTO")
	public void canBeUpdated()
	{
		User user = new User("Vadim", "Zaytsev", "chevek", "1", new HashSet<>());
		Account account = new Account(user, 1L, 100);

		UserDTOLight userDTOLight = new UserDTOLight("Vadim", "Zaytsev", "chevek", "2");
		AccountDTO accountDTO = new AccountDTO(userDTOLight, 1L, 200);

		accountMapper.updateAccountFromDTO(accountDTO, account);

		assertEquals(1L, account.getAccountNumber());
		assertEquals(200, account.getAmount());
		assertEquals("Vadim", account.getOwner().getFirstname());
		assertEquals("2", account.getOwner().getPassword());
	}
}
