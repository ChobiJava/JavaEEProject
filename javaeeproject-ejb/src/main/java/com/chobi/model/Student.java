package com.chobi.model;

import com.chobi.model.superclasses.HumanEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Chobii on 07/09/15.
 */

@NamedQueries({
        @NamedQuery(name = Student.FIND_ALL,
                    query = "SELECT s from Student s")
})

@NamedEntityGraphs({
        @NamedEntityGraph(name = "student.withContactInfo",
                          attributeNodes = {
                                  @NamedAttributeNode("contactInfo")
                          })
})

@Entity
@Table(name = "student")
public class Student extends HumanEntity{

    public static final String FIND_ALL = "student.findAll";

    @ManyToMany
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses;
}
