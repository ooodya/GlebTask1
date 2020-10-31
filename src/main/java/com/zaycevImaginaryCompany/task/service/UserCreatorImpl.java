package com.zaycevImaginaryCompany.task.service;

import com.zaycevImaginaryCompany.task.domain.UserDTO;
import com.zaycevImaginaryCompany.task.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserCreatorImpl implements UserCreator
{
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserCRUDService userCRUDService;

    @Autowired
    private SecurityService securityService;

    @Override
    public void createUser(UserDTO userDTO)
    {
        String password = userDTO.getPassword();

        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        userCRUDService.create(userDTO);
        securityService.loginAfterRegister(userDTO.getUsername(), password);
    }
}
