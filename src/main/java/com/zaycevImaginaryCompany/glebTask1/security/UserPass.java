package com.zaycevImaginaryCompany.glebTask1.security;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserPass
{
	@NotBlank(message = "{validation.user.username.empty}")
	private String username;
	
	@NotBlank(message = "{validation.user.password.empty}")
	private String password;
}
