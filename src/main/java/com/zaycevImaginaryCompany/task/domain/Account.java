package com.zaycevImaginaryCompany.task.domain;

import javax.persistence.*;

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
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Long id;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
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
