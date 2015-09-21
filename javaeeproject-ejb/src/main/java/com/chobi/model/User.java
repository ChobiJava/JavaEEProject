package com.chobi.model;

import com.chobi.model.superclasses.SuperEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Chobii on 08/09/15.
 */
@Entity
@Table(name = "user")
@NamedQueries({
        @NamedQuery(name = "user.findByUserNameAndPassword", query = "select u from User u where u.userName like :userName and u.password like :password")
})
public class User extends SuperEntity {

    @NotNull
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Enumerated(EnumType.STRING)
    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}

