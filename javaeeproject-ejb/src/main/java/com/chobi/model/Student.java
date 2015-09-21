package com.chobi.model;

import com.chobi.model.superclasses.HumanEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Chobii on 07/09/15.
 */

@Entity
@Table(name = "student")
public class Student extends HumanEntity{

    @ManyToMany
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses;
}
