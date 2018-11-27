package com.ht.vo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

public class PaidType {
    private int paidtypeid;
    private String paidtypename;
    private String iscash;
    private String invalid;

    public int getPaidtypeid() {
        return paidtypeid;
    }

    public void setPaidtypeid(int paidtypeid) {
        this.paidtypeid = paidtypeid;
    }

    public String getPaidtypename() {
        return paidtypename;
    }

    public void setPaidtypename(String paidtypename) {
        this.paidtypename = paidtypename;
    }

    public String getIscash() {
        return iscash;
    }

    public void setIscash(String iscash) {
        this.iscash = iscash;
    }

    public String getInvalid() {
        return invalid;
    }

    public void setInvalid(String invalid) {
        this.invalid = invalid;
    }

}
