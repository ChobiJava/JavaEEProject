package com.chobi.business.entities;

import com.chobi.business.entities.superclasses.HumanEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Chobii on 07/09/15.
 */

@NamedQueries({
        @NamedQuery(name = Student.FIND_ALL,
                    query = "SELECT distinct s from Student s"),
        @NamedQuery(name = Student.FIND_ONE,
        query = "select distinct s from Student s where s.id like :id")
})

@NamedEntityGraphs({
        @NamedEntityGraph(name = "student.withContactInfo",
                          attributeNodes = {
                                  @NamedAttributeNode("contactInfo")
                          }),
        @NamedEntityGraph(name = Student.GRAPH_DEEP,
                          attributeNodes = {
                                  @NamedAttributeNode("contactInfo"),
                                  @NamedAttributeNode("courses")
                          }),
        @NamedEntityGraph(name = Student.GRAPH_WITH_TEACHER,
                          attributeNodes =
                                  @NamedAttributeNode("courses"),
                                        subgraphs = @NamedSubgraph(
                                                name = Course.GRAPH_DEEP,
                                                attributeNodes = @NamedAttributeNode("teacher")))
})

@Entity
@Table(name = "student")
public class Student extends HumanEntity{

    public static final String FIND_ALL = "student.findAll";
    public static final String FIND_ONE = "student.findOne";
    public static final String GRAPH_DEEP = "student.WithEverything";
    public static final String GRAPH_WITH_TEACHER = "student.findTeacher";

    @ManyToMany(targetEntity = Course.class)
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
