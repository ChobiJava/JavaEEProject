package com.chobi.controller.courses;

import com.chobi.business.entities.Student;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chobii on 07/10/15.
 */
public abstract class AutoCompleteStudent {

    private List<Student> studentsInClass = new ArrayList<>();
    private List<Student> allStudents;
    private Student student;

    public void handleSelectStudent(SelectEvent event) {
        student = (Student) event.getObject();
        allStudents.remove(student);
    }

    public void handleUnSelectStudent(UnselectEvent event) {
        student = (Student) event.getObject();
        allStudents.add(student);
    }

    public List<Student> autoCompleteStudents(String query) {
        List<Student> filtered = new ArrayList<>();
        for (int i = 0; i < allStudents.size(); i++) {
            Student s = allStudents.get(i);
            if (s.getFirstName().toLowerCase().startsWith(query)) {
                filtered.add(s);
            }
        }

        return filtered;
    }

    public List<Student> getStudentsInClass() {
        return studentsInClass;
    }

    public void setStudentsInClass(List<Student> studentsInClass) {
        this.studentsInClass = studentsInClass;
    }

    public List<Student> getAllStudents() {
        return allStudents;
    }

    public void setAllStudents(List<Student> allStudents) {
        this.allStudents = allStudents;
    }
}
