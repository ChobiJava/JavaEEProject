package com.chobi.business.entities;

import com.chobi.business.entities.superclasses.SuperEntity;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by Chobii on 15/09/15.
 */

@Entity
@Table(name = "attendance")
public class Attendance extends SuperEntity{

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;
    @OneToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @Column(name = "school_date")
    private LocalDate schoolDay;
    private boolean present;

    public LocalDate getSchoolDay() {
        return schoolDay;
    }

    public void setSchoolDay(LocalDate schoolDay) {
        this.schoolDay = schoolDay;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

}
