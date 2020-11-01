package com.zaycevImaginaryCompany.task.service;

import com.zaycevImaginaryCompany.task.dto.UserDTO;
import com.zaycevImaginaryCompany.task.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BusinessUserServiceImpl implements BusinessUserService
{
    @Autowired
    private UserCRUDService userCRUDService;

    @Autowired
    private SecurityService securityService;

    @Override
    public UserDTO findLoggedUser()
    {
        Optional<String> usernameOptional = securityService.getLoggedUsername();

        if (usernameOptional.isPresent())
        {
            return userCRUDService.findByUsername(usernameOptional.get()).orElseGet(UserDTO::new);
        }

        return new UserDTO();
    }
}
