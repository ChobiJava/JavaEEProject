package com.chobi.controller;

import com.chobi.boundary.facades.StudentFacade;
import com.chobi.business.entities.ContactInfo;
import com.chobi.business.entities.Student;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 * Created by Chobii on 30/09/15.
 */
@ManagedBean
@ViewScoped
public class AddStudent {

    private Student newStudent;
    private ContactInfo newContact;
    @Inject
    private StudentFacade sFacade;

    @PostConstruct
    private void init() {
        newStudent = new Student();
        newContact = new ContactInfo();
    }

    public String addStudente() {
        System.out.println("hello?");
        newStudent.setContactInfo(newContact);
        sFacade.addStudent(newStudent);
        return "/app/studentView.xhtml";
    }

    public Student getNewStudent() {
        return newStudent;
    }

    public void setNewStudent(Student newStudent) {
        this.newStudent = newStudent;
    }

    public ContactInfo getNewContact() {
        return newContact;
    }

    public void setNewContact(ContactInfo newContact) {
        this.newContact = newContact;
    }
}
