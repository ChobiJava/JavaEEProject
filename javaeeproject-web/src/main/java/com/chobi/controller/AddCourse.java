package com.chobi.controller;

import com.chobi.boundary.facades.CourseFacade;
import com.chobi.boundary.facades.TeacherFacade;
import com.chobi.business.entities.Course;
import com.chobi.business.entities.Teacher;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;

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

    private Course newCourse;
    private List<Teacher> teachers;
    private Teacher teacher;
    private Date start;
    private Date end;

    @PostConstruct
    private void init() {
        teachers = tFacade.retrieveAllTeachers();
        newCourse = new Course();
    }

    public String addCoursee() {
        newCourse.setStartDate(start);
        newCourse.setEndDate(end);
        newCourse.setTeacher(teacher);
        cFacade.addCourse(newCourse);
        return "/app/courses.xhtml?faces-redirect=true";
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
}
