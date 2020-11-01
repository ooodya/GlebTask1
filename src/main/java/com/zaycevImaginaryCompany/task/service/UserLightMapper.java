package com.zaycevImaginaryCompany.task.service;

import com.zaycevImaginaryCompany.task.domain.User;
import com.zaycevImaginaryCompany.task.dto.UserDTOLite;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserLightMapper
{
    UserDTOLite userToDTO(User user);

    User DTOtoUser(UserDTOLite userDTOLite);
}
