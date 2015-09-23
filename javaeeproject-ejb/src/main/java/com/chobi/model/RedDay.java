package com.chobi.model;

import com.chobi.model.superclasses.SuperEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Created by Chobii on 15/09/15.
 */

@Entity
@Table(name = "red_day")
public class RedDay extends SuperEntity{

    @Temporal(TemporalType.DATE)
    private Date redDay;

    public Date getRedDay() {
        return redDay;
    }

    public void setRedDay(Date redDay) {
        this.redDay = redDay;
    }
}
