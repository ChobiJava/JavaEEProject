package com.chobi.controller;

import com.chobi.business.entities.Teacher;
import com.chobi.service.TeacherRegistration;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Chobii on 16/09/15.
 */
@ManagedBean
@SessionScoped
public class TeacherController {

    private Teacher newTeacher;
    private Teacher sessionTeacher;

    @Produces
    @Named
    public Teacher getNewTeacher() {
        return newTeacher;
    }

    @Inject
    TeacherRegistration sRegistration;

    public void register() {
        try {
            sRegistration.register(newTeacher);
            initNewStudent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void initNewStudent() {
        newTeacher = new Teacher();
    }

    public Teacher getSessionTeacher() {
        return sessionTeacher;
    }

    public void setSessionTeacher(Teacher sessionTeacher) {
        this.sessionTeacher = sessionTeacher;
    }
}
