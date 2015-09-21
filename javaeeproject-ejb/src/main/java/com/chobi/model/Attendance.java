package com.chobi.model;

import com.chobi.model.superclasses.SuperEntity;

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
    private LocalDate schoolday;

}
