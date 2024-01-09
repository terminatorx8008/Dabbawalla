package com.company.dabawalla.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Mess {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int messId;
    private String messName;
    private String messOwnerName;
    private String messAddress;
    private String messContact;
    private String messEmail;
    @JsonIgnore
    private String messPassword;
    @JsonIgnore
    private String messRole;
    private String messDescription;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "mess",fetch = FetchType.EAGER)
    private List<MessImages> messImage;
    @OneToMany(mappedBy ="mess",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Menu> menuItems = new ArrayList<>();
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "mess",fetch = FetchType.EAGER)
    private List<MessReview> messReviews=new ArrayList<>();
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "mess",fetch = FetchType.EAGER)
    private List<CustomerReview> costumerReviews=new ArrayList<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "mess",fetch = FetchType.EAGER)
    private List<Subscribtion> subscribtions=new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "mess",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Orders> orders=new ArrayList<>();

    public Mess() {
    }

    public Mess(int messId, String messName, String messOwnerName, String messAddress, String messContact, String messEmail, String messPassword, String messRole, String messDescription, List<MessImages> messImage, List<Menu> menuItems, List<MessReview> messReviews, List<CustomerReview> costumerReviews, List<Subscribtion> subscribtions, List<Orders> orders) {
        this.messId = messId;
        this.messName = messName;
        this.messOwnerName = messOwnerName;
        this.messAddress = messAddress;
        this.messContact = messContact;
        this.messEmail = messEmail;
        this.messPassword = messPassword;
        this.messRole = messRole;
        this.messDescription = messDescription;
        this.messImage = messImage;
        this.menuItems = menuItems;
        this.messReviews = messReviews;
        this.costumerReviews = costumerReviews;
        this.subscribtions = subscribtions;
        this.orders = orders;
    }

    public List<MessImages> getMessImage() {
        return messImage;
    }

    public void setMessImage(List<MessImages> messImage) {
        this.messImage = messImage;
    }

    public int getMessId() {
        return messId;
    }

    public void setMessId(int messId) {
        this.messId = messId;
    }

    public String getMessName() {
        return messName;
    }

    public void setMessName(String messName) {
        this.messName = messName;
    }

    public String getMessOwnerName() {
        return messOwnerName;
    }

    public void setMessOwnerName(String messOwnerName) {
        this.messOwnerName = messOwnerName;
    }

    public String getMessAddress() {
        return messAddress;
    }

    public void setMessAddress(String messAddress) {
        this.messAddress = messAddress;
    }

    public String getMessContact() {
        return messContact;
    }

    public void setMessContact(String messContact) {
        this.messContact = messContact;
    }

    public String getMessEmail() {
        return messEmail;
    }

    public void setMessEmail(String messEmail) {
        this.messEmail = messEmail;
    }

    public String getMessPassword() {
        return messPassword;
    }

    public void setMessPassword(String messPassword) {
        this.messPassword = messPassword;
    }


    public String getMessRole() {
        return messRole;
    }

    public void setMessRole(String messRole) {
        this.messRole = messRole;
    }

    public String getMessDescription() {
        return messDescription;
    }

    public void setMessDescription(String messDescription) {
        this.messDescription = messDescription;
    }

    public List<Menu> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<Menu> menuItems) {
        this.menuItems = menuItems;
    }

    public List<MessReview> getMessReviews() {
        return messReviews;
    }

    public void setMessReviews(List<MessReview> messReviews) {
        this.messReviews = messReviews;
    }

    public List<CustomerReview> getCostumerReviews() {
        return costumerReviews;
    }

    public void setCostumerReviews(List<CustomerReview> costumerReviews) {
        this.costumerReviews = costumerReviews;
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

}
