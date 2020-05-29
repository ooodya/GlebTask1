package com.zaycevImaginaryCompany.glebTask1.security;

public interface SecurityService
{
	String getLoggedInUsername();

    void autoLogin(String username, String password);
}
