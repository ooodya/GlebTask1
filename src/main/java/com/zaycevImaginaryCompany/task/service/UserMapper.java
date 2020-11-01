package com.zaycevImaginaryCompany.task.service;

import org.mapstruct.*;

import com.zaycevImaginaryCompany.task.domain.User;
import com.zaycevImaginaryCompany.task.dto.UserDTO;

import java.util.List;

@Mapper(componentModel = "spring", uses = AccountLightMapper.class)
public interface UserMapper
{
	@Mapping(source = "accounts", target = "accountDTOLites")
	UserDTO userToDTO(User user);
	
	@Mapping(source = "accountDTOLites", target = "accounts")
	User DTOtoUser(UserDTO userDTO);

	@Mapping(source = "accounts", target = "accountDTOLites")
	List<UserDTO> usersToDTOs(List<User> user);

	@Mapping(source = "accountDTOLites", target = "accounts")
	List<User> DTOsToUsers(List<UserDTO> user);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateUserFromDTO(UserDTO userDTO, @MappingTarget User user);
}
