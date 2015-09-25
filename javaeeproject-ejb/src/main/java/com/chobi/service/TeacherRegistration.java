package com.chobi.service;

import com.chobi.business.entities.Teacher;
import com.chobi.business.service.CRUDRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 * Created by Chobii on 16/09/15.
 */

@RequestScoped
public class TeacherRegistration {

    @Inject
    private CRUDRepository crudRepository;

    public void register(Teacher teacher) {
        try {
            crudRepository.create(teacher);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
