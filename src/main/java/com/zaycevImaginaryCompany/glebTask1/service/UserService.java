package com.zaycevImaginaryCompany.glebTask1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.zaycevImaginaryCompany.glebTask1.domain.User;

public interface UserService extends UserDetailsService
{
	List<User> findAll();

	List<User> findByLastname(String lastname);
	
	List<User> findByLastnameAndFirstname(String lastname, String firstname);
	
	Optional<User> findByUsername(String username);

	Optional<User> findById(Long id);

	boolean save(User user);

	void delete(User user);

}
