package com.chobi.service;

import com.chobi.model.Student;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.logging.Logger;

/**
 * Created by Chobii on 08/09/15.
 */

@Stateless
public class StudentRegistration {

    @Inject
    private Logger log;
    @Inject
    private EntityManager eManager;

    @Inject
    private Event<Student> studentEvent;

    public void register(Student student) {
        try {
            log.info("Registering " + student.getFirstName());
            eManager.persist(student);
            studentEvent.fire(student);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
