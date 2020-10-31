package com.zaycevImaginaryCompany.task.service;

import java.util.List;
import java.util.Optional;

import com.zaycevImaginaryCompany.task.domain.UserDTO;

public interface UserCRUDService
{
	List<UserDTO> findAll();

	Optional<UserDTO> findByUsername(String username);

	void create(UserDTO userDTO);

	void update(UserDTO userDTO);

	void delete(UserDTO userDTO);

}
