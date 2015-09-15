package com.chobi.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Chobii on 08/09/15.
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    private int id;
    @NotNull
    @Column(name = "user_name")
    private String userName;
    @NotNull
    @Size(min = 8, max = 16)
    private String password;
    @NotNull
    @Column(name = "user_type")
    private UserType userType;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Enumerated(EnumType.ORDINAL)
    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}

