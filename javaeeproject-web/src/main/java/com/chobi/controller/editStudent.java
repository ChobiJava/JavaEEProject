package com.chobi.controller;

import com.chobi.boundary.facades.StudentFacade;
import com.chobi.business.entities.Student;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 * Created by Chobii on 25/09/15.
 */
@ManagedBean
@ViewScoped
public class editStudent {

    private Student student;

    @Inject
    StudentFacade facade;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String save() {
        facade.editStudent(student);
        return "/app/studentView.xhtml?faces-redirect=true";
    }
}
