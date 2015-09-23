package com.chobi.model;

import com.chobi.model.superclasses.SuperEntity;

import javax.persistence.*;
import java.util.Date;

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
    @Temporal(TemporalType.DATE)
    private Date schoolday;


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

    public Date getSchoolday() {
        return schoolday;
    }

    public void setSchoolday(Date schoolday) {
        this.schoolday = schoolday;
    }
}
