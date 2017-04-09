package com.validation.domain;

/**
 * Created by kambiz on 06/04/2017.
 */
public class ContactUnit implements Organization {
    public ContactUnit(String contactUnitKey) {
        this.contactUnitKey = contactUnitKey;
    }

    private final String contactUnitKey;

}
