package com.xrest.nchl.specification;

import com.xrest.nchl.model.Account;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class AccountSpec implements Specification<Account> {

    private final String FILTER_BY_NAME = "accountName";
    private final String FILTER_BY_BALANCE_GREATER = "balance";
    private final String key;
    private final String value;

    public AccountSpec(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        switch (key) {
            case FILTER_BY_NAME -> {
                return criteriaBuilder.like(root.get(key), value + "%");
            }
            case FILTER_BY_BALANCE_GREATER -> {
                return criteriaBuilder.greaterThan(root.get(key), value );
            }
            default -> {
                return null;
            }
        }
    }
}
