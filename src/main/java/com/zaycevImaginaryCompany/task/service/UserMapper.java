package com.zaycevImaginaryCompany.task.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.zaycevImaginaryCompany.task.domain.User;
import com.zaycevImaginaryCompany.task.domain.UserDTO;

@Mapper(componentModel = "spring", uses = AccountMapper.class)
public interface UserMapper
{
	@Mapping(source = "accounts", target = "accountDTOs")
	UserDTO userToDTO(User user);
	
	@Mapping(source = "accountDTOs", target = "accounts")
	User DTOtoUser(UserDTO userDTO);
}
