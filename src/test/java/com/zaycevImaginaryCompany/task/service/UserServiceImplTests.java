package com.zaycevImaginaryCompany.task.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.zaycevImaginaryCompany.task.domain.*;
import com.zaycevImaginaryCompany.task.exceptions.UserAlreadyExistsExseption;
import com.zaycevImaginaryCompany.task.exceptions.UserNotFoundException;
import com.zaycevImaginaryCompany.task.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
public class UserServiceImplTests
{
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountService accountService;

    @Test
    @DisplayName("All users can be found")
    public void findAllShouldReturnAllUsers()
    {
        UserDTO userDTO1 = new UserDTO("firstname1", "lastname1", "username1", "password1", new HashSet<>());
        UserDTO userDTO2 = new UserDTO("firstname2", "lastname2", "username2", "password2", new HashSet<>());
        userService.create(userDTO1);
        userService.create(userDTO2);

        final List<UserDTO> allUsers = userService.findAll();

        assertEquals(2, allUsers.size());

        assertEquals("firstname1", allUsers.get(0).getFirstname());
        assertEquals("lastname1", allUsers.get(0).getLastname());
        assertEquals("username1", allUsers.get(0).getUsername());
        assertEquals("password1", allUsers.get(0).getPassword());

        assertEquals("firstname2", allUsers.get(1).getFirstname());
        assertEquals("lastname2", allUsers.get(1).getLastname());
        assertEquals("username2", allUsers.get(1).getUsername());
        assertEquals("password2", allUsers.get(1).getPassword());

        userService.delete(userDTO1);
        userService.delete(userDTO2);
    }

    @Test
    @DisplayName("User can be found by username if exists")
    public void findByUsernameShouldReturnIfExists()
    {
        UserDTO userDTO1 = new UserDTO("firstname1", "lastname1", "username1", "password1", new HashSet<>());
        userService.create(userDTO1);
        final Optional<UserDTO> userDTOOptional = userService.findByUsername("username1");

        assertTrue(userDTOOptional.isPresent());

        UserDTO userDTO = userDTOOptional.get();

        assertEquals("firstname1", userDTO.getFirstname());
        assertEquals("lastname1", userDTO.getLastname());
        assertEquals("username1", userDTO.getUsername());
        assertEquals("password1", userDTO.getPassword());

        userService.delete(userDTO1);
    }

    @Test
    @DisplayName("empty optional is returned if no user with such username exists")
    public void findByUsernameShouldReturnEmptyOptionalIfNotExists()
    {
        final Optional<UserDTO> userDTOOptional = userService.findByUsername("username1");

        assertTrue(userDTOOptional.isEmpty());
    }

    @Test
    @DisplayName("User can be created")
    public void canBeSaved()
    {
        AccountDTOLight accountDTOLight1 = new AccountDTOLight(1l, 100);
        AccountDTOLight accountDTOLight2 = new AccountDTOLight(2l, 200);
        UserDTO userDTO = new UserDTO("firstname1", "lastname1", "username1", "password1",
                Set.of(accountDTOLight1, accountDTOLight2));

        userService.create(userDTO);

        Optional<UserDTO> userDTOFromDbOptional = userService.findByUsername("username1");

        assertTrue(userDTOFromDbOptional.isPresent());

        UserDTO userDTOFromDb = userDTOFromDbOptional.get();

        assertEquals("firstname1", userDTOFromDb.getFirstname());
        assertEquals("lastname1", userDTOFromDb.getLastname());
        assertEquals("username1", userDTOFromDb.getUsername());
        assertEquals("password1", userDTOFromDb.getPassword());

        assertEquals(2, userDTOFromDb.getAccountDTOLights().size());

        userService.delete(userDTO);
    }

    @Test
    @DisplayName("throws UserAlreadyExistException for already existing username")
    public void throwsUserAlreadyExistExceptionIfUserWIthThisUsernameExists()
    {
        UserDTO userDTO1 = new UserDTO("firstname1", "lastname1", "username1", "password1", new HashSet<>());
        userService.create(userDTO1);

        UserDTO userDTO2 = new UserDTO("firstname2", "lastname2", "username1", "password2", new HashSet<>());

        assertThrows(UserAlreadyExistsExseption.class, () -> userService.create(userDTO2));

        userService.delete(userDTO1);
    }

    @Test
    @DisplayName("User can be updated")
    public void canBeUpdated()
    {
        AccountDTOLight accountDTOLight1 = new AccountDTOLight(1l, 100);
        AccountDTOLight accountDTOLight2 = new AccountDTOLight(2l, 200);
        UserDTO userDTO = new UserDTO("firstname1", "lastname1", "username1", "password1",
                Set.of(accountDTOLight1, accountDTOLight2));

        userService.create(userDTO);

        userDTO.setFirstname("newName");
        userDTO.setPassword("newPassword");

        userService.update(userDTO);

        Optional<UserDTO> userDTOFromDbOptional = userService.findByUsername("username1");

        UserDTO userDTOFromDb = userDTOFromDbOptional.get();

        assertEquals("newName", userDTOFromDb.getFirstname());
        assertEquals("lastname1", userDTOFromDb.getLastname());
        assertEquals("username1", userDTOFromDb.getUsername());
        assertEquals("newPassword", userDTOFromDb.getPassword());

        assertEquals(2, userDTOFromDb.getAccountDTOLights().size());

        userService.delete(userDTO);
    }

    @Test
    @DisplayName("throws UserNotFoundException for non existing user")
    public void throwsUserNotFoundExceptionIfNoSuchUserExists()
    {
        UserDTO userDTO1 = new UserDTO("firstname1", "lastname1", "username1", "password1", new HashSet<>());

        assertThrows(UserNotFoundException.class, () -> userService.update(userDTO1));

    }

    @Test
    @DisplayName("User can be deleted with all its accounts")
    public void canBeDeleted()
    {
        AccountDTOLight accountDTOLight1 = new AccountDTOLight(1l, 100);
        AccountDTOLight accountDTOLight2 = new AccountDTOLight(2l, 200);
        UserDTO userDTO = new UserDTO("firstname1", "lastname1", "username1", "password1",
                Set.of(accountDTOLight1, accountDTOLight2));

        userService.create(userDTO);

        final List<UserDTO> allUsers = userService.findAll();
        assertEquals(1, allUsers.size());

        userService.delete(userDTO);

        final List<UserDTO> allUsersAfterDelete = userService.findAll();
        assertEquals(0, allUsersAfterDelete.size());

        final List<AccountDTO> allAccounts = accountService.findAll();
        assertEquals(0, allAccounts.size());
    }
}
