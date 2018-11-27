package com.ht.vo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

public class ExamDetail {
    private int detailid;
    private int reseachid;
    private int examid;
    private int unitid;
    private String unitName;
    private String unitvalue;

    public int getDetailid() {
        return detailid;
    }

    public void setDetailid(int detailid) {
        this.detailid = detailid;
    }

    public int getReseachid() {
        return reseachid;
    }

    public void setReseachid(int reseachid) {
        this.reseachid = reseachid;
    }

    public int getExamid() {
        return examid;
    }

    public void setExamid(int examid) {
        this.examid = examid;
    }

    public int getUnitid() {
        return unitid;
    }

    public void setUnitid(int unitid) {
        this.unitid = unitid;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitvalue() {
        return unitvalue;
    }

    public void setUnitvalue(String unitvalue) {
        this.unitvalue = unitvalue;
    }


}
