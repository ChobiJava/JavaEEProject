package com.chobi.controller;

import com.chobi.boundary.facades.CourseFacade;
import com.chobi.business.entities.Course;
import com.chobi.business.entities.Student;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;

/**
 * Created by Chobii on 24/09/15.
 */
@ManagedBean
@RequestScoped
public class CourseManager {

    @Inject
    private CourseFacade producer;
    @Inject
    private SessionManager sm;

    private Course course;
    private List<Student> students;

    @PostConstruct
    private void init() {
        course = all().get(0);
    }

    public List<Course> all() {
        return producer.getAllCourses();
    }

    public List<Course> forTeacher() {
        System.out.println();
        return producer.getMyCourses(sm.getUser());
    }

    public Course getACourse() {
        return producer.getAllCourses().get(0);
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void handleStudents() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int id = Integer.parseInt(params.get("class"));
        System.out.println(id);
        students.forEach(System.out::println);
        producer.saveAttendance(students, id);
    }
}
