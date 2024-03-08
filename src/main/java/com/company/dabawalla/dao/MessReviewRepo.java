package com.company.dabawalla.dao;

import com.company.dabawalla.entities.Mess;
import com.company.dabawalla.entities.MessReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessReviewRepo extends JpaRepository<MessReview,Integer> {
    public List<MessReview> findMessReviewByMess(Mess mess);
}
