package com.chobi.controller;

import com.chobi.boundary.facades.StudentFacade;
import com.chobi.business.entities.Student;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
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
    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    @Inject
    StudentFacade facade;

    @PostConstruct
    private void init() {
        student = new Student();
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String save() {
        byte[] fileToPersist = file.getContents();
        student.setPhoto(fileToPersist);
        facade.editStudent(student);
        return "/app/studentView.xhtml?faces-redirect=true";
    }

}
