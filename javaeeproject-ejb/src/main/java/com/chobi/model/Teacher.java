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
        @NamedQuery(name = "teachers.FindAll" , query = "select t from Teacher t")

})

@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "graphDeep",
                attributeNodes = {
                        @NamedAttributeNode(value = "courses"),
                        @NamedAttributeNode(value = "user"),
                        @NamedAttributeNode(value = "contactInfo")
                }
        )
})
public class Teacher extends EmployeeEntity {

    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

}
