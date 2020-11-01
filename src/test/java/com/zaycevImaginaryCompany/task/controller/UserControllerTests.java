package com.zaycevImaginaryCompany.task.controller;

import com.zaycevImaginaryCompany.task.dto.UserDTO;
import com.zaycevImaginaryCompany.task.service.UserCRUDService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.HashSet;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserCRUDService userCRUDService;

    @Test
    @DisplayName("Controller handles get request to /register")
    @SneakyThrows
    public void controllerListensToRegisterGetRequest()
    {
        mockMvc.perform(get("/register")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Controller handles get request to /login")
    @SneakyThrows
    public void controllerListensToLoginGetRequest()
    {
        mockMvc.perform(get("/login")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("UserCRUDService is called when registering user")
    @SneakyThrows
    public void testUserServiceIsCalled()
    {
        RequestBuilder request = post("/register")
                .param("firstname", "firstname")
                .param("lastname", "lastname")
                .param("username", "username")
                .param("password","password");

        mockMvc.perform(request);

        ArgumentCaptor<UserDTO> userDtoCaptor = ArgumentCaptor.forClass(UserDTO.class);
        Mockito.verify(userCRUDService).create(userDtoCaptor.capture());
    }

    @Test
    @DisplayName("UserDTO is validated")
    @SneakyThrows
    public void userDTOIsValidated()
    {
        RequestBuilder request = post("/register")
                .param("firstname", "")
                .param("lastname", "")
                .param("username", "")
                .param("password","");

        mockMvc.perform(request).andExpect(model().attributeHasFieldErrors("userDTO",
                "firstname", "lastname", "username", "password"));
    }
}
