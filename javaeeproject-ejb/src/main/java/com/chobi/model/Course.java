package com.chobi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;


/**
 * Created by Chobii on 07/09/15.
 */
@Entity
public class Course {

    @Id
    private int id;
    private String courseName;
    private int points;
    private LocalDate start;
    private LocalDate end;
}
