package com.chobi.repository;

import com.chobi.model.Teacher;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Chobii on 16/09/15.
 */

@ApplicationScoped
public class TeacherRepository {

    @Inject
    private EntityManager eManager;

    public Teacher findBySsn(String ssn) {
        return (eManager.find(Teacher.class, ssn));
    }

    public List<Teacher> findAllOrderedByName() {
        return eManager.createNamedQuery("teachers.FindAll")
                .setHint("javax.persistence.fetchgraph", eManager.getEntityGraph("graphDeep"))
                .getResultList();

    }
}
