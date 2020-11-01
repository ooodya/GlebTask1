package com.zaycevImaginaryCompany.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTOLite
{
    private String firstname;

    private String lastname;

    private String username;

    private String password;
}
