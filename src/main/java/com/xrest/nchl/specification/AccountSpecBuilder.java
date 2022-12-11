package com.xrest.nchl.specification;

import com.xrest.nchl.model.Account;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;

public class AccountSpecBuilder {

    private final Map<String, String> params;

    public AccountSpecBuilder(Map<String, String> params) {
        this.params = params;
    }

    public Specification<Account> build() {
        List<String> keys = params.keySet().stream().toList();
        Specification<Account> accountSpecification = new AccountSpec(keys.get(0), params.get(keys.get(0)));
        for (String k : keys) {
            accountSpecification = Specification.where(accountSpecification).and(new AccountSpec(k, params.get(k)));
        }
        return accountSpecification;
    }
}
