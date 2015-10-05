package com.chobi.business.entities;

import com.chobi.business.entities.superclasses.SuperEntity;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;


/**
 * Created by Chobii on 07/09/15.
 */

@NamedQueries({
        @NamedQuery(name = Course.FIND_ALL, query = "select distinct c from Course c")
})
@NamedEntityGraphs({
        @NamedEntityGraph(name = Course.GRAPH_DEEP,
        attributeNodes = {
                @NamedAttributeNode("teacher"),
                @NamedAttributeNode("students")
        },
        subgraphs = @NamedSubgraph(name = Teacher.GRAPH_DEEP,
        attributeNodes = @NamedAttributeNode("user")))
})

@Entity
@Table(name = "course")
public class Course extends SuperEntity{

    public static final String FIND_ONE = "course.FindOne";
    public static final String FIND_ALL = "course.FindAll";
    public static final String GRAPH_DEEP = "course.Deep";

    @NotNull
    @Column(name = "course_name")
    private String courseName;
    @NotNull
    @Digits(fraction = 0, integer = 3)
    private int points;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    @ManyToMany(targetEntity = Student.class)
    @JoinTable(name = "student_course",
            joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id")
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

    public void setStartDate(Date date) {
        this.startDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public void setEndDate(Date date) {
        this.endDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", points=" + points +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", teacher=" + teacher +
                ", students=" + students +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (getPoints() != course.getPoints()) return false;
        if (getCourseName() != null ? !getCourseName().equals(course.getCourseName()) : course.getCourseName() != null)
            return false;
        if (getStartDate() != null ? !getStartDate().equals(course.getStartDate()) : course.getStartDate() != null)
            return false;
        if (getEndDate() != null ? !getEndDate().equals(course.getEndDate()) : course.getEndDate() != null)
            return false;
        if (getTeacher() != null ? !getTeacher().equals(course.getTeacher()) : course.getTeacher() != null)
            return false;
        return !(getStudents() != null ? !getStudents().equals(course.getStudents()) : course.getStudents() != null);

    }

    @Override
    public int hashCode() {
        int result = getCourseName() != null ? getCourseName().hashCode() : 0;
        result = 31 * result + getPoints();
        result = 31 * result + (getStartDate() != null ? getStartDate().hashCode() : 0);
        result = 31 * result + (getEndDate() != null ? getEndDate().hashCode() : 0);
        result = 31 * result + (getTeacher() != null ? getTeacher().hashCode() : 0);
        result = 31 * result + (getStudents() != null ? getStudents().hashCode() : 0);
        return result;
    }
}
