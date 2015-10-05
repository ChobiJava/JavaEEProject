package com.chobi.boundary.facades;

import com.chobi.business.entities.Teacher;
import com.chobi.business.service.CRUDService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by Chobii on 16/09/15.
 */

@Stateless
public class TeacherFacade {

    @Inject
    CRUDService crudService;


    public List<Teacher> retrieveAllTeachers() {
         return crudService.findByNamedQuery(
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
