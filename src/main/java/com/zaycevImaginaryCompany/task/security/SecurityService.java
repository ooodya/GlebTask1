package com.zaycevImaginaryCompany.task.security;

public interface SecurityService
{
	String getLoggedInUsername();

    void autoLogin(String username, String password);
}
