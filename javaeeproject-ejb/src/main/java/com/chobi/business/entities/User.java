package com.chobi.business.entities;

import com.chobi.business.entities.superclasses.SuperEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Chobii on 08/09/15.
 */
@Entity
@Table(name = "user")
@NamedQueries({
        @NamedQuery(name = User.FIND_BY_USERNAME_AND_PASSWORD, query = "select u from User u where u.userName like :userName and u.password like :password")
})
public class User extends SuperEntity {

    public static final String FIND_BY_USERNAME_AND_PASSWORD = "user.findByUserNameAndPassword";

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

    @Enumerated(EnumType.ORDINAL)
    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}

