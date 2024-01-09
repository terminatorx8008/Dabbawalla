package com.company.dabawalla.config;

import com.company.dabawalla.entities.Customer;
import com.company.dabawalla.entities.Mess;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserImpl implements UserDetails {
    private Mess mess;
    private Customer customer;
    public CustomUserImpl(Mess mess) {
        super();
        this.mess =mess;
    }
    public CustomUserImpl(Customer customer) {
        super();
        this.customer =customer;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority;
        if(mess!=null){
            simpleGrantedAuthority = new SimpleGrantedAuthority(mess.getMessRole());
        }else{
            simpleGrantedAuthority = new SimpleGrantedAuthority(customer.getCustomerRole());
        }
        return List.of(simpleGrantedAuthority);
    }

    @Override
    public String getPassword() {
        if(customer!=null) {
            System.out.println(customer.getCustomerPassword());
            return customer.getCustomerPassword();
        }
        else {
            return mess.getMessPassword();
        }
    }

    @Override
    public String getUsername() {
        if(customer!=null)
            return customer.getCustomerEmail();
        else {
            return mess.getMessEmail();
        }
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
