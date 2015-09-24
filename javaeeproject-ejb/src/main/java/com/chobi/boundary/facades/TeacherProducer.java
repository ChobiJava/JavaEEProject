package com.chobi.boundary.facades;

import com.chobi.business.service.CRUDRepository;
import com.chobi.business.entities.Teacher;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import java.util.List;

/**
 * Created by Chobii on 16/09/15.
 */

@RequestScoped
public class TeacherProducer {

    @EJB
    CRUDRepository repo;

    private List<Teacher> teachers;

    @Produces
    @Named
    public List<Teacher> getTeachers() {
        return teachers;
    }

    @PostConstruct
    public void retrieveAllTeachers() {
        teachers = repo.findByNamedQuery(
                Teacher.class,
                Teacher.FIND_ALL,
                Teacher.GRAPH_DEEP
        );
    }
}
