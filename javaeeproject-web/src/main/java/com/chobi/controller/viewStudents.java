package com.chobi.controller;

import com.chobi.boundary.facades.StudentFacade;
import com.chobi.business.entities.Student;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by Chobii on 25/09/15.
 */

@Named
@RequestScoped
public class viewStudents {

    private List<Student> students;

    public List<Student> getStudents() {
        return students;
    }

    @Inject
    private StudentFacade facade;

    @PostConstruct
    private void init() {
        students = facade.retrieveAllStudents();

    }

}
