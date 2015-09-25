package com.chobi.controller;

import com.chobi.boundary.facades.StudentFacade;
import com.chobi.business.entities.Student;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Chobii on 25/09/15.
 */
@Named
@ViewScoped
public class StudentManager {

    @PostConstruct
    private void test() {
        System.out.println("test");
        if (FacesContext.getCurrentInstance().getExternalContext().getFlash().get("student") != null) {
            this.student = (Student) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("student");
            System.out.println(student);
        } else student = new Student();
    }


    @Inject
    private StudentFacade facade;

    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String editStudent(Student student) {
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("student", student);
        return "/app/edit-student.xhtml?faces-redirect=true";
    }

    public String saveStudent() {
        facade.editStudent(student);
        return "/app/studentView.xhtml?faces-redirect=true";
    }
}
