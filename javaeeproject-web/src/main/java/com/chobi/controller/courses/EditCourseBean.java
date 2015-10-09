package com.chobi.controller.courses;

import com.chobi.boundary.facades.CourseFacade;
import com.chobi.boundary.facades.StudentFacade;
import com.chobi.business.entities.Course;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import java.util.Date;

/**
 * Created by Chobii on 07/10/15.
 */
@ManagedBean
@ViewScoped
public class EditCourseBean extends AutoCompleteStudent {

    @Inject
    private CourseFacade cFacade;
    @Inject
    private StudentFacade sFacade;
    private Course course;
    private Date startDate;
    private Date endDate;

    @PostConstruct
    private void init() {
        setAllStudents(sFacade.retrieveAllStudents());
    }

    public String removeCourse() {
        cFacade.deleteCourse(course);
        return "/school/courses.xhtml";
    }

    public String save() {
        course.setStudents(getStudentsInClass());
        cFacade.editCourse(course);
        return "/school/courses.xhtml";
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void preRender(ComponentSystemEvent event) {
        setStudentsInClass(course.getStudents());
        System.out.println("hello!==");
    }

}
