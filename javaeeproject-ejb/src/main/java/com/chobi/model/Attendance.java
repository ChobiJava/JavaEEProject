package com.chobi.model;

import com.chobi.model.superclasses.SuperEntity;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by Chobii on 15/09/15.
 */

@Entity
public class Attendance extends SuperEntity{

    @OneToOne
    private Student student;
    @OneToOne
    private Course course;
    private LocalDate schoolday;

}
