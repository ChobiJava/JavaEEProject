package com.chobi.boundary.facades;

import com.chobi.business.entities.Teacher;
import com.chobi.business.service.CRUDService;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by Chobii on 16/09/15.
 */

@Stateless
public class TeacherFacade {

    @Inject
    CRUDService crudService;

    private List<Teacher> teachers;

    @Produces
    @Named
    public List<Teacher> getTeachers() {
        return teachers;
    }

    @PostConstruct
    public void retrieveAllTeachers() {
        teachers = crudService.findByNamedQuery(
                Teacher.class,
                Teacher.FIND_ALL,
                Teacher.GRAPH_DEEP
        );
    }

    public Teacher addTeacher(Teacher teacher) {
        return null;
    }

    public Teacher editTeacher(Teacher teacher) {
        return null;
    }

    public void deleteTeacher(Teacher teacher) {

    }

    public Teacher getOneTeacher(int id) {
        return crudService.find(Teacher.class, id);
    }
}
