package com.zaycevImaginaryCompany.task.service;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.zaycevImaginaryCompany.task.domain.Account;
import com.zaycevImaginaryCompany.task.domain.AccountDTO;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface AccountMapper
{
	@Mapping(source = "owner", target = "userDTO")
	AccountDTO accountToDTO(Account acc, @Context CycleAvoidingMappingContext context);
	
	@Mapping(source = "userDTO", target = "owner")
	Account DTOtoAccount(AccountDTO accDTO, @Context CycleAvoidingMappingContext context);
}
