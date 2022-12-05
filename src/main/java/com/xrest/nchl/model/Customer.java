package com.xrest.nchl.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.xrest.nchl.enums.RoleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ObjectUtils;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;



@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer extends BaseModel<Long> implements UserDetails {



    @JsonProperty("first_name")
    @NotBlank(message = "Cannot Be Blank")
    @NotNull(message = "Cannot Be Null")
    private String firstName;

    @JsonProperty("last_name")
    @NotBlank(message = "Cannot Be Blank")
    @NotNull(message = "Cannot Be Null")
    private String lastName;


    private Double balance;

    private String profile;

    private String username;
    private String password;
    private RoleType role;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Account> accounts;

    @OneToOne(cascade = CascadeType.ALL)
    private Certificates certificates;

    @Transient
    private Double totalBalance = 0d;

    public Double getTotalBalance() {
        Double b = this.accounts.stream().mapToDouble(d ->  Double.parseDouble(d.getBalance())).sum();
        setBalance(b);
        return b;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return  ObjectUtils.isEmpty(role) ? new ArrayList<>() : Arrays.asList(new SimpleGrantedAuthority("ROLE_"+role.label));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
