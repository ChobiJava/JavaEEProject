package com.chobi.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Chobii on 07/09/15.
 */

@Entity(name = "student")
public class Student {

    @Id
    private String ssn;

    private String firstName;

    private String lastName;

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
