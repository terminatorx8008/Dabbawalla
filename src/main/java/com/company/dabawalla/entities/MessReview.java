package com.company.dabawalla.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class MessReview {
    @Id
    private int messReviewId;
    private int costumerId;
    private String messReview;
    private String messRating;
    private String messReviewDate;
    private String messReviewTime;
    @ManyToOne
    @JoinColumn(name = "mess_id")
    private Mess mess;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public MessReview() {
    }

    public MessReview(int messReviewId, int costumerId, String messReview, String messRating, String messReviewDate, String messReviewTime, Mess mess, Customer customer) {
        this.messReviewId = messReviewId;
        this.costumerId = costumerId;
        this.messReview = messReview;
        this.messRating = messRating;
        this.messReviewDate = messReviewDate;
        this.messReviewTime = messReviewTime;
        this.mess = mess;
        this.customer = customer;
    }

    public int getMessReviewId() {
        return messReviewId;
    }

    public void setMessReviewId(int messReviewId) {
        this.messReviewId = messReviewId;
    }

    public int getCostumerId() {
        return costumerId;
    }

    public void setCostumerId(int costumerId) {
        this.costumerId = costumerId;
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

    public String getMessReviewDate() {
        return messReviewDate;
    }

    public void setMessReviewDate(String messReviewDate) {
        this.messReviewDate = messReviewDate;
    }

    public String getMessReviewTime() {
        return messReviewTime;
    }

    public void setMessReviewTime(String messReviewTime) {
        this.messReviewTime = messReviewTime;
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

    @Override
    public String toString() {
        return "MessReview{" +
                "messReviewId=" + messReviewId +
                ", costumerId=" + costumerId +
                ", messReview='" + messReview + '\'' +
                ", messRating='" + messRating + '\'' +
                ", messReviewDate='" + messReviewDate + '\'' +
                ", messReviewTime='" + messReviewTime + '\'' +
                ", mess=" + mess +
                ", customer=" + customer +
                '}';
    }
}
