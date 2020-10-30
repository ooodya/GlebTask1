package com.zaycevImaginaryCompany.task.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaycevImaginaryCompany.task.domain.UserDTO;
import com.zaycevImaginaryCompany.task.service.UserService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashSet;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTests
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    @SneakyThrows
    public void controllerListensToRegisterGetRequest()
    {
        mockMvc.perform(get("/register")).andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    public void controllerListensToLoginGetRequest()
    {
        mockMvc.perform(get("/login")).andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    public void testUserServiceIsCalled()
    {
        UserDTO userDTO = new UserDTO("firstname", "lastname", "username", "password", new HashSet<>());

        RequestBuilder request = post("/register")
                .param("firstname", "firstname")
                .param("lastname", "lastname")
                .param("username", "")
                .param("password","password");

        mockMvc.perform(request).andExpect(status().isBadRequest());

        Mockito.verify(userService).create(userDTO);
    }

    @Test
    @SneakyThrows
    public void userDTOIsValidated()
    {
        RequestBuilder request = post("/register")
                .param("firstname", "firstname")
                .param("lastname", "lastname")
                .param("username", "")
                .param("password","password");

        mockMvc.perform(request).andExpect(status().isBadRequest());
    }
}
