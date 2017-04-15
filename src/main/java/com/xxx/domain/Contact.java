package com.xxx.domain;

/**
 * Created by kambiz on 06/04/2017.
 */
public class Contact implements Organization {
    public Contact(String cwid) {
        this.cwid = cwid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCwid() {
        return cwid;
    }

    public void setCwid(String cwid) {
        this.cwid = cwid;
    }

    String fullName;
    String cwid;
}
