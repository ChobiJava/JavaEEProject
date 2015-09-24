package com.chobi.service;

import com.chobi.business.entities.User;
import com.chobi.business.service.CRUDRepository;
import com.chobi.business.service.QueryParams;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;

/**
 * Created by Chobii on 16/09/15.
 */

@ApplicationScoped
public class UserRepository {

    @EJB
    private CRUDRepository crudRepository;

    public boolean findUser(String userName, String password) {
        try {
            User user = crudRepository.findByNamedQuery(
                    User.class,
                    User.FIND_BY_USERNAME_AND_PASSWORD,
                    QueryParams.with("userName", userName)
                               .and("password", password)
                               .parameters()
            ).get(0);
            return user != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
