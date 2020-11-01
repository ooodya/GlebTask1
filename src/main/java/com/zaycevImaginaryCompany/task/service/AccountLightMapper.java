package com.zaycevImaginaryCompany.task.service;

import com.zaycevImaginaryCompany.task.domain.Account;
import com.zaycevImaginaryCompany.task.dto.AccountDTOLite;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountLightMapper
{
    AccountDTOLite accountToDTOLight(Account acc);

    Account DTOtoAccount(AccountDTOLite accountDTOLite);
}
