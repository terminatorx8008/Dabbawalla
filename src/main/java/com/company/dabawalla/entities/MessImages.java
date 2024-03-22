package com.company.dabawalla.entities;

import jakarta.persistence.*;

@Entity
public class MessImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mess_id")  // This is the foreign key column
    private Mess mess;
    private String messImage;

    public MessImages() {
    }

    public MessImages(Long id, Mess mess, String messImage) {
        this.id = id;
        this.mess = mess;
        this.messImage = messImage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Mess getMess() {
        return mess;
    }

    public void setMess(Mess mess) {
        this.mess = mess;
    }

    public String getMessImage() {
        return messImage;
    }

    public void setMessImage(String messImage) {
        this.messImage = messImage;
    }

}
