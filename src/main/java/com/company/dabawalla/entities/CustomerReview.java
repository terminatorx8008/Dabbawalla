package com.company.dabawalla.entities;

import jakarta.persistence.*;

@Entity
public class CustomerReview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int customerReviewId;
    private String customerReview;
    private String customerRating;
    private String customerReviewDate;
    private String customerReviewTime;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "mess_id")
    private Mess mess;

    public CustomerReview() {
    }

    public CustomerReview(int customerReviewId, String customerReview, String customerRating, String customerReviewDate, String customerReviewTime, Customer customer, Mess mess) {
        this.customerReviewId = customerReviewId;
        this.customerReview = customerReview;
        this.customerRating = customerRating;
        this.customerReviewDate = customerReviewDate;
        this.customerReviewTime = customerReviewTime;
        this.customer = customer;
        this.mess = mess;
    }

    public int getCustomerReviewId() {
        return customerReviewId;
    }

    public void setCustomerReviewId(int customerReviewId) {
        this.customerReviewId = customerReviewId;
    }

    public String getCustomerReview() {
        return customerReview;
    }

    public void setCustomerReview(String customerReview) {
        this.customerReview = customerReview;
    }

    public String getCustomerRating() {
        return customerRating;
    }

    public void setCustomerRating(String customerRating) {
        this.customerRating = customerRating;
    }

    public String getCustomerReviewDate() {
        return customerReviewDate;
    }

    public void setCustomerReviewDate(String customerReviewDate) {
        this.customerReviewDate = customerReviewDate;
    }

    public String getCustomerReviewTime() {
        return customerReviewTime;
    }

    public void setCustomerReviewTime(String customerReviewTime) {
        this.customerReviewTime = customerReviewTime;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Mess getMess() {
        return mess;
    }

    public void setMess(Mess mess) {
        this.mess = mess;
    }

    @Override
    public String toString() {
        return "CustomerReview{" +
                "customerReviewId=" + customerReviewId +
                ", customerReview='" + customerReview + '\'' +
                ", customerRating='" + customerRating + '\'' +
                ", customerReviewDate='" + customerReviewDate + '\'' +
                ", customerReviewTime='" + customerReviewTime + '\'' +
                ", customer=" + customer +
                ", mess=" + mess +
                '}';
    }
}
