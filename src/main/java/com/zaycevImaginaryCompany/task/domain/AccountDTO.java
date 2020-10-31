package com.zaycevImaginaryCompany.task.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO
{
	private UserDTOLight userDTOLight = new UserDTOLight();

	private long accountNumber;
	
	private int amount;
}
