package com.company.dabawalla.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int customerId;
    private String customerName;
    private String customerAddress;
    private String customerContact;
    private String customerEmail;
    private String customerRole;
    private String customerPassword;
    private String customerImage;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer",fetch = FetchType.EAGER)
    private List<CustomerReview> customerReview=new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer",fetch = FetchType.EAGER)
    private List<MessReview> messReview=new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer",fetch = FetchType.EAGER)
    private List<Subscribtion> subscribtions=new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer",fetch = FetchType.EAGER)
    private List<Orders> orders=new ArrayList<>();

    public Customer() {
    }

    public Customer(int customerId, String customerName, String customerAddress, String customerContact, String customerEmail, String customerRole, String customerPassword, String customerImage, List<CustomerReview> customerReview, List<MessReview> messReview, List<Subscribtion> subscribtions, List<Orders> orders) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerContact = customerContact;
        this.customerEmail = customerEmail;
        this.customerRole = customerRole;
        this.customerPassword = customerPassword;
        this.customerImage = customerImage;
        this.customerReview = customerReview;
        this.messReview = messReview;
        this.subscribtions = subscribtions;
        this.orders = orders;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(String customerContact) {
        this.customerContact = customerContact;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerRole() {
        return customerRole;
    }

    public void setCustomerRole(String customerRole) {
        this.customerRole = customerRole;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public String getCustomerImage() {
        return customerImage;
    }

    public void setCustomerImage(String customerImage) {
        this.customerImage = customerImage;
    }

    public List<CustomerReview> getCustomerReview() {
        return customerReview;
    }

    public void setCustomerReview(List<CustomerReview> customerReview) {
        this.customerReview = customerReview;
    }

    public List<MessReview> getMessReview() {
        return messReview;
    }

    public void setMessReview(List<MessReview> messReview) {
        this.messReview = messReview;
    }

    public List<Subscribtion> getSubscribtions() {
        return subscribtions;
    }

    public void setSubscribtions(List<Subscribtion> subscribtions) {
        this.subscribtions = subscribtions;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerContact='" + customerContact + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", customerRole='" + customerRole + '\'' +
                ", customerPassword='" + customerPassword + '\'' +
                ", customerImage='" + customerImage + '\'' +
                ", customerReview=" + customerReview +
                ", messReview=" + messReview +
                ", subscribtions=" + subscribtions +
                ", orders=" + orders +
                '}';
    }
}
