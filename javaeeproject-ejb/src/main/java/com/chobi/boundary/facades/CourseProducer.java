package com.chobi.boundary.facades;

import com.chobi.business.entities.Course;
import com.chobi.business.entities.User;
import com.chobi.business.service.CRUDRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Chobii on 24/09/15.
 */
@RequestScoped
public class CourseProducer {

    @Inject
    private CRUDRepository crudRepository;

    public List<Course> getMyCourses(User user) {
        List<Course> all = crudRepository.findByNamedQuery(
                Course.class,
                Course.FIND_ALL,
                Course.GRAPH_DEEP
        );

        return all.stream().filter(c -> c.getTeacher().getUser().getId() == user.getId()).collect(Collectors.toList());

    }

    public List<Course> getAllCourses() {
        return crudRepository.findByNamedQuery(
                Course.class,
                Course.FIND_ALL
        );
    }
}
