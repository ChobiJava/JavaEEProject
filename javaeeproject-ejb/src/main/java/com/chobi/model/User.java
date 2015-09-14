package com.chobi.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Chobii on 08/09/15.
 */
@Entity(name = "user")
public class User {

    @Id
    private int id;
    @NotNull
    private String userName;
    @NotNull
    @Size(min = 8, max = 16)
    private String passWord;
    @NotNull
    private UserType userType;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Enumerated(EnumType.ORDINAL)
    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}

