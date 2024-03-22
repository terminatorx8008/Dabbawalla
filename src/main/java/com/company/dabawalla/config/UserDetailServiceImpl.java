package com.company.dabawalla.config;

import com.company.dabawalla.dao.MessRepo;
import com.company.dabawalla.dao.UserRepo;
import com.company.dabawalla.entities.Customer;
import com.company.dabawalla.entities.Mess;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserDetailServiceImpl implements UserDetailsService{
    @Autowired
    private MessRepo messRepo;
    @Autowired
    private UserRepo userRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Mess> mess = messRepo.findMessByEmail(username);
        if(mess.isPresent()){
            System.out.println(mess.get().getMessEmail());
            return new CustomUserImpl(mess.get());
        }
        Optional<Customer> customer = userRepo.findByCustomerEmail(username);
        if(customer.isPresent()){
            System.out.println(customer.get().getCustomerEmail());
            return new CustomUserImpl(customer.get());
        }
        throw new UsernameNotFoundException("User not found");
    }
}
