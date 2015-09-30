package com.chobi.service;

import com.chobi.business.entities.Student;
import com.chobi.business.service.CRUDService;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

/**
 * Created by Chobii on 08/09/15.
 */

@RequestScoped
public class StudentRegistration {

    @Inject
    private CRUDService crudService;

    @Inject
    private Event<Student> studentEvent;

    public void register(Student student) {
        try {
            crudService.create(student);
            studentEvent.fire(student);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
