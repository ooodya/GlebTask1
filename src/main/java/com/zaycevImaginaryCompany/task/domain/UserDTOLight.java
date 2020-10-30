package com.zaycevImaginaryCompany.task.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTOLight
{
    private String firstname;

    private String lastname;

    private String username;

    private String password;
}
