package com.zaycevImaginaryCompany.task.controller;

import com.zaycevImaginaryCompany.task.service.AccountTransferService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(AccountController.class)
class AccountControllerTests
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountTransferService transferService;

    @Test
    @DisplayName("AccountTransferService.addMoney() is called when adding money to account")
    @SneakyThrows
    public void transferServiceIsCalledWhenAddingMoney()
    {
        Long accountNumber = 1L;
        int amountToAdd = 1000;

        RequestBuilder request = post("/addAmount")
                .param("accountNumber", accountNumber.toString())
                .param("amount", "0")
                .param("amountToAdd", Integer.toString(amountToAdd));

        mockMvc.perform(request);

        Mockito.verify(transferService).addMoney(accountNumber, amountToAdd);

    }

    @Test
    @DisplayName("AccountTransferService.transfer() is called when transferring money to account")
    @SneakyThrows
    public void transferServiceIsCalledWhenTransferringMoney()
    {
        Long accountNumber = 1L;
        int transferAmount = 1000;
        long destinationAccountNumber = 2L;

        RequestBuilder request = post("/transferMoney")
                .param("accountNumber", accountNumber.toString())
                .param("amount", "0")
                .param("destinationAccountNumber", Long.toString(destinationAccountNumber))
                .param("transferAmount", Integer.toString(transferAmount));

        mockMvc.perform(request);

        Mockito.verify(transferService).transfer(destinationAccountNumber, accountNumber, transferAmount);
    }
}