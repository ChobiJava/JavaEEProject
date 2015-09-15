package com.chobi.model;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by Chobii on 15/09/15.
 */

@Entity
@Table(name ="attendence")
public class Attendence {

    @Id
    private int id;
    @OneToOne
    private Student student;
    @OneToOne
    private Course course;
    @Column(name = "date")
    private LocalDate schoolday;

}
