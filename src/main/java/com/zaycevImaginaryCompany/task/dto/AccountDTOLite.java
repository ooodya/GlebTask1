package com.zaycevImaginaryCompany.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTOLite
{
    private long accountNumber;

    private int amount;
}
