package com.company.dabawalla.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
public class Subscribtion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int subscribtionId;

    private LocalDate subscribtionStartDate;
    private boolean subscribtionStatus;
    private int subscriptionDuration;

    private int numberOfTiffin;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "mess_id")
    private Mess mess;

    public Subscribtion() {
    }

    public Subscribtion(int subscribtionId, LocalDate subscribtionStartDate, boolean subscribtionStatus, int subscriptionDuration, Customer customer, Mess mess) {
        this.subscribtionId = subscribtionId;
        this.subscribtionStartDate = subscribtionStartDate;
        this.subscribtionStatus = subscribtionStatus;
        this.subscriptionDuration = subscriptionDuration;
        this.customer = customer;
        this.mess = mess;
    }
    public Subscribtion(Customer customer, Mess mess) {
        this.customer = customer;
        this.mess = mess;
    }

    public int getSubscribtionId() {
        return subscribtionId;
    }

    public void setSubscribtionId(int subscribtionId) {
        this.subscribtionId = subscribtionId;
    }

    public LocalDate getSubscribtionStartDate() {
        return subscribtionStartDate;
    }

    public void setSubscribtionStartDate(LocalDate subscribtionStartDate) {
        this.subscribtionStartDate = subscribtionStartDate;
    }

    public boolean isSubscribtionStatus() {
        return subscribtionStatus;
    }

    public void setSubscribtionStatus(boolean subscribtionStatus) {
        this.subscribtionStatus = subscribtionStatus;
    }

    public int getSubscriptionDuration() {
        return subscriptionDuration;
    }

    public void setSubscriptionDuration(int subscriptionDuration) {
        this.subscriptionDuration = subscriptionDuration;
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

    public int getNumberOfTiffin() {
        return numberOfTiffin;
    }

    public void setNumberOfTiffin(int numberOfTiffin) {
        this.numberOfTiffin = numberOfTiffin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscribtion that = (Subscribtion) o;
        return Objects.equals(customer, that.customer) &&
                Objects.equals(mess, that.mess);
    }
//    add duration of month to the start date and return the end date
    public LocalDate getSubscriptionEndDate(){
        return this.subscribtionStartDate.plusMonths(this.subscriptionDuration);
    }
//  chcek if the subscription is expired or not
    public boolean isExpired(){
        return LocalDate.now().isAfter(getSubscriptionEndDate());
    }
//    check if the start date is in the future
    public long getDaysLeft(){
        if(this.subscribtionStartDate.isAfter(LocalDate.now())){
            return ChronoUnit.DAYS.between(this.subscribtionStartDate,getSubscriptionEndDate());
        }else{
            return ChronoUnit.DAYS.between(LocalDate.now(),getSubscriptionEndDate());
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, mess);
    }

    @Override
    public String toString() {
        return "Subscribtion{" +
                "subscribtionId=" + subscribtionId +
                ", subscribtionStartDate=" + subscribtionStartDate +
                ", subscribtionStatus=" + subscribtionStatus +
                ", subscriptionDuration='" + subscriptionDuration + '\'' +
                ", customer=" + customer +
                ", mess=" + mess +
                '}';
    }

}
