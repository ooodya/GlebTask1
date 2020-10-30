package com.zaycevImaginaryCompany.task.service;

import com.zaycevImaginaryCompany.task.domain.User;
import com.zaycevImaginaryCompany.task.domain.UserDTOLight;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserLightMapper
{
    UserDTOLight userToDTO(User user);

    User DTOtoUser(UserDTOLight userDTOLight);
}
