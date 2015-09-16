package com.chobi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

/**
 * Created by Chobii on 15/09/15.
 */

@Entity
public class RedDay {

    @Id
    private int id;
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
