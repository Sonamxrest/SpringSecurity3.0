package com.xrest.nchl.controller;

import com.xrest.nchl.model.Account;
import com.xrest.nchl.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("v1/account")
public class AccountController extends BaseController<Account, Long>{

    private final AccountService accountService;
    public AccountController(AccountService accountService) {
        super(accountService);
        this.accountService = accountService;
    }

    @PostMapping("/filter")
    public ResponseEntity<?> filter(@RequestBody Map<String,String> sp) {
        return new ResponseEntity(accountService.filterAll(sp), HttpStatus.OK);
    }
}
