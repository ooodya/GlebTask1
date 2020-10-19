package com.zaycevImaginaryCompany.task.service;

public interface AccountTransferService
{
	boolean transfer (long destinationAccNumber, long sourceAccNumber, int amount);
}
