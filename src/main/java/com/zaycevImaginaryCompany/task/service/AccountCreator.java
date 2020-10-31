package com.zaycevImaginaryCompany.task.service;

import com.zaycevImaginaryCompany.task.domain.UserDTO;

public interface AccountCreator
{
    UserDTO createAccount(String username);
}
