package com.zaycevImaginaryCompany.task.service;

import com.zaycevImaginaryCompany.task.domain.AccountDTO;
import com.zaycevImaginaryCompany.task.domain.AccountDTOLight;
import com.zaycevImaginaryCompany.task.domain.UserDTO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class AccountTransferServiceTests
{
    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountTransferService transferService;

    private final Long destinationAccountNumber = 1L;
    private final int destinationAmount = 1000;
    private final Long sourceAccountNumber = 2L;
    private final int sourceAmount = 2000;

    private final AccountDTOLight accountDTOLight1 = new AccountDTOLight(destinationAccountNumber, destinationAmount);
    private final AccountDTOLight accountDTOLight2 = new AccountDTOLight(sourceAccountNumber, sourceAmount);
    private final UserDTO userDTO = new UserDTO("firstame", "lastname", "username", "password",
            Set.of(accountDTOLight1, accountDTOLight2));

    @BeforeEach
    public void setUp()
    {
        userService.create(userDTO);
    }

    @AfterEach
    public void cleanUp()
    {
        userService.delete(userDTO);
    }

    @Test
    @DisplayName("Transfer fails if transfer amount < 0")
    public void transferFailsIfNegativeAmount()
    {
        assertFalse(transferService.transfer(destinationAccountNumber, sourceAccountNumber, -100));
    }

    @Test
    @DisplayName("Transfer fails if wrong source account")
    public void transferFailsIfNoSuchSourceExists()
    {
        assertFalse(transferService.transfer(destinationAccountNumber, 4656234234L, 100));
    }

    @Test
    @DisplayName("Transfer fails if wrong destination account")
    public void transferFailsIfNoSuchDestinationExists()
    {
        assertFalse(transferService.transfer(867435233L, sourceAccountNumber, 100));
    }

    @Test
    @DisplayName("Transfer fails if source dont have enough money")
    public void transferFailsIfSourceHasNotEnoughMoney()
    {
        assertFalse(transferService.transfer(destinationAccountNumber, sourceAccountNumber, sourceAmount + 1000));
    }

    @Test
    @DisplayName("Money can be transferred")
    public void canBeTransferred()
    {
        int amount = 200;

        assertTrue(transferService.transfer(destinationAccountNumber, sourceAccountNumber, amount));

        Optional<AccountDTO> sourceAccountDTO = accountService.findByAccountNumber(sourceAccountNumber);
        Optional<AccountDTO> destinationAccountDTO = accountService.findByAccountNumber(destinationAccountNumber);

        assertEquals(sourceAmount - amount, sourceAccountDTO.get().getAmount());
        assertEquals(destinationAmount + amount, destinationAccountDTO.get().getAmount());
    }
}
