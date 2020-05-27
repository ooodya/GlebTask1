package com.zaycevImaginaryCompany.glebTask1.domain;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class User implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -122259508010801586L;
	
	@Setter(AccessLevel.NONE)
	private Long id;
	
	private String firstname;
	
	private String lastname;
	
	private String username;

	
}
