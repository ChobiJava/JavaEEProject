package com.chobi.business.entities.superclasses;

import com.chobi.business.entities.User;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

/**
 * Created by Chobii on 21/09/15.
 */
@MappedSuperclass
public class EmployeeEntity extends HumanEntity {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
