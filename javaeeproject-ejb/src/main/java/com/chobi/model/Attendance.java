package com.chobi.model;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by Chobii on 15/09/15.
 */

@Entity
public class Attendance {

    @Id
    private int id;
    @OneToOne
    private Student student;
    @OneToOne
    private Course course;
    private LocalDate schoolday;

}
