package com.zaycevImaginaryCompany.glebTask1.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.springframework.lang.NonNull;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@NoArgsConstructor
public abstract class Account
{
	@Setter(AccessLevel.NONE)
	@Id
	@GeneratedValue
	protected Long id;
	
	@NonNull
	protected User owner;
	
	protected int amount;
		
	public Account(User owner, int amount)
	{
		this.owner = owner;
		this.amount = amount;
	}

}
