package com.company.dabawalla.dao;

import com.company.dabawalla.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<Customer, Integer> {
    @Query("select c from Customer c where c.customerEmail = :email")
    public Optional<Customer> findByCustomerEmail(String email);
}
