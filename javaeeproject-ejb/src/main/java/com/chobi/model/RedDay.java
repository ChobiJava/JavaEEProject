package com.chobi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * Created by Chobii on 15/09/15.
 */

@Entity
@Table(name = "red_day")
public class RedDay {

    @Id
    private int id;
    @Column(name = "red_day")
    private LocalDate redDay;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getRedDay() {
        return redDay;
    }

    public void setRedDay(LocalDate redDay) {
        this.redDay = redDay;
    }
}
