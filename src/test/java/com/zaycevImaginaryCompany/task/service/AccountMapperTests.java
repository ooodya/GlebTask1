package com.zaycevImaginaryCompany.task.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.zaycevImaginaryCompany.task.domain.*;
import com.zaycevImaginaryCompany.task.dto.AccountDTO;
import com.zaycevImaginaryCompany.task.dto.UserDTOLite;
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

		assertEquals(firstname, accDTO.getUserDTOLite().getFirstname());
		assertEquals(lastname, accDTO.getUserDTOLite().getLastname());
		assertEquals(username, accDTO.getUserDTOLite().getUsername());
		assertEquals(password, accDTO.getUserDTOLite().getPassword());
	}
	
	@Test
	@DisplayName("AccountDTO should be correctly mapped to Account")
	public void canBeMappedToAccount()
	{
		long accountNumber = 123456789;
		int amount = 1000;

		UserDTOLite userDTOLite = new UserDTOLite();
		String firstname = "John";
		String lastname = "Patrik";
		String username = "JohnnyGuitar";
		String password = "1";
		userDTOLite.setFirstname(firstname);
		userDTOLite.setLastname(lastname);
		userDTOLite.setUsername(username);
		userDTOLite.setPassword(password);
		
		AccountDTO accDTO = new AccountDTO(userDTOLite, accountNumber, amount);
		
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
		assertEquals("Vadim", accountDTOList.get(0).getUserDTOLite().getFirstname());

		assertEquals(2L, accountDTOList.get(1).getAccountNumber());
		assertEquals(200, accountDTOList.get(1).getAmount());
		assertEquals("Vadim", accountDTOList.get(1).getUserDTOLite().getFirstname());
	}

	@Test
	@DisplayName("List of AccountDTO should be correctly mapped to List of Account")
	public void canBeMappedToAccountList()
	{
		UserDTOLite userDTOLite = new UserDTOLite("Vadim", "Zaytsev", "chevek", "1");

		AccountDTO accDTO1 = new AccountDTO(userDTOLite, 1L, 100);
		AccountDTO accDTO2 = new AccountDTO(userDTOLite, 2L, 200);

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

		UserDTOLite userDTOLite = new UserDTOLite("Vadim", "Zaytsev", "chevek", "1");
		AccountDTO accountDTO = new AccountDTO(userDTOLite, 1L, 200);

		accountMapper.updateAccountFromDTO(accountDTO, account);

		assertEquals(1L, account.getAccountNumber());
		assertEquals(200, account.getAmount());
	}
}
