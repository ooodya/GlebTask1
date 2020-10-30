package com.zaycevImaginaryCompany.task.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import com.zaycevImaginaryCompany.task.domain.UserDTO;
import com.zaycevImaginaryCompany.task.exceptions.UserAlreadyExistsExseption;
import com.zaycevImaginaryCompany.task.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zaycevImaginaryCompany.task.domain.Account;
import com.zaycevImaginaryCompany.task.domain.User;
import com.zaycevImaginaryCompany.task.repository.AccountRepository;
import com.zaycevImaginaryCompany.task.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	UserMapper userMapper;

	@Override
	@Transactional(readOnly = true)
	public List<UserDTO> findAll()
	{
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return userMapper.usersToDTOs(users);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<UserDTO> findByUsername(String username)
	{
		final Optional<User> user = userRepository.findByUsername(username);
		return user.map(userMapper::userToDTO);
	}

	@Override
	public void create(UserDTO userDTO)
	{
		final Optional<User> dbUser = userRepository.findByUsername(userDTO.getUsername());
		if (dbUser.isPresent())
		{
			throw new UserAlreadyExistsExseption(userDTO.getUsername());
		}

		final User user = userMapper.DTOtoUser(userDTO);

		userRepository.save(user);

	}

	@Override
	public void update(UserDTO userDTO)
	{
		final Optional<User> user = userRepository.findByUsername(userDTO.getUsername());
		if (user.isEmpty())
		{
			throw new UserNotFoundException(userDTO.getUsername());
		}

		userMapper.updateUserFromDTO(userDTO, user.get());

		userRepository.save(user.get());
	}

	@Override
	public void delete(UserDTO userDTO)
	{
		final Optional<User> user = userRepository.findByUsername(userDTO.getUsername());
		user.ifPresent(userRepository::delete);
	}
}
