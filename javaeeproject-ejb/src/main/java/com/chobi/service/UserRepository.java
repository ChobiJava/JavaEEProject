package com.chobi.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Created by Chobii on 16/09/15.
 */

@ApplicationScoped
public class UserRepository {

    @Inject
    private EntityManager em;

    public boolean findUser(String userName, String password) {
        try {
            return em.createNamedQuery("user.findByUserNameAndPassword")
                    .setParameter("userName", userName)
                    .setParameter("password", password)
                    .getSingleResult() != null;
        } catch (Exception e) {
            return false;
        }
    }
}
