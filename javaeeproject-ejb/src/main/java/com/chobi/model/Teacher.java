package com.chobi.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
public class Teacher {

    @Id
    private int id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;
    @OneToOne
    @JoinColumn(name = "contact_id")
    private ContactInfo contactInfo;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
