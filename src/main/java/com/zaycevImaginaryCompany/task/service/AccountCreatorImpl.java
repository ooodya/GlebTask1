package com.zaycevImaginaryCompany.task.service;

import com.zaycevImaginaryCompany.task.dto.AccountDTO;
import com.zaycevImaginaryCompany.task.dto.UserDTO;
import com.zaycevImaginaryCompany.task.dto.UserDTOLite;
import com.zaycevImaginaryCompany.task.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class AccountCreatorImpl implements AccountCreator
{
    @Autowired
    AccountCRUDService accountCRUDService;

    @Autowired
    UserCRUDService userCRUDService;

    @Override
    public UserDTO createAccount(String username)
    {
        final Optional<UserDTO> foundUserDTOOptional = userCRUDService.findByUsername(username);

        if (foundUserDTOOptional.isEmpty())
        {
            throw new UserNotFoundException(username);
        }

        UserDTO foundUserDTO = foundUserDTOOptional.get();
        AccountDTO accountDTO = new AccountDTO(new UserDTOLite(foundUserDTO.getFirstname(), foundUserDTO.getLastname(), foundUserDTO.getUsername(), foundUserDTO.getPassword()),
                generateAccountNumber(), 0);

        accountCRUDService.create(accountDTO);

        return userCRUDService.findByUsername(foundUserDTO.getUsername()).get();
    }

    private long generateAccountNumber()
    {
        long accountNumber = ThreadLocalRandom.current().nextLong(1000000L, 9999999L);
        while (accountCRUDService.findByAccountNumber(accountNumber).isPresent())
        {
            accountNumber = ThreadLocalRandom.current().nextLong(1000000L, 9999999L);
        }

        return accountNumber;
    }
}
