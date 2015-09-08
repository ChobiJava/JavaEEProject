package com.chobi.controller;

import com.chobi.model.Student;
import com.chobi.service.StudentRegistration;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Chobii on 08/09/15.
 */
@Model
public class StudentController {

    private Student newStudent;

    @Produces
    @Named
    public Student getNewStudent() {
        return newStudent;
    }

    @Inject
    StudentRegistration sRegistration;

    public void register() {
        try {
            sRegistration.register(newStudent);
            initNewStudent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void initNewStudent() {
        newStudent = new Student();
    }
}
