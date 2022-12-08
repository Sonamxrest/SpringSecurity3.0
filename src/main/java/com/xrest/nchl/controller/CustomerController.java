package com.xrest.nchl.controller;

import com.xrest.nchl.core.JWTUtils;
import com.xrest.nchl.dto.RestResponseDto;
import com.xrest.nchl.model.Customer;
import com.xrest.nchl.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("v1/customer")
public class CustomerController extends BaseController<Customer, Long> {
    private final CustomerService customerService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;


    public CustomerController(CustomerService customerService, AuthenticationManager authenticationManager) {
        super(customerService);
        this.customerService = customerService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/upload/{id}")
    private ResponseEntity<?> save(@RequestParam(required = false, name = "image") MultipartFile file, @PathVariable("id") Long id) throws IOException {
        return new RestResponseDto().successModel(customerService.uploadProfile(file, id));
    }

    @PostMapping("/signup")
    private ResponseEntity<?> signup(@RequestBody Customer customer) throws IOException {
        Customer customer1 = (Customer) customerService.loadUserByUsername(customer.getUsername());
        if (!ObjectUtils.isEmpty(customer1)) {
            try {
                Map<String,Object> map = new HashMap<>();
                map.put("message","User already exists please try with another username");
              return new ResponseEntity(map,HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return new RestResponseDto().successModel(customerService.save(customer));
    }

    @PostMapping("/spec")
    private ResponseEntity<?> get(@RequestBody Map<String, String> obj) {
        return new RestResponseDto().successModel(customerService.getAll(obj));
    }
 @PostMapping("/load")
    private ResponseEntity<?> get(@RequestBody String username) {
     Customer customer1 = (Customer) customerService.loadUserByUsername(username);
     return new RestResponseDto().successModel(customer1);
    }
 @PostMapping("/authenticate")
    private ResponseEntity<?> authenticate(@RequestBody String token) {
     Customer customer1 = (Customer) customerService.loadUserByUsername(JWTUtils.decode(token));
     return new RestResponseDto().successModel(customer1);
    }

    @PostMapping("/login")
    private ResponseEntity<?> login(@RequestBody Customer customer) {
        Customer customer1 = (Customer) customerService.loadUserByUsername(customer.getUsername());
        if (passwordEncoder.matches(customer.getPassword(), customer1.getPassword())) {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(customer1, customer.getPassword(), customer1.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationManager.authenticate(authenticationToken));
            Map<String, Object> map = new HashMap<>();
            map.put("token", JWTUtils.encode(customer.getUsername()));
            return new RestResponseDto().successModel(map);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
