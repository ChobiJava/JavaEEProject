package com.chobi.controller;

import com.chobi.boundary.facades.CourseProducer;
import com.chobi.business.entities.Course;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by Chobii on 24/09/15.
 */
@ManagedBean
@RequestScoped
public class CourseManager {

    @Inject
    private CourseProducer producer;
    @Inject
    private SessionManager sm;

    public List<Course> all() {
        return producer.getAllCourses();
    }

    public List<Course> forTeacher() {
        System.out.println();
        return producer.getMyCourses(sm.getUser());
    }
}
