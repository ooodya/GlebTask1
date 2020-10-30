package com.zaycevImaginaryCompany.task.service;

import com.zaycevImaginaryCompany.task.domain.Account;
import com.zaycevImaginaryCompany.task.domain.AccountDTOLight;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountLightMapper
{
    AccountDTOLight accountToDTOLight(Account acc);

    Account DTOtoAccount(AccountDTOLight accountDTOLight);
}
