package com.zaycevImaginaryCompany.task.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import com.zaycevImaginaryCompany.task.domain.*;
import com.zaycevImaginaryCompany.task.exceptions.AccountNotFoundException;
import com.zaycevImaginaryCompany.task.repository.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class AccountServiceImplTests
{
    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    @DisplayName("All accounts can be found")
    public void findAllShouldReturnAllAccounts()
    {
        UserDTOLight userDTOLight = new UserDTOLight("firstname1", "lastname1", "username1", "password1");
        AccountDTO accountDTO1 = new AccountDTO(userDTOLight, 1L, 100);
        AccountDTO accountDTO2 = new AccountDTO(userDTOLight, 2L, 200);

        accountService.create(accountDTO1);
        accountService.create(accountDTO2);

        final List<AccountDTO> allAccountDTOs = accountService.findAll();

        assertEquals(2, allAccountDTOs.size());

        accountService.delete(accountDTO1);
        accountService.delete(accountDTO2);

    }

    @Test
    @DisplayName("Account can be found by account number if exists")
    public void findByAccountNumberShouldReturnIfExists()
    {
        UserDTOLight userDTOLight = new UserDTOLight("firstname2", "lastname2", "username2", "passwor2");
        AccountDTO accountDTO = new AccountDTO(userDTOLight, 3L, 100);

        accountService.create(accountDTO);

        final Optional<AccountDTO> foundAccountDTO = accountService.findByAccountNumber(3L);

        assertTrue(foundAccountDTO.isPresent());
        assertEquals(100, foundAccountDTO.get().getAmount());

        accountService.delete(accountDTO);
    }

    @Test
    @DisplayName("Empty optional returns if no account with such number exists")
    public void findByAccountNumberShouldReturnEmptyOptionalIfNotExists()
    {
        UserDTOLight userDTOLight = new UserDTOLight("firstname3", "lastname3", "username3", "password3");
        AccountDTO accountDTO = new AccountDTO(userDTOLight, 4L, 100);

        final Optional<AccountDTO> foundAccountDTO = accountService.findByAccountNumber(4L);

        assertTrue(foundAccountDTO.isEmpty());

    }

    @Test
    @DisplayName("Account can be saved")
    public void canBeSaved()
    {
        UserDTOLight userDTOLight = new UserDTOLight("firstname5", "lastname5", "username5", "password5");
        AccountDTO accountDTO = new AccountDTO(userDTOLight, 6L, 100);

        accountService.create(accountDTO);

        final Optional<AccountDTO> foundAccountDTO = accountService.findByAccountNumber(6L);

        assertTrue(foundAccountDTO.isPresent());
        assertEquals(100, foundAccountDTO.get().getAmount());

        accountService.delete(accountDTO);
    }

    @Test
    @DisplayName("Account can be updated")
    public void canBeUpdated()
    {
        UserDTOLight userDTOLight = new UserDTOLight("firstname6", "lastname6", "username6", "password6");
        AccountDTO accountDTO = new AccountDTO(userDTOLight, 7L, 100);

        accountService.create(accountDTO);

        accountDTO.setAmount(200);

        accountService.update(accountDTO);

        final Optional<AccountDTO> foundAccountDTO = accountService.findByAccountNumber(7L);

        assertTrue(foundAccountDTO.isPresent());
        assertEquals(200, foundAccountDTO.get().getAmount());

        accountService.delete(accountDTO);
    }

    @Test
    @DisplayName("if account for updating not found throw AccountNotFoundException")
    public void trowsAccountNotFoundExceptionIfNoAccountFOrUpdating()
    {
        AccountDTO accountDTO = new AccountDTO(new UserDTOLight(), 4783123123554L, 100);

        assertThrows(AccountNotFoundException.class, () -> accountService.update(accountDTO));
    }

    @Test
    @DisplayName("Account can be deleted")
    public void canBeDeleted()
    {
        UserDTOLight userDTOLight = new UserDTOLight("firstname7", "lastname7", "username7", "password7");
        AccountDTO accountDTO = new AccountDTO(userDTOLight, 8L, 100);

        accountService.create(accountDTO);

        accountService.delete(accountDTO);

        final Optional<AccountDTO> foundAccountDTO = accountService.findByAccountNumber(8L);

        assertTrue(foundAccountDTO.isEmpty());
    }
}
