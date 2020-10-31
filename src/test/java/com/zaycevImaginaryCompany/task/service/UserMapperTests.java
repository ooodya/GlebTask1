package com.zaycevImaginaryCompany.task.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.zaycevImaginaryCompany.task.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMapperTests
{
	@Autowired
	private UserMapper userMapper;
	
	@Test
	@DisplayName("User should be correctly mapped to UserDTO")
	public void canBeMappedToDTO()
	{
		String firstname = "John";
		String lastname = "Patrik";
		String username = "JohnnyGuitar";
		String password = "1";
		
		User user = new User(firstname, lastname, username, password, new HashSet<>());

		Account account1 = new Account(user, 1L, 100);
		Account account2 = new Account(user, 2L, 200);

		UserDTO userDTO = userMapper.userToDTO(user);
		
		assertEquals(lastname, userDTO.getLastname());
		assertEquals(firstname, userDTO.getFirstname());
		assertEquals(username, userDTO.getUsername());
		assertEquals(password, userDTO.getPassword());
		
		assertEquals(2, userDTO.getAccountDTOLights().size());
	}
	
	@Test
	@DisplayName("UserDTO should be correctly mapped to User")
	public void canBeMappedFromUserDTOtoUser()
	{
		String lastname = "Patrik";
		String firstname = "John";
		String username = "JohnnyGuitar";
		String password = "1";

		AccountDTOLight accountDTOLight1 = new AccountDTOLight(1, 100);
		AccountDTOLight accountDTOLight2 = new AccountDTOLight(2, 200);
		Set<AccountDTOLight> accDTOs = Set.of(accountDTOLight1, accountDTOLight2);

		UserDTO userDTO = new UserDTO(firstname, lastname, username, password, accDTOs);

		User user = userMapper.DTOtoUser(userDTO);
		
		assertEquals(lastname, user.getLastname());
		assertEquals(firstname, user.getFirstname());
		assertEquals(username, user.getUsername());
		assertEquals(password, user.getPassword());
		
		assertEquals(2, user.getAccounts().size());
	}

	@Test
	@DisplayName("List of UsertDTO should be correctly mapped to List of User")
	public void canBeMappedToUserList()
	{
		AccountDTOLight accountDTOLight1 = new AccountDTOLight(1L, 100);
		UserDTO userDTO1 = new UserDTO("Egor", "Topor", "Egrr", "123", Set.of(accountDTOLight1));

		AccountDTOLight accountDTOLight2 = new AccountDTOLight(2L, 200);
		UserDTO userDTO2 = new UserDTO("Anchi", "Fedorov", "Panch", "12345", Set.of(accountDTOLight2));

		List<User> userList = userMapper.DTOsToUsers(List.of(userDTO1, userDTO2));

		assertEquals(2, userList.size());

		assertEquals("Egor", userList.get(0).getFirstname());
		assertEquals("Topor", userList.get(0).getLastname());
		assertEquals("Egrr", userList.get(0).getUsername());
		assertEquals("123", userList.get(0).getPassword());
		assertEquals(1, userList.get(0).getAccounts().size());

		assertEquals("Anchi", userList.get(1).getFirstname());
		assertEquals("Fedorov", userList.get(1).getLastname());
		assertEquals("Panch", userList.get(1).getUsername());
		assertEquals("12345", userList.get(1).getPassword());
		assertEquals(1, userList.get(1).getAccounts().size());
	}

	@Test
	@DisplayName("List of User should be correctly mapped to List of UserDTO")
	public void canBeMappedToUserDTOList()
	{
		User user1 = new User("Egor", "Topor", "Egrr", "123", new HashSet<>());
		Account account1 = new Account(user1, 1L, 100);

		User user2 = new User("Anchi", "Fedorov", "Panch", "12345", new HashSet<>());
		Account account2 = new Account(user2, 1L, 100);

		List<UserDTO> userDTOList = userMapper.usersToDTOs(List.of(user1, user2));

		assertEquals(2, userDTOList.size());

		assertEquals("Egor", userDTOList.get(0).getFirstname());
		assertEquals("Topor", userDTOList.get(0).getLastname());
		assertEquals("Egrr", userDTOList.get(0).getUsername());
		assertEquals("123", userDTOList.get(0).getPassword());
		assertEquals(1, userDTOList.get(0).getAccountDTOLights().size());

		assertEquals("Anchi", userDTOList.get(1).getFirstname());
		assertEquals("Fedorov", userDTOList.get(1).getLastname());
		assertEquals("Panch", userDTOList.get(1).getUsername());
		assertEquals("12345", userDTOList.get(1).getPassword());
		assertEquals(1, userDTOList.get(1).getAccountDTOLights().size());
	}

	@Test
	@DisplayName("User can be updated from DTO")
	public void canBeUpdated()
	{
		User user = new User("firstname1", "lastname2", "username1", "password1", new HashSet<>());
		UserDTO userDTO = new UserDTO("newFirstname", "lastname2", "username1", "newPassword", new HashSet<>());

		userMapper.updateUserFromDTO(userDTO, user);

		assertEquals("newFirstname", user.getFirstname());
		assertEquals("lastname2", user.getLastname());
		assertEquals("username1", user.getUsername());
		assertEquals("newPassword", user.getPassword());
	}
}
