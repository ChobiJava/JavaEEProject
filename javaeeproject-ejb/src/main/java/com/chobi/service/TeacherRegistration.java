package com.chobi.service;

import com.chobi.business.entities.Teacher;
import com.chobi.business.service.CRUDRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Created by Chobii on 16/09/15.
 */

@Stateless
public class TeacherRegistration {

    @EJB
    private CRUDRepository crudRepository;

    public void register(Teacher teacher) {
        try {
            crudRepository.create(teacher);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
