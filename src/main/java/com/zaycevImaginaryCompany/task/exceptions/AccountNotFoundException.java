package com.zaycevImaginaryCompany.task.exceptions;

public class AccountNotFoundException extends RuntimeException
{
    public AccountNotFoundException(Long accountNumber)
    {
        super("There is no account with number " + accountNumber);
    }
}
