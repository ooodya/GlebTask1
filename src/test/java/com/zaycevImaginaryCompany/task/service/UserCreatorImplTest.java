package com.zaycevImaginaryCompany.task.service;

import com.zaycevImaginaryCompany.task.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserCreatorImplTest
{
    @Autowired
    private UserCRUDService userCRUDService;

    @Autowired
    private UserCreator userCreator;

    @Test
    void canCreateUser()
    {
        UserDTO userDTO = new UserDTO("firstname", "lastname", "username", "password", new HashSet<>());
        userCreator.createUser(userDTO);

        final Optional<UserDTO> userDTOOptional = userCRUDService.findByUsername("username");

        assertTrue(userDTOOptional.isPresent());

        userCRUDService.delete(userDTO);
    }
}