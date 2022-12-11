package com.xrest.nchl.serviceImpl;

import com.xrest.nchl.model.Account;
import com.xrest.nchl.repository.AccountRepository;
import com.xrest.nchl.service.AccountService;
import com.xrest.nchl.specification.AccountSpecBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.util.Map;
@Service
public class AccountServiceImpl extends BaseServiceImpl<Account, Long> implements AccountService {
    private final AccountRepository accountRepository;
    public AccountServiceImpl(AccountRepository accountRepository) {
        super(accountRepository);
        this.accountRepository = accountRepository;
    }
    @Override
    public List<Account> filterAll(Map<String,String> specification) {
        if (specification.isEmpty()) {
            return null;
        }
        AccountSpecBuilder accountSpecBuilder = new AccountSpecBuilder(specification);
        Specification<Account> accountSpec = accountSpecBuilder.build();
        return accountRepository.findAll(accountSpec);
    }
}
