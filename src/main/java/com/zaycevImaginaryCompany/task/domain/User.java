package com.zaycevImaginaryCompany.task.domain;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

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

	@Id
	@GeneratedValue
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@Setter(AccessLevel.NONE)
	private Long id;

	@Column(name = "firstname")
	private String firstname;

	@Column(name = "lastname")
	private String lastname;

	@Column(name = "username", unique = true, nullable = false)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	@Setter(AccessLevel.NONE)
	@Getter(AccessLevel.NONE)
	@OneToMany(mappedBy = "owner", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	//@org.hibernate.annotations.OnDelete(action = org.hibernate.annotations.OnDeleteAction.CASCADE)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private List<Account> accounts = new ArrayList<>();

	public User(String firstname, String lastname, String username, String password, Set<Account> accounts)
	{
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		
		accounts.forEach(a -> a.setOwner(this)); 		
		this.accounts = new ArrayList<>(accounts);
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
		return Set.copyOf(new HashSet<>(accounts));
	}
	
	public void setAccounts(Set<Account> accounts)
	{
		accounts.forEach(a -> a.setOwner(this)); 
		
		this.accounts = new ArrayList<>(accounts);
	}
}
