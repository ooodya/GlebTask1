package com.zaycevImaginaryCompany.task.service;

import java.util.List;
import java.util.Optional;

import com.zaycevImaginaryCompany.task.domain.User;

public interface UserService
{
	List<User> findAll();

	Optional<User> findByUsername(String username);

	Optional<User> findById(Long id);

	boolean save(User user);

	void delete(User user);

}
