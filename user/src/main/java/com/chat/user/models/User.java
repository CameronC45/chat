package com.chat.user.models;

import com.chat.user.pojo.UserPojo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User extends UserPojo {


    public User(){}

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

    @Column(name = "username", unique = true, nullable = false, length = 100)
    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Column(name = "password", nullable = false, length = 72)
    @Override
    public String getPassword() {
        return super.getPassword();
    }


}