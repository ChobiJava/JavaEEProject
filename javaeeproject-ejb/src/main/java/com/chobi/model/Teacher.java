package com.chobi.model;

import com.chobi.model.superclasses.EmployeeEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Chobii on 07/09/15.
 */

@Entity
@Table(name = "teacher")
@NamedQueries({
        @NamedQuery(name = Teacher.FIND_ALL , query = "select t from Teacher t")

})

@NamedEntityGraphs({
        @NamedEntityGraph(
                name = Teacher.GRAPH_DEEP,
                attributeNodes = {
                        @NamedAttributeNode(value = "courses"),
                        @NamedAttributeNode(value = "user"),
                        @NamedAttributeNode(value = "contactInfo")
                }
        )
})
public class Teacher extends EmployeeEntity {

    public static final String FIND_ALL = "teacher.FindAll";
    public static final String GRAPH_DEEP = "teacher.Deep";
    public static final String GRAPH_COURSE = "teacher.WithCourses";

    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

}
