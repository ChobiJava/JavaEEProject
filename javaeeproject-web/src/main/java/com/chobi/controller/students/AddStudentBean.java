package com.chobi.controller.students;

import com.chobi.boundary.facades.StudentFacade;
import com.chobi.business.entities.ContactInfo;
import com.chobi.business.entities.Student;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 * Created by Chobii on 30/09/15.
 */
@ManagedBean
@ViewScoped
public class AddStudentBean {

    @Inject
    private StudentFacade sFacade;
    private Student newStudent;
    private ContactInfo newContact;
    private UploadedFile file;

    @PostConstruct
    private void init() {
        newStudent = new Student();
        newContact = new ContactInfo();
    }

    public String addStudente() {
        byte[] fileToPersist = file.getContents();
        newStudent.setContactInfo(newContact);
        newStudent.setPhoto(fileToPersist);
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

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
}
