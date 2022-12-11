package com.xrest.nchl.service;

import com.xrest.nchl.model.Account;

import java.util.List;
import java.util.Map;

public interface AccountService extends BaseService<Account, Long> {

    List<Account> filterAll(Map<String, String> specification);
}
