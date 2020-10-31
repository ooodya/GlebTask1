package com.zaycevImaginaryCompany.task.service;

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
public class AccountCRUDServiceImplTests
{
    @Autowired
    private AccountCRUDService accountCRUDService;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    @DisplayName("All accounts can be found")
    public void findAllShouldReturnAllAccounts()
    {
        UserDTOLight userDTOLight = new UserDTOLight("firstname1", "lastname1", "username1", "password1");
        AccountDTO accountDTO1 = new AccountDTO(userDTOLight, 1L, 100);
        AccountDTO accountDTO2 = new AccountDTO(userDTOLight, 2L, 200);

        accountCRUDService.create(accountDTO1);
        accountCRUDService.create(accountDTO2);

        final List<AccountDTO> allAccountDTOs = accountCRUDService.findAll();

        assertEquals(2, allAccountDTOs.size());

        accountCRUDService.delete(accountDTO1);
        accountCRUDService.delete(accountDTO2);

    }

    @Test
    @DisplayName("Account can be found by account number if exists")
    public void findByAccountNumberShouldReturnIfExists()
    {
        UserDTOLight userDTOLight = new UserDTOLight("firstname2", "lastname2", "username2", "passwor2");
        AccountDTO accountDTO = new AccountDTO(userDTOLight, 3L, 100);

        accountCRUDService.create(accountDTO);

        final Optional<AccountDTO> foundAccountDTO = accountCRUDService.findByAccountNumber(3L);

        assertTrue(foundAccountDTO.isPresent());
        assertEquals(100, foundAccountDTO.get().getAmount());

        accountCRUDService.delete(accountDTO);
    }

    @Test
    @DisplayName("Empty optional returns if no account with such number exists")
    public void findByAccountNumberShouldReturnEmptyOptionalIfNotExists()
    {
        UserDTOLight userDTOLight = new UserDTOLight("firstname3", "lastname3", "username3", "password3");

        final Optional<AccountDTO> foundAccountDTO = accountCRUDService.findByAccountNumber(4L);

        assertTrue(foundAccountDTO.isEmpty());

    }

    @Test
    @DisplayName("Account can be saved")
    public void canBeSaved()
    {
        UserDTOLight userDTOLight = new UserDTOLight("firstname5", "lastname5", "username5", "password5");
        AccountDTO accountDTO = new AccountDTO(userDTOLight, 6L, 100);

        accountCRUDService.create(accountDTO);

        final Optional<AccountDTO> foundAccountDTO = accountCRUDService.findByAccountNumber(6L);

        assertTrue(foundAccountDTO.isPresent());
        assertEquals(100, foundAccountDTO.get().getAmount());

        accountCRUDService.delete(accountDTO);
    }

    @Test
    @DisplayName("Account can be updated")
    public void canBeUpdated()
    {
        UserDTOLight userDTOLight = new UserDTOLight("firstname6", "lastname6", "username6", "password6");
        AccountDTO accountDTO = new AccountDTO(userDTOLight, 7L, 100);

        accountCRUDService.create(accountDTO);

        accountDTO.setAmount(200);

        accountCRUDService.update(accountDTO);

        final Optional<AccountDTO> foundAccountDTO = accountCRUDService.findByAccountNumber(7L);

        assertTrue(foundAccountDTO.isPresent());
        assertEquals(200, foundAccountDTO.get().getAmount());

        accountCRUDService.delete(accountDTO);
    }

    @Test
    @DisplayName("if account for updating not found throw AccountNotFoundException")
    public void trowsAccountNotFoundExceptionIfNoAccountFOrUpdating()
    {
        AccountDTO accountDTO = new AccountDTO(new UserDTOLight(), 4783123123554L, 100);

        assertThrows(AccountNotFoundException.class, () -> accountCRUDService.update(accountDTO));
    }

    @Test
    @DisplayName("Account can be deleted")
    public void canBeDeleted()
    {
        UserDTOLight userDTOLight = new UserDTOLight("firstname7", "lastname7", "username7", "password7");
        AccountDTO accountDTO = new AccountDTO(userDTOLight, 8L, 100);

        accountCRUDService.create(accountDTO);

        accountCRUDService.delete(accountDTO);

        final Optional<AccountDTO> foundAccountDTO = accountCRUDService.findByAccountNumber(8L);

        assertTrue(foundAccountDTO.isEmpty());
    }
}
