package com.zaycevImaginaryCompany.task.domain;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Long id;

	@Column(name = "firstname")
	private String firstname;

	@Column(name = "lastname")
	private String lastname;

	@Column(name = "username", unique = true, nullable = false)
	@NotBlank(message = "{validation.user.username.empty}")
	private String username;

	@Column(name = "password", nullable = false)
	@NotBlank(message = "{validation.user.password.empty}")
	private String password;

	@Setter(AccessLevel.NONE)
	@Getter(AccessLevel.NONE)
	@OneToMany(mappedBy = "owner", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	//@org.hibernate.annotations.OnDelete(action = org.hibernate.annotations.OnDeleteAction.CASCADE)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Account> accounts = new HashSet<>();

	public User(String firstname, String lastname, String username, String password, Set<Account> accounts)
	{
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		
		accounts.forEach(a -> a.setOwner(this)); 		
		this.accounts = accounts;
	}

	public void addAccount(Account account)
	{
		if (account.getOwner() == null)
		{
			account.setOwner(this);
		}

		accounts.add(account);
	}

	public void deleteAccount(Account account)
	{
		accounts.remove(account);
	}

	public Set<Account> getAccounts()
	{
		return Collections.unmodifiableSet(accounts);
	}
	
	public void setAccounts(Set<Account> accounts)
	{
		accounts.forEach(a -> a.setOwner(this)); 
		
		this.accounts = accounts;
	}
}
