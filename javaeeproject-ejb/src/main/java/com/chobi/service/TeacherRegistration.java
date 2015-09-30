package com.chobi.service;

import com.chobi.business.entities.Teacher;
import com.chobi.business.service.CRUDService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 * Created by Chobii on 16/09/15.
 */

@RequestScoped
public class TeacherRegistration {

    @Inject
    private CRUDService crudService;

    public void register(Teacher teacher) {
        try {
            crudService.create(teacher);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
