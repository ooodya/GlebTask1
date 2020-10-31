package com.zaycevImaginaryCompany.task.security;

public interface SecurityService
{
    String getLoggedUsername();

    void loginAfterRegister(String username, String password);
}
