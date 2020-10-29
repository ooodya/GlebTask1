package com.zaycevImaginaryCompany.task.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.zaycevImaginaryCompany.task.domain.Account;
import com.zaycevImaginaryCompany.task.domain.AccountDTO;
import com.zaycevImaginaryCompany.task.domain.User;
import com.zaycevImaginaryCompany.task.domain.UserDTO;
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
		new Account(user, 1L, 0);
		new Account(user, 2L, 0);
		
		UserDTO userDTO = userMapper.userToDTO(user, new CycleAvoidingMappingContext());
		
		assertEquals(lastname, userDTO.getLastname());
		assertEquals(firstname, userDTO.getFirstname());
		assertEquals(username, userDTO.getUsername());
		assertEquals(password, userDTO.getPassword());
		
		assertEquals(2, userDTO.getAccountDTOs().size());
	}
	
	@Test
	@DisplayName("UserDTO should be correctly mapped to User")
	public void canBeMappedFromUserDTOtoUser()
	{
		String lastname = "Patrik";
		String firstname = "John";
		String username = "JohnnyGuitar";
		String password = "1";

		UserDTO userDTO = new UserDTO(firstname, lastname, username, password, new HashSet<>());

		AccountDTO accDTO1 = new AccountDTO(userDTO, 1, 100);
		AccountDTO accDTO2 = new AccountDTO(userDTO, 2, 200);
		Set<AccountDTO> accDTOs = Set.of(accDTO1, accDTO2);
		userDTO.setAccountDTOs(accDTOs);

		User user = userMapper.DTOtoUser(userDTO, new CycleAvoidingMappingContext());
		
		assertEquals(lastname, user.getLastname());
		assertEquals(firstname, user.getFirstname());
		assertEquals(username, user.getUsername());
		assertEquals(password, user.getPassword());
		
		assertEquals(2, user.getAccounts().size());
		
	}
}
