package com.xrest.nchl.serviceImpl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.xrest.nchl.dto.AccountDto;
import com.xrest.nchl.exception.BankException;
import com.xrest.nchl.enums.ExceptionTypes;
import com.xrest.nchl.model.Bank;
import com.xrest.nchl.model.Customer;
import com.xrest.nchl.repository.BankRepository;
import com.xrest.nchl.service.BankService;
import com.xrest.nchl.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class BankServiceImpl extends BaseServiceImpl<Bank,Long> implements BankService   {
    private final BankRepository bankRepository;
    private final CustomerService customerService;
    public BankServiceImpl(BankRepository bankRepository, CustomerService customerService) {
        super(bankRepository);
        this.bankRepository = bankRepository;
        this.customerService = customerService;
    }


    @Override
    public List<Bank> findAll() {
        try {
            List<Bank> banks = this.bankRepository.findAll();
                if (banks.size() < 1) {
                    throw new BankException(ExceptionTypes.NOT_FOUND);
                }
            List<Customer> c = customerService.findAll();
            banks.forEach((d)->{
                    if (c.size() > 0) {
                        List<Map<String, Object>> cx = bankRepository.findAllAccount(d.getId());
                        d.setAccounts(Arrays.asList(new ObjectMapper().convertValue(cx, AccountDto[].class)));
                    }
                });
                return banks;
        }  catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }


    @Override
    public Bank findOne(Long id) {
        Bank bank = bankRepository.findById(id).get();
        List<Map<String, Object>> cx = bankRepository.findAllAccount(bank.getId());
        bank.setAccounts(Arrays.asList(new ObjectMapper().convertValue(cx, AccountDto[].class)));
        return bank;
    }
}
