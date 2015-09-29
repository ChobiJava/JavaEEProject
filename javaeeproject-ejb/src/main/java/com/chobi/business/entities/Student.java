package com.chobi.business.entities;

import com.chobi.business.entities.superclasses.HumanEntity;

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

    @ManyToMany(targetEntity = Course.class, cascade = CascadeType.ALL)
    @JoinTable(name = "student_course",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id")
    )
    private List<Course> courses;

    private byte[] photo;

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
