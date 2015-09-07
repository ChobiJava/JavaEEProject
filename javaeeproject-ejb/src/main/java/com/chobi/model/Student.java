package com.chobi.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Chobii on 07/09/15.
 */

@Entity
public class Student {

    @Id
    private String ssn;
    private String firstName;
    private String lastName;

}
