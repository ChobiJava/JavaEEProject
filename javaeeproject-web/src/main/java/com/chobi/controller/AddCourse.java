package com.chobi.controller;

import com.chobi.boundary.facades.CourseFacade;
import com.chobi.boundary.facades.StudentFacade;
import com.chobi.boundary.facades.TeacherFacade;
import com.chobi.business.entities.Course;
import com.chobi.business.entities.Student;
import com.chobi.business.entities.Teacher;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.util.*;

/**
 * Created by Chobii on 30/09/15.
 */

@ManagedBean
@ViewScoped
public class AddCourse {

    @Inject
    private TeacherFacade tFacade;
    @Inject
    private CourseFacade cFacade;
    @Inject
    private StudentFacade sFacade;

    private Course newCourse;
    private List<Teacher> teachers;
    private Teacher teacher;
    private Date start;
    private Date end;
    private Student student;
    private List<Student> allStudents;
    private List<Student> studentsToAddToCourse;

    @PostConstruct
    private void init() {
        teachers = tFacade.retrieveAllTeachers();
        newCourse = new Course();
        allStudents = sFacade.retrieveAllStudents();
        studentsToAddToCourse = new ArrayList<>();
    }

    public String addCoursee() {
        newCourse.setStartDate(start);
        newCourse.setEndDate(end);
        newCourse.setTeacher(teacher);
        cFacade.addCourse(newCourse);
        return "/app/courses.xhtml?faces-redirect=true";
    }

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

    public Course getNewCourse() {
        return newCourse;
    }

    public void setNewCourse(Course newCourse) {
        this.newCourse = newCourse;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Student> getAllStudents() {
        return allStudents;
    }

    public void setAllStudents(List<Student> allStudents) {
        this.allStudents = allStudents;
    }

    public List<Student> getStudentsToAddToCourse() {
        return studentsToAddToCourse;
    }

    public void setStudentsToAddToCourse(List<Student> studentsToAddToCourse) {
        this.studentsToAddToCourse = studentsToAddToCourse;
    }
}
