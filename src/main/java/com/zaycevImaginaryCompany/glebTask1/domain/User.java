package com.zaycevImaginaryCompany.glebTask1.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Entity
public class User implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -122259508010801586L;
	
	@Setter(AccessLevel.NONE)
	@Id
	@GeneratedValue
	private Long id;
	
	private String firstname;
	
	private String lastname;
	
	private String username;

	public User(String firstname, String lastname, String username)
	{
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
	}
	
}
