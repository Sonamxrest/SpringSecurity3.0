package com.xrest.nchl.service;

import com.xrest.nchl.model.Customer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface CustomerService extends BaseService<Customer,Long>, UserDetailsService {

    List<Customer> findAllByBankId(Long bankId);
    Customer uploadProfile(MultipartFile file , Long id);

    List<Customer> getAll(Map<String, String> map);
}
