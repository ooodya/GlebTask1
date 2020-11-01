package com.zaycevImaginaryCompany.task.controller;

import com.zaycevImaginaryCompany.task.service.AccountCRUDService;
import com.zaycevImaginaryCompany.task.service.AccountCreator;
import com.zaycevImaginaryCompany.task.service.UserCRUDService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser("username")
class UserAccountsControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountCRUDService accountCRUDService;

    @MockBean
    private UserCRUDService userCRUDService;

    @MockBean
    private AccountCreator accountCreator;

    @Test
    @DisplayName("userCRUDService.findByUsername() is called when get /userAccounts")
    @SneakyThrows
    public void accountCRUDServiceIsCalledWhenGettingUserAccounts()
    {
        mockMvc.perform(get("/userAccounts").param("username", "username"));

        Mockito.verify(userCRUDService).findByUsername("username");
    }

    @Test
    @DisplayName("AccountCRUDService.findByAccountNumber() is called when choosing account")
    @SneakyThrows
    public void accountCRUDServiceIsCalledWhenChoosingAccount()
    {
        mockMvc.perform(post("/chooseAccount").param("accountNumber", "1"));

        Mockito.verify(accountCRUDService).findByAccountNumber(1);
    }

    @Test
    @DisplayName("AccountCreator.createAccount() is called when adding account")
    @SneakyThrows
    public void accountCreatorIsCalledWhenAddingAccount()
    {
        mockMvc.perform(post("/addAccount").param("username", "username"));

        Mockito.verify(accountCreator).createAccount("username");
    }
}