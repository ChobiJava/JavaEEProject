package com.chobi.util;

import javax.inject.Named;
import java.time.LocalDate;

/**
 * Created by Chobii on 24/09/15.
 */
@Named
public class RedDayCalender {

    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
