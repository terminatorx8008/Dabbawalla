package com.company.dabawalla.entities;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int menuId;
    private String menuName;
    private String menuImage;
    private String menuDescription;
    private String menuPrice;
    private String menuDay;
    private String menuTiming;
    @ManyToOne
    private Mess mess;

    public Menu() {
    }

    public Menu(int menuId, String menuName, String menuImage, String menuDescription, String menuPrice, String menuDay, String menuTiming, Mess mess) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.menuImage = menuImage;
        this.menuDescription = menuDescription;
        this.menuPrice = menuPrice;
        this.menuDay = menuDay;
        this.menuTiming = menuTiming;
        this.mess = mess;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuImage() {
        return menuImage;
    }

    public void setMenuImage(String menuImage) {
        this.menuImage = menuImage;
    }

    public String getMenuDescription() {
        return menuDescription;
    }

    public void setMenuDescription(String menuDescription) {
        this.menuDescription = menuDescription;
    }

    public String getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(String menuPrice) {
        this.menuPrice = menuPrice;
    }

    public String getMenuDay() {
        return menuDay;
    }

    public void setMenuDay(String menuDay) {
        this.menuDay = menuDay;
    }

    public String getMenuTiming() {
        return menuTiming;
    }

    public void setMenuTiming(String menuTiming) {
        this.menuTiming = menuTiming;
    }

    public Mess getMess() {
        return mess;
    }

    public void setMess(Mess mess) {
        this.mess = mess;
    }
    public String getMessName(){
        return mess.getMessName();
    }

}
