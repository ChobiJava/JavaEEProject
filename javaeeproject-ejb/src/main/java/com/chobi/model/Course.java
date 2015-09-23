package com.chobi.model;

import com.chobi.model.superclasses.SuperEntity;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


/**
 * Created by Chobii on 07/09/15.
 */

@Entity
@Table(name = "course")
public class Course extends SuperEntity{

    @NotNull
    @Column(name = "course_name")
    private String courseName;
    @NotNull
    @Digits(fraction = 0, integer = 3)
    private int points;
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    @ManyToMany(targetEntity = com.chobi.model.Student.class,
            mappedBy = "courses"
    )
    private List<Student> students;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
