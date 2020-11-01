package com.zaycevImaginaryCompany.task.service;

import com.zaycevImaginaryCompany.task.dto.UserDTO;

public interface AccountCreator
{
    UserDTO createAccount(String username);
}
