package com.xrest.nchl.repository;

import com.xrest.nchl.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends BaseRepository<Customer, Long> {
    Customer getCustomerByUsername(String username);

}
