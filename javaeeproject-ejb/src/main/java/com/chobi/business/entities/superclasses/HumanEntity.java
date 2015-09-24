package com.chobi.business.entities.superclasses;

import com.chobi.business.entities.ContactInfo;

import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

/**
 * Created by Chobii on 21/09/15.
 */
@MappedSuperclass
public abstract class HumanEntity extends SuperEntity {


    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    @Digits(fraction = 0, integer = 12)
    private String ssn;
    @OneToOne
    @JoinColumn(name = "contact_id")
    private ContactInfo contactInfo;

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

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }
}
