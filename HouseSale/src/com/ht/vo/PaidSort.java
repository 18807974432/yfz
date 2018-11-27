package com.ht.vo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

public class PaidSort {
    private int paidsortid;
    private String paidsortname;
    private String paidtype;
    private String invalid;

    public int getPaidsortid() {
        return paidsortid;
    }

    public void setPaidsortid(int paidsortid) {
        this.paidsortid = paidsortid;
    }

    public String getPaidsortname() {
        return paidsortname;
    }

    public void setPaidsortname(String paidsortname) {
        this.paidsortname = paidsortname;
    }

    public String getPaidtype() {
        return paidtype;
    }

    public void setPaidtype(String paidtype) {
        this.paidtype = paidtype;
    }

    public String getInvalid() {
        return invalid;
    }

    public void setInvalid(String invalid) {
        this.invalid = invalid;
    }

}
