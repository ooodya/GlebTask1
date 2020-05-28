package com.zaycevImaginaryCompany.glebTask1.domain;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
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
	
	@Column(unique = true, nullable = false)
	@NotBlank(message = "${validation.user.username.empty}")
	private String username;
	
	@Column(nullable = false)
	//@NotBlank(message = "${validation.user.password.empty}")
	@NotBlank(message = "HELLO")
	private String password;
	
	@Setter(AccessLevel.NONE)
	@Getter(AccessLevel.NONE)
	@OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
	private Set<Account> accounts = new HashSet<>();

	public User(String lastname, String firstname, String username)
	{
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
	}
	
	public User(String lastname, String firstname, String username, String password)
	{
		this(lastname, firstname, username);
		this.password = password;
	}
	
	public void addAccount(Account account)
	{
		accounts.add(account);
		if (account.getOwner() == null)
		{
			account.addOwner(this);
		}
	}
	
	public void deleteAccount(Account account)
	{
		accounts.remove(account);
	}
	
	public Set<Account> getAccounts()
	{
		return Collections.unmodifiableSet(accounts);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (firstname == null)
		{
			if (other.firstname != null)
				return false;
		}
		else if (!firstname.equals(other.firstname))
			return false;
		if (lastname == null)
		{
			if (other.lastname != null)
				return false;
		}
		else if (!lastname.equals(other.lastname))
			return false;
		if (username == null)
		{
			if (other.username != null)
				return false;
		}
		else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public String toString()
	{
		return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", username=" + username
				+ "]";
	}

	
}
