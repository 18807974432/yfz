package com.ht.vo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Objects;

public class CustPlan {
    private int planId;
    private int custid;
    private String termTypeName;
    private String hourseid;
    private String modelId;
    private String paidtypeid;
    private Double plantotalPrice;
    private Double planunitPrice;
    private String planArea;
    private String priority;//优先级

    public Double getPlantotalPrice() {
        return plantotalPrice;
    }

    public void setPlantotalPrice(Double plantotalPrice) {
        this.plantotalPrice = plantotalPrice;
    }

    public Double getPlanunitPrice() {
        return planunitPrice;
    }

    public void setPlanunitPrice(Double planunitPrice) {
        this.planunitPrice = planunitPrice;
    }

    private String demo;

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public int getCustid() {
        return custid;
    }

    public void setCustid(int custid) {
        this.custid = custid;
    }

    public String getTermTypeName() {
        return termTypeName;
    }

    public void setTermTypeName(String termTypeName) {
        this.termTypeName = termTypeName;
    }

    public String getHourseid() {
        return hourseid;
    }

    public void setHourseid(String hourseid) {
        this.hourseid = hourseid;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getPaidtypeid() {
        return paidtypeid;
    }

    public void setPaidtypeid(String paidtypeid) {
        this.paidtypeid = paidtypeid;
    }

    public String getPlanArea() {
        return planArea;
    }

    public void setPlanArea(String planArea) {
        this.planArea = planArea;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDemo() {
        return demo;
    }

    public void setDemo(String demo) {
        this.demo = demo;
    }

}
