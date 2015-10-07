package com.chobi.business.entities;

import com.chobi.business.entities.superclasses.SuperEntity;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by Chobii on 15/09/15.
 */

@NamedQueries({
        @NamedQuery(name = RedDay.FIND_REDDAY_FOR_COURSE, query = "SELECT distinct r from RedDay r where r.course like :course and r.redDay like :redday")
})

@NamedEntityGraphs({
        @NamedEntityGraph(name = RedDay.GRAPH_DEEP, attributeNodes = @NamedAttributeNode("course"))
})

@Entity
@Table(name = "redday")
public class RedDay extends SuperEntity {

    public static final String FIND_REDDAY_FOR_COURSE = "redday.findforcourse";
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
