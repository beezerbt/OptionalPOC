package com.validation.domain;

/**
 * Created by kambiz on 07/04/2017.
 */
public class FunctionalUnitInfo {

    public String getContactUnitKey() {
        return contactUnitKey;
    }

    public String getContactUnitNaame() {
        return contactUnitNaame;
    }

    private final String contactUnitKey;
    private final String contactUnitNaame;

    public FunctionalUnitInfo(String contactUnitKey) {
        this.contactUnitKey = contactUnitKey;
        this.contactUnitNaame = "UnitName set by Single Arg constructor";

    }

    public FunctionalUnitInfo(String contactUnitKey, String contactUnitNaame) {
        this.contactUnitKey = contactUnitKey;
        this.contactUnitNaame = contactUnitNaame;
    }
}
