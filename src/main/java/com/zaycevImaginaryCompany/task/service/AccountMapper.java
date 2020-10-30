package com.zaycevImaginaryCompany.task.service;

import org.mapstruct.*;

import com.zaycevImaginaryCompany.task.domain.Account;
import com.zaycevImaginaryCompany.task.domain.AccountDTO;

import java.util.List;

@Mapper(componentModel = "spring", uses = UserLightMapper.class)
public interface AccountMapper
{
	@Mapping(source = "owner", target = "userDTOLight")
	AccountDTO accountToDTO(Account acc);

	@Mapping(source = "userDTOLight", target = "owner")
	Account DTOtoAccount(AccountDTO accDTO);

	@Mapping(source = "owner", target = "userDTOLight")
	List<AccountDTO> accountsToDTOs(List<Account> accounts);

	@Mapping(source = "userDTOLight", target = "owner")
	List<Account> DTOsToAccounts(List<AccountDTO> accounts);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	@Mapping(source = "userDTOLight", target = "owner")
	void updateAccountFromDTO(AccountDTO accDTO, @MappingTarget Account acc);
}
