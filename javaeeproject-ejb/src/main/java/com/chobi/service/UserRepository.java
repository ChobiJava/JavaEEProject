package com.chobi.service;

import com.chobi.business.entities.User;
import com.chobi.business.service.CRUDRepository;
import com.chobi.business.service.QueryParams;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.io.Serializable;

/**
 * Created by Chobii on 16/09/15.
 */

@RequestScoped
public class UserRepository implements Serializable {

    @Inject
    private CRUDRepository crudRepository;

    public User findUser(String userName, String password) {
        try {
            return crudRepository.findByNamedQuery(
                    User.class,
                    User.FIND_BY_USERNAME_AND_PASSWORD,
                    QueryParams.with("userName", userName)
                               .and("password", password)
                               .parameters()
            ).get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
