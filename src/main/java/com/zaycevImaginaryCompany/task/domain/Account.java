package com.zaycevImaginaryCompany.task.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
public class Account
{
	@Setter(AccessLevel.NONE)
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Long id;
	
	@ManyToOne()
	@JoinColumn(name = "USER_ID", nullable = false)
	@Setter(AccessLevel.NONE)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private User owner;
	
	@Column(name = "accountNumber", unique = true)
	private long accountNumber;
	
	@Column(name  = "amount")
	private int amount;
		
	public void setOwner(User owner)
	{
		this.owner = owner;
		
		owner.addAccount(this);
	}

	public Account(User owner, long accountNumber, int amount)
	{
		this.accountNumber = accountNumber;
		this.amount = amount;
		this.owner = owner;
		owner.addAccount(this);
	}

}
