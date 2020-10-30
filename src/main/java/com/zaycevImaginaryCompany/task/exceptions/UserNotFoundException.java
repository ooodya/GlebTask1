package com.zaycevImaginaryCompany.task.exceptions;

public class UserNotFoundException extends RuntimeException
{
    public UserNotFoundException (String username)
    {
        super ("There is no user with username " + username);
    }
}
