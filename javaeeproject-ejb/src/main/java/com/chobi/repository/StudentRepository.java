package com.chobi.repository;

import com.chobi.model.Student;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Chobii on 08/09/15.
 */
@ApplicationScoped
public class StudentRepository {

    @Inject
    private EntityManager eManager;

    public Student findBySsn(String ssn) {
        return (eManager.find(Student.class, ssn));
    }

    public List<Student> findAllOrderedByName() {
        CriteriaBuilder cb = eManager.getCriteriaBuilder();
        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
        Root<Student> students = cq.from(Student.class);
        cq.select(students).orderBy(cb.asc(students.get("firstName")));
        return eManager.createQuery(cq).getResultList();

    }
}
