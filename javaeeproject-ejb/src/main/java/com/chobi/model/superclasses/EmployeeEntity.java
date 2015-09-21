package com.chobi.model.superclasses;

import com.chobi.model.User;

import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

/**
 * Created by Chobii on 21/09/15.
 */
@MappedSuperclass
public class EmployeeEntity extends HumanEntity {

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
