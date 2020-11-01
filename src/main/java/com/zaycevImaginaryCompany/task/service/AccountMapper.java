package com.zaycevImaginaryCompany.task.service;

import org.mapstruct.*;

import com.zaycevImaginaryCompany.task.domain.Account;
import com.zaycevImaginaryCompany.task.dto.AccountDTO;

import java.util.List;

@Mapper(componentModel = "spring", uses = UserLightMapper.class)
public interface AccountMapper
{
	@Mapping(source = "owner", target = "userDTOLite")
	AccountDTO accountToDTO(Account acc);

	@Mapping(source = "userDTOLite", target = "owner")
	Account DTOtoAccount(AccountDTO accDTO);

	@Mapping(source = "owner", target = "userDTOLite")
	List<AccountDTO> accountsToDTOs(List<Account> accounts);

	@Mapping(source = "userDTOLite", target = "owner")
	List<Account> DTOsToAccounts(List<AccountDTO> accounts);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateAccountFromDTO(AccountDTO accDTO, @MappingTarget Account acc);
}
