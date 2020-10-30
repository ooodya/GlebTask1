package com.zaycevImaginaryCompany.task.exceptions;

public class UserAlreadyExistsExseption extends RuntimeException
{
    public UserAlreadyExistsExseption(String username)
    {
        super("User with username " + username + " already exists");
    }
}
