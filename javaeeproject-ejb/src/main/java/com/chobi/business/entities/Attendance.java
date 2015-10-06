package com.chobi.business.entities;

import com.chobi.business.entities.superclasses.SuperEntity;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by Chobii on 15/09/15.
 */

@NamedQueries({
        @NamedQuery(name = Attendance.ATTENDANCE_FOR_COURSE_AND_DAY, query = "SELECT a from Attendance a where a.course.courseName like :course and a.schoolDay like :schoolday"),
        @NamedQuery(name = Attendance.ATTENDANCE_FOR_COURSE_AND_MONTH, query = "Select a from Attendance a where a.course.courseName like :course and MONTH(a.schoolDay) like :schoolday"),
        @NamedQuery(name = Attendance.ATTENDANCE_FOR_STUDENT_AND_MONTH, query = "SELECT a from Attendance a where a.student.id like :student and MONTH(a.schoolDay) like :schoolday")
})

@NamedEntityGraphs({
        @NamedEntityGraph(name = Attendance.GRAPH_DEEP, attributeNodes = {
                @NamedAttributeNode("course"),
                @NamedAttributeNode("student")
        })
})

@Entity
@Table(name = "attendance")
public class Attendance extends SuperEntity{

    public Attendance(Student student, Course course, boolean present) {
        this.student = student;
        this.course = course;
        this.schoolDay = LocalDate.now();
        this.present = present;
    }

    public Attendance() {}

    public static final String ATTENDANCE_FOR_COURSE_AND_DAY = "attendance.FindAllForCourseAndDate";
    public static final String ATTENDANCE_FOR_COURSE_AND_MONTH = "attendance.FindAllForCourseAndMonth";
    public static final String ATTENDANCE_FOR_STUDENT_AND_MONTH = "attendance.FindAllForStudentAndMonth";
    public static final String GRAPH_DEEP = "attendance.WithCourse";

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
