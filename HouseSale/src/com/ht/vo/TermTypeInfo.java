package com.ht.vo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

public class TermTypeInfo {
    private int termTypeId;
    private String termTypeName;

    public int getTermTypeId() {
        return termTypeId;
    }

    public void setTermTypeId(int termTypeId) {
        this.termTypeId = termTypeId;
    }

    public String getTermTypeName() {
        return termTypeName;
    }

    public void setTermTypeName(String termTypeName) {
        this.termTypeName = termTypeName;
    }

}
