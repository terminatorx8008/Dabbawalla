package com.company.dabawalla.dao;

import com.company.dabawalla.entities.Mess;
import com.company.dabawalla.entities.Subscribtion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubsRepo extends JpaRepository<Subscribtion,Integer> {
    @Query("from Subscribtion where mess=:mess and subscribtionStatus=false")
    List<Subscribtion> findByMessWhereStatusISFalse(Mess mess);
    @Query("from Subscribtion where mess=:mess and subscribtionStatus=true")
    List<Subscribtion> findByMessWhereStatusISTrue(Mess mess);
}
