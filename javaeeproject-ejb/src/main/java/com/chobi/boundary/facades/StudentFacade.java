package com.chobi.boundary.facades;

import com.chobi.business.entities.Student;
import com.chobi.business.service.CRUDRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by Chobii on 08/09/15.
 */

@Stateless
public class StudentFacade {

    @Inject
    CRUDRepository service;

    public List<Student> retrieveAllStudents() {
         return service.findByNamedQuery(
                Student.class,
                Student.FIND_ALL,
                "student.withContactInfo"
        );
    }

    public Student getOneStudent(int id) {
        return service.find(Student.class, id);
    }

    public Student addStudent(Student student) {
        return null;
    }

    public void editStudent(Student student) {
        service.update(student);
    }

    public void deleteStudent(Student student) {

    }
}
