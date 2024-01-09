package com.company.dabawalla.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Payments {
    @Id
    private int paymentId;
    private int costumerId;
    private int messId;
    private int subscribtionId;
    private String paymentStatus;
    private String paymentMode;
    private String paymentDate;
    private String paymentTime;
    private String paymentAmount;
    private String paymentTransactionId;

    public Payments() {
    }

    public Payments(int paymentId, int costumerId, int messId, int subscribtionId, String paymentStatus, String paymentMode, String paymentDate, String paymentTime, String paymentAmount, String paymentTransactionId) {
        this.paymentId = paymentId;
        this.costumerId = costumerId;
        this.messId = messId;
        this.subscribtionId = subscribtionId;
        this.paymentStatus = paymentStatus;
        this.paymentMode = paymentMode;
        this.paymentDate = paymentDate;
        this.paymentTime = paymentTime;
        this.paymentAmount = paymentAmount;
        this.paymentTransactionId = paymentTransactionId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getCostumerId() {
        return costumerId;
    }

    public void setCostumerId(int costumerId) {
        this.costumerId = costumerId;
    }

    public int getMessId() {
        return messId;
    }

    public void setMessId(int messId) {
        this.messId = messId;
    }

    public int getSubscribtionId() {
        return subscribtionId;
    }

    public void setSubscribtionId(int subscribtionId) {
        this.subscribtionId = subscribtionId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentTransactionId() {
        return paymentTransactionId;
    }

    public void setPaymentTransactionId(String paymentTransactionId) {
        this.paymentTransactionId = paymentTransactionId;
    }

    @Override
    public String toString() {
        return "Payments{" +
                "paymentId=" + paymentId +
                ", costumerId=" + costumerId +
                ", messId=" + messId +
                ", subscribtionId=" + subscribtionId +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", paymentMode='" + paymentMode + '\'' +
                ", paymentDate='" + paymentDate + '\'' +
                ", paymentTime='" + paymentTime + '\'' +
                ", paymentAmount='" + paymentAmount + '\'' +
                ", paymentTransactionId='" + paymentTransactionId + '\'' +
                '}';
    }
}
