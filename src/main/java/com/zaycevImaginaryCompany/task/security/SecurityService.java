package com.zaycevImaginaryCompany.task.security;

import java.util.Optional;

public interface SecurityService
{
    Optional<String> getLoggedUsername();

    void loginAfterRegister(String username, String password);
}
