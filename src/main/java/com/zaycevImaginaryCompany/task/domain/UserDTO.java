package com.zaycevImaginaryCompany.task.domain;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO
{
	@NotBlank(message = "{validation.user.firstname.empty}")
	private String firstname;

	@NotBlank(message = "{validation.user.lastname.empty}")
	private String lastname;

	@NotBlank(message = "{validation.user.username.empty}")
	private String username;

	@NotBlank(message = "{validation.user.password.empty}")
	private String password;
	
	private Set<AccountDTOLight> accountDTOLights = new HashSet<>();
}
