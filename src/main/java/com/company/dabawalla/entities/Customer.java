package com.company.dabawalla.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer",fetch = FetchType.EAGER)
    private List<CustomerReview> customerReview=new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<MessReview> messReview=new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Subscribtion> subscribtions=new ArrayList<>();
    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "faviorateCustomers",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Mess> faviorateMesses=new ArrayList<>();

    public Customer() {
    }

    public Customer(int customerId, String customerName, String customerAddress, String customerContact, String customerEmail, String customerRole, String customerPassword, String customerImage, List<CustomerReview> customerReview, List<MessReview> messReview, List<Subscribtion> subscribtions) {
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

    public List<Mess> getFaviorateMesses() {
        return faviorateMesses;
    }

    public void setFaviorateMesses(List<Mess> faviorateMesses) {
        this.faviorateMesses = faviorateMesses;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerId, customer.customerId);
    }

    public boolean isSubscribedToMess(Mess mess){
        for(Subscribtion subscribtion:subscribtions){
            if(subscribtion.getMess().equals(mess)&& subscribtion.isSubscribtionStatus()){
                return true;
            }
        }
        return false;
    }


    @Override
    public int hashCode() {
        return Objects.hash(customerId);
    }
}
