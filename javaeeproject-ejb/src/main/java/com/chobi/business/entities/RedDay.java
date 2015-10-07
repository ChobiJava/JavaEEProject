package com.chobi.business.entities;

import com.chobi.business.entities.superclasses.SuperEntity;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by Chobii on 15/09/15.
 */

@NamedQueries({
        @NamedQuery(name = RedDay.FIND_REDDAY_FOR_COURSE_AND_DATE, query = "SELECT distinct r from RedDay r where r.course like :course and r.redDay like :redday"),
        @NamedQuery(name = RedDay.FIND_REDDAYS_FOR_COURSE, query = "SELECT distinct r from RedDay r where r.course like :course")
})

@NamedEntityGraphs({
        @NamedEntityGraph(name = RedDay.GRAPH_DEEP, attributeNodes = @NamedAttributeNode("course"))
})

@Entity
@Table(name = "redday")
public class RedDay extends SuperEntity {

    public RedDay(LocalDate date, Course course) {
        this.redDay = date;
        this.course = course;
    }

    public RedDay() {}

    public static final String FIND_REDDAY_FOR_COURSE_AND_DATE = "redday.findforcourseanddate";
    public static final String FIND_REDDAYS_FOR_COURSE = "redday.findallforcourse";
    public static final String GRAPH_DEEP = "redday.deep";

    private LocalDate redDay;
    @OneToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public LocalDate getRedDay() {
        return redDay;
    }

    public void setRedDay(LocalDate redDay) {
        this.redDay = redDay;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
