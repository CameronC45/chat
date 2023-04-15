package com.chat.messaging.models;

import com.chat.messaging.pojo.UserPojo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User extends UserPojo {


    public User(String email, String username, String password) {
        super(email, username, password);
    }

    public User(UserPojo userPojo){
        this(userPojo.getEmail(), userPojo.getUsername(), userPojo.getPassword());
    }

    @Id
    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Column(name = "username", unique = true, nullable = false, length = 100)
    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public void setUsername(String username) {
        super.setUsername(username);
    }

    @Column(name = "password", nullable = false, length = 50)
    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }
}
