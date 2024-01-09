package com.company.dabawalla.dao;

import com.company.dabawalla.entities.Mess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MessRepo extends JpaRepository<Mess, Integer> {
    public Mess findByMessEmail(String email);
    @Query("select m from Mess m where m.messEmail = :email")
    public Optional<Mess> findMessByEmail(String email);
    public int findMessIdByMessEmail(String email);

    @Query("select m from Mess m where m.messName like %:query%")
     public List<Mess> findByMessNameContainingIgnoreCase(String query);
}
