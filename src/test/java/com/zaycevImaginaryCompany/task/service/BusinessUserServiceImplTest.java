package com.zaycevImaginaryCompany.task.service;

import com.zaycevImaginaryCompany.task.dto.UserDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser("username")
class BusinessUserServiceImplTest
{
    @Autowired
    private UserCRUDService userCRUDService;

    @Autowired
    private BusinessUserService businessUserService;

    @Test
    @DisplayName("Logged user can be found")
    public void canFindLoggedUser()
    {
        UserDTO userDTO1 = new UserDTO("firstname", "lastname", "username", "password", new HashSet<>());
        userCRUDService.create(userDTO1);

        final UserDTO loggedUserDTO = businessUserService.findLoggedUser();

        assertEquals("firstname", loggedUserDTO.getFirstname());
        assertEquals("lastname", loggedUserDTO.getLastname());

        userCRUDService.delete(userDTO1);
    }
}