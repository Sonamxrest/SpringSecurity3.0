package com.xrest.nchl.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AccountDto {
    @JsonProperty("account_name")
    private String accountName;
    @JsonProperty("account_number")
    private String accountNumber;
    private String balance;
}
