package com.xrest.nchl.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;


@Entity
@Data
public class Account extends BaseModel<Long> {
    @JsonProperty("account_name")
    private String accountName;
    @JsonProperty("account_number")
    private String accountNumber;
    private String balance;
    @OneToOne
    @JoinColumn(name = "bank_id", referencedColumnName = "id")
    private Bank bank;
}
