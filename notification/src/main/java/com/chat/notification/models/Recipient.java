package com.chat.notification.models;

import jakarta.persistence.*;

@Entity
@Table(name = "recipient")
public class Recipient {

    @Id
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    public Recipient(){}

    public Recipient(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
