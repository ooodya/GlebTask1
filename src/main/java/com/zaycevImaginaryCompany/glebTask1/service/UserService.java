package com.zaycevImaginaryCompany.glebTask1.service;

import java.util.List;
import java.util.Optional;

import com.zaycevImaginaryCompany.glebTask1.domain.User;

public interface UserService
{
	List<User> findAll();

	List<User> findByLastname(String lastname);
	
	List<User> findByLastnameAndFirstname(String lastname, String firstname);
	
	Optional<User> findByUserName(String username);

	Optional<User> findById(Long id);

	User save(User user);

	void delete(User user);

}
