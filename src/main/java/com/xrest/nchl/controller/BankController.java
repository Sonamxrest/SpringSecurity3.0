package com.xrest.nchl.controller;

import com.xrest.nchl.model.Bank;
import com.xrest.nchl.service.BankService;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("v1/bank")
public class BankController extends BaseController<Bank,Long>{
    private final BankService bankService;
    public BankController(BankService bankService) {
        super(bankService);
        this.bankService = bankService;
    }
}
