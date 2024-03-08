package com.company.dabawalla.dao;

import com.company.dabawalla.entities.CustomerReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepo extends JpaRepository<CustomerReview,Integer> {

}
