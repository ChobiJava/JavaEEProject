package com.chobi.controller;

import com.chobi.model.Student;
import com.chobi.repository.StudentRepository;
import com.chobi.service.StudentRegistration;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Chobii on 08/09/15.
 */
@ManagedBean
@SessionScoped
public class StudentController {

    private Student newStudent;
    private Student student;

    public Student getStudent() {
        return student;
    }

    @Produces
    @Named
    public Student getNewStudent() {
        return newStudent;
    }

    @Inject
    StudentRegistration sRegistration;
    @Inject
    StudentRepository sRep;

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

    public String blaha(int id) {
        student = sRep.findBySsn(id);
        return "/app/student.xhtml?faces-redirect=true";

    }
}
