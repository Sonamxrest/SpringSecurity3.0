package com.xrest.nchl.specification;

import com.xrest.nchl.model.Customer;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserSpecBuilder {
    private  final Map<String, String> params;
    public UserSpecBuilder(Map<String, String> params) {
        this.params = params;
    }

    public Specification<Customer> build() {
        if (ObjectUtils.isEmpty(params)) {
            return null;
        }
        List<String> parameters = new ArrayList<>(params.keySet());
        Specification<Customer> specification = new UserSpec(parameters.get(0), params.get(parameters.get(0)));
        for (String p: parameters) {
            specification = Specification.where(specification).and(new UserSpec(p, params.get(p)));
        }
        return specification;
    }
}
