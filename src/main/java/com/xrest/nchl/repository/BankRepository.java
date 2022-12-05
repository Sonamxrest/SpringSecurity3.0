package com.xrest.nchl.repository;

import com.xrest.nchl.dto.AccountDto;
import com.xrest.nchl.model.Account;
import com.xrest.nchl.model.Bank;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;


public interface BankRepository extends  BaseRepository<Bank, Long>{

    @Query(value = "SELECT a.account_name, a.account_number, a.balance FROM account a WHERE a.bank_id = :bankId", nativeQuery = true)
     List<Map<String, Object>> findAllAccount(@Param("bankId") Long bankId);
}
