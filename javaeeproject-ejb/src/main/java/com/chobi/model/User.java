package com.chobi.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Created by Chobii on 08/09/15.
 */

public class User {

    private String userName;
    private String passWord;
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

