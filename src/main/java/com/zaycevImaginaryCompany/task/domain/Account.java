package com.zaycevImaginaryCompany.task.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	
	@ManyToOne()
	@JoinColumn(name = "USER_ID", nullable = false)
	@Setter(AccessLevel.NONE)
	protected User owner;
	
	@Column(unique = true)
	@Setter(AccessLevel.NONE)
	private long accountNumber;
	
	protected int amount;
	
	public Account(long accountNumber, User owner)
	{
		
		this.owner = owner;
		this.amount = 0;
		this.accountNumber = accountNumber;
		
		owner.addAccount(this);
	}
	
	public Account(long accountNumber, User owner, int amount)
	{
		this(accountNumber, owner);
		this.amount = amount;
		owner.addAccount(this);
	}
	
	public void addOwner(User owner)
	{
		this.owner = owner;
		
		owner.addAccount(this);
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
		Account other = (Account) obj;
		if (accountNumber != other.accountNumber)
			return false;
		if (amount != other.amount)
			return false;
		if (owner == null)
		{
			if (other.owner != null)
				return false;
		}
		else if (!owner.equals(other.owner))
			return false;
		return true;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (accountNumber ^ (accountNumber >>> 32));
		result = prime * result + amount;
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		return result;
	}

	@Override
	public String toString()
	{
		return "Account [id=" + id + ", owner=" + owner + ", accountNumber=" + accountNumber + ", amount=" + amount
				+ "]";
	}
	
	

}
