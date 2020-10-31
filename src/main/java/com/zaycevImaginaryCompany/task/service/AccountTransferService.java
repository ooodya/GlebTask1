package com.zaycevImaginaryCompany.task.service;

import com.zaycevImaginaryCompany.task.domain.AccountDTO;

import java.util.Optional;

public interface AccountTransferService
{
	Optional<AccountDTO> transfer (long destinationAccountNumber, long sourceAccNumber, int amount);

	Optional<AccountDTO> addMoney(long accountNumber, int amount);
}
