package com.company.dabawalla.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Date;

@Entity
public class Subscribtion {
    @Id
    private int subscribtionId;
    private Date subscribtionStartDate;
    private Date subscribtionEndDate;
    private String subscribtionStatus;
    private String subscribtionAmount;
    private String subscribtionDuration;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "mess_id")
    private Mess mess;

    public Subscribtion() {
    }

    public Subscribtion(int subscribtionId, Date subscribtionStartDate, Date subscribtionEndDate, String subscribtionStatus, String subscribtionAmount, String subscribtionDuration, Customer customer, Mess mess) {
        this.subscribtionId = subscribtionId;
        this.subscribtionStartDate = subscribtionStartDate;
        this.subscribtionEndDate = subscribtionEndDate;
        this.subscribtionStatus = subscribtionStatus;
        this.subscribtionAmount = subscribtionAmount;
        this.subscribtionDuration = subscribtionDuration;
        this.customer = customer;
        this.mess = mess;
    }

    public int getSubscribtionId() {
        return subscribtionId;
    }

    public void setSubscribtionId(int subscribtionId) {
        this.subscribtionId = subscribtionId;
    }

    public Date getSubscribtionStartDate() {
        return subscribtionStartDate;
    }

    public void setSubscribtionStartDate(Date subscribtionStartDate) {
        this.subscribtionStartDate = subscribtionStartDate;
    }

    public Date getSubscribtionEndDate() {
        return subscribtionEndDate;
    }

    public void setSubscribtionEndDate(Date subscribtionEndDate) {
        this.subscribtionEndDate = subscribtionEndDate;
    }

    public String getSubscribtionStatus() {
        return subscribtionStatus;
    }

    public void setSubscribtionStatus(String subscribtionStatus) {
        this.subscribtionStatus = subscribtionStatus;
    }

    public String getSubscribtionAmount() {
        return subscribtionAmount;
    }

    public void setSubscribtionAmount(String subscribtionAmount) {
        this.subscribtionAmount = subscribtionAmount;
    }

    public String getSubscribtionDuration() {
        return subscribtionDuration;
    }

    public void setSubscribtionDuration(String subscribtionDuration) {
        this.subscribtionDuration = subscribtionDuration;
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
        return "Subscribtion{" +
                "subscribtionId=" + subscribtionId +
                ", subscribtionStartDate=" + subscribtionStartDate +
                ", subscribtionEndDate=" + subscribtionEndDate +
                ", subscribtionStatus='" + subscribtionStatus + '\'' +
                ", subscribtionAmount='" + subscribtionAmount + '\'' +
                ", subscribtionDuration='" + subscribtionDuration + '\'' +
                ", customer=" + customer +
                ", mess=" + mess +
                '}';
    }
}
