package com.xxx.domain;

/**
 * Created by kambiz on 07/04/2017.
 */
public class MapSetMetaData {
    private Contact contact;
    private ContactUnit contactUnit;
    private MapSetName mapSetName;
    private Remarks remarks;

    public MapSetMetaData(Contact contact, ContactUnit contactUnit, MapSetName mapSetName,
                          Remarks remarks) {
        this.contact = contact;
        this.contactUnit = contactUnit;
        this.mapSetName = mapSetName;
        this.remarks = remarks;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public ContactUnit getContactUnit() {
        return contactUnit;
    }

    public void setContactUnit(ContactUnit contactUnit) {
        this.contactUnit = contactUnit;
    }

    public MapSetName getMapSetName() {
        return mapSetName;
    }

    public void setMapSetName(MapSetName mapSetName) {
        this.mapSetName = mapSetName;
    }

    public Remarks getRemarks() {
        return remarks;
    }

    public void setRemarks(Remarks remarks) {
        this.remarks = remarks;
    }


}
