package com.chobi.repository;

import com.chobi.model.Student;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by Chobii on 08/09/15.
 */

@RequestScoped
public class StudentProducer {

    @Inject
    StudentRepository sRepository;

    private List<Student> students;

    @Produces
    @Named
    public List<Student> getStudents() {
        return students;
    }

    public void onListChange(@Observes(notifyObserver = Reception.IF_EXISTS) final Student student) {
        retrieveAllStudents();
    }

    @PostConstruct
    public void retrieveAllStudents() {
        students = sRepository.findAllOrderedByName();
    }


}