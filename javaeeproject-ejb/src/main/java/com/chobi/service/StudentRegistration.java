package com.chobi.service;

import com.chobi.business.entities.Student;
import com.chobi.business.service.CRUDRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

/**
 * Created by Chobii on 08/09/15.
 */

@Stateless
public class StudentRegistration {

    @EJB
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
