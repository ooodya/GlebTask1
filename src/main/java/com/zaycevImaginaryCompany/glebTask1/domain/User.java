package com.zaycevImaginaryCompany.glebTask1.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
	
	@Setter(AccessLevel.NONE)
	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
	private Set<Account> accounts = new HashSet<>();

	public User(String lastname, String firstname, String username)
	{
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
	}
	
	public void addAccount(Account account)
	{
		if (!accounts.contains(account))
		{
			accounts.add(account);
		}
		if (account.getOwner() == null)
		{
			account.addOwner(this);
		}
	}
	
}
