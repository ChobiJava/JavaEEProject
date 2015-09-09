package com.chobi.model;


import javax.validation.constraints.NotNull;

/**
 * Created by Chobii on 07/09/15.
 */


public class ContactInfo {


    private String ssn;
    @NotNull
    private String phonenumber;
    @NotNull
    private String adress;
    @NotNull
    private int zip;
    @NotNull
    private String city;
    private String email;
}
