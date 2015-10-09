package com.chobi.controller.students;

import com.chobi.boundary.facades.StudentFacade;
import com.chobi.business.entities.Student;
import org.primefaces.model.UploadedFile;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 * Created by Chobii on 25/09/15.
 */
@ManagedBean
@ViewScoped
public class EditStudentBean {

    @Inject
    StudentFacade facade;
    private Student student;
    private UploadedFile file;

    public String save() {
        if (file != null) {
            byte[] fileToPersist = file.getContents();
            if (fileToPersist.length < 10) {
                System.out.println("test2");
                student.setPhoto(null);
            } else {
                student.setPhoto(fileToPersist);
            }

        } else {
            student.setPhoto(null);
        }
        facade.editStudent(student);
        return "/school/students.xhtml";
    }

    public String removeStudent() {
        facade.deleteStudent(student);
        return "/school/students.xhtml";
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

}
