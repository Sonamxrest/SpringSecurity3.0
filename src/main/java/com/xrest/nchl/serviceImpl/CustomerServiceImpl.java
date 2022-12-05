package com.xrest.nchl.serviceImpl;

import com.xrest.nchl.model.Customer;
import com.xrest.nchl.repository.CustomerRepository;
import com.xrest.nchl.service.CustomerService;
import com.xrest.nchl.specification.UserSpecBuilder;
import com.xrest.nchl.utility.CommonUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl extends BaseServiceImpl<Customer, Long> implements CustomerService {
    private final CustomerRepository customerRepository;



    public CustomerServiceImpl(CustomerRepository customerRepository) {
        super(customerRepository);
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findAllByBankId(Long bankId) {
        return this.customerRepository.getAllCustomerByBankId(bankId);
    }

    @Override
    public Customer uploadProfile(MultipartFile file, Long id) {
        try {
            Customer c = customerRepository.findById(id).get();
            String path = CommonUtils.getPath(file, "profile", "profile" + id.toString());
            c.setProfile(path);
            return customerRepository.save(c);
        } catch (Exception ex) {
        }
        return null;
    }

    @Override
    public List<Customer> getAll(Map<String, String> map) {
        Specification<Customer> specification = new UserSpecBuilder(map).build();
        return customerRepository.findAll(specification);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return customerRepository.getCustomerByUsername(username);
    }

    @Override
    public Customer save(Customer customer) {
        if (ObjectUtils.isEmpty(customer.getId())) {
            customer.setPassword(new BCryptPasswordEncoder().encode(customer.getPassword()));
        }
        return customerRepository.save(customer);
    }
}
