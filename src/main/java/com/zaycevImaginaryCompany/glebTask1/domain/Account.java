package com.zaycevImaginaryCompany.glebTask1.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.lang.NonNull;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
public class Account
{
	@Setter(AccessLevel.NONE)
	@Id
	@GeneratedValue
	protected Long id;
	
	@NonNull
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_ID")
	@Setter(AccessLevel.NONE)
	protected User owner;
	
	private long accountNumber;
	
	protected int amount;
		
	public Account(User owner, int amount, long accountNumber)
	{
		if (!owner.getAccounts().contains(this))
		{
			owner.getAccounts().add(this);
		}
		this.owner = owner;
		this.amount = amount;
		this.accountNumber = accountNumber;
	}
	
	public void addOwner(User owner)
	{
		if (!owner.getAccounts().contains(this))
		{
			owner.getAccounts().add(this);
		}
		this.owner = owner;
	}

}
