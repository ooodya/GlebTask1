package com.zaycevImaginaryCompany.glebTask1.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.lang.NonNull;

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
	
	@NonNull
	private String firstname;
	
	@NonNull
	private String lastname;
	
	@NonNull
	private String username;

	public User(String lastname, String firstname, String username)
	{
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
	}
	
}
