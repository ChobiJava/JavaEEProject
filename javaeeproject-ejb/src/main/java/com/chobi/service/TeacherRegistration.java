package com.chobi.service;

import com.chobi.model.Teacher;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Created by Chobii on 16/09/15.
 */

@Stateless
public class TeacherRegistration {

    @Inject
    private EntityManager em;

    public void register(Teacher teacher) {
        try {
            em.persist(teacher);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
