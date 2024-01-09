package com.company.dabawalla.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;
    private String orderStatus;
    private Date orderDate;
    private String orderAmount;
    private String orderPaymentStatus;
    private String orderRating;
    private String orderFeedback;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Mess mess;


    public Orders() {
    }

    public Orders(int orderId, String orderStatus, Date orderDate,  String orderAmount, String orderPaymentStatus, String orderRating, String orderFeedback, Customer customer, Mess mess) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
        this.orderAmount = orderAmount;
        this.orderPaymentStatus = orderPaymentStatus;
        this.orderRating = orderRating;
        this.orderFeedback = orderFeedback;
        this.customer = customer;
        this.mess = mess;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderPaymentStatus() {
        return orderPaymentStatus;
    }

    public void setOrderPaymentStatus(String orderPaymentStatus) {
        this.orderPaymentStatus = orderPaymentStatus;
    }

    public String getOrderRating() {
        return orderRating;
    }

    public void setOrderRating(String orderRating) {
        this.orderRating = orderRating;
    }

    public String getOrderFeedback() {
        return orderFeedback;
    }

    public void setOrderFeedback(String orderFeedback) {
        this.orderFeedback = orderFeedback;
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
        return "Orders{" +
                "orderId=" + orderId +
                ", orderStatus='" + orderStatus + '\'' +
                ", orderDate=" + orderDate +
                ", orderAmount='" + orderAmount + '\'' +
                ", orderPaymentStatus='" + orderPaymentStatus + '\'' +
                ", orderRating='" + orderRating + '\'' +
                ", orderFeedback='" + orderFeedback + '\'' +
                ", customer=" + customer +
                ", mess=" + mess +
                '}';
    }
}
