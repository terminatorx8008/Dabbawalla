package com.company.dabawalla.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class MessReview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int messReviewId;
    private String messReview;
    private String messRating;
    private Date messReviewDate;
    @ManyToOne
    @JoinColumn(name = "mess_id")
    private Mess mess;

    @ManyToOne
    private Customer customer;

    public MessReview() {
    }

    public MessReview(int messReviewId, String messReview, String messRating, Date messReviewDate, Mess mess, Customer customer) {
        this.messReviewId = messReviewId;
        this.messReview = messReview;
        this.messRating = messRating;
        this.messReviewDate = messReviewDate;
        this.mess = mess;
        this.customer = customer;
    }

    public int getMessReviewId() {
        return messReviewId;
    }

    public void setMessReviewId(int messReviewId) {
        this.messReviewId = messReviewId;
    }


    public String getMessReview() {
        return messReview;
    }

    public void setMessReview(String messReview) {
        this.messReview = messReview;
    }

    public String getMessRating() {
        return messRating;
    }

    public void setMessRating(String messRating) {
        this.messRating = messRating;
    }

    public Date getMessReviewDate() {
        return messReviewDate;
    }

    public void setMessReviewDate(Date messReviewDate) {
        this.messReviewDate = messReviewDate;
    }

    public Mess getMess() {
        return mess;
    }

    public void setMess(Mess mess) {
        this.mess = mess;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
