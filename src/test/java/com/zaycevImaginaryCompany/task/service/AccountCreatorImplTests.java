package com.zaycevImaginaryCompany.task.service;

import com.zaycevImaginaryCompany.task.domain.AccountDTO;
import com.zaycevImaginaryCompany.task.domain.UserDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountCreatorImplTests
{
    @Autowired
    private AccountCRUDService accountCRUDService;

    @Autowired
    private UserCRUDService userCRUDService;

    @Autowired
    private AccountCreator accountCreator;

    final UserDTO userDTO = new UserDTO("firstname", "lastname", "username", "password", new HashSet<>());

    @BeforeEach
    public void setUp()
    {
        userCRUDService.create(userDTO);
    }

    @AfterEach
    public void cleanUp()
    {
        userCRUDService.delete(userDTO);
    }

    @Test
    void canBeCreated()
    {
        final UserDTO updatedUserDTO = accountCreator.createAccount("username");

        assertEquals(1, updatedUserDTO.getAccountDTOLights().size());

        final List<AccountDTO> allAccounts = accountCRUDService.findAll();

        assertEquals(1, allAccounts.size());
    }
}