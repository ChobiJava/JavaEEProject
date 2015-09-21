package com.chobi.model;

import com.chobi.model.superclasses.SuperEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * Created by Chobii on 15/09/15.
 */

@Entity
@Table(name = "red_day")
public class RedDay extends SuperEntity{

    private LocalDate redDay;

    public LocalDate getRedDay() {
        return redDay;
    }

    public void setRedDay(LocalDate redDay) {
        this.redDay = redDay;
    }
}
