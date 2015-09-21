package com.chobi.model;


import com.chobi.model.superclasses.SuperEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

/**
 * Created by Chobii on 07/09/15.
 */


@Entity
@Table(name = "contact_info")
public class ContactInfo extends SuperEntity {

    @NotNull
    private String adress;
    @NotNull
    @Digits(fraction = 0, integer = 5)
    private int zip;
    @NotNull
    private String city;
    @NotNull
    @Digits(fraction = 0, integer = 12)
    private String phone;
    private String email;

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
