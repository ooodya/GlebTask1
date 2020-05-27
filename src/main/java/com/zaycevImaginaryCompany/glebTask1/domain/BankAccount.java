package com.zaycevImaginaryCompany.glebTask1.domain;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class BankAccount extends Account
{
	private long accountNumber;

	public BankAccount(User owner, int amount, long accountNumber)
	{
		super(owner, amount);
		this.accountNumber = accountNumber;
	}
	
}
