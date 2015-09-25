package com.chobi.service;

import com.chobi.business.entities.Student;
import com.chobi.business.service.CRUDRepository;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

/**
 * Created by Chobii on 08/09/15.
 */

@RequestScoped
public class StudentRegistration {

    @Inject
    private CRUDRepository crudRepository;

    @Inject
    private Event<Student> studentEvent;

    public void register(Student student) {
        try {
            crudRepository.create(student);
            studentEvent.fire(student);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
