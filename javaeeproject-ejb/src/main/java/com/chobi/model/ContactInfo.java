package com.chobi.model;


import org.hibernate.validator.constraints.Email;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

/**
 * Created by Chobii on 07/09/15.
 */


@Entity(name = "contact_info")
public class ContactInfo {

    @Id
    private int id;
    @NotNull
    @Digits(fraction = 0, integer = 12)
    private String phonenumber;
    @NotNull
    private String adress;
    @NotNull
    @Digits(fraction = 0, integer = 5)
    private int zip;
    @NotNull
    private String city;
    @Email
    private String email;
}
