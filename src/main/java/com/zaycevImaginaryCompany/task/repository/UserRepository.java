package com.zaycevImaginaryCompany.task.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.zaycevImaginaryCompany.task.domain.User;

public interface UserRepository extends CrudRepository<User, Long>
{
	Optional<User> findByUsername(String username);
	
	List<User> findByLastname(String lastname);
	
	List<User> findByLastnameAndFirstname(String lastname, String firstname);
}
