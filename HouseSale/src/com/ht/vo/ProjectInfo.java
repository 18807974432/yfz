package com.ht.vo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class ProjectInfo {
    private int projectId;
    private String projectName;
    private String gardenName;
    private String gardenCode;
    private double buildArea;
    private double useArea;
    private double viewArea;
    private Timestamp startTime;
    private Timestamp endTime;
    private String location;
    private double hourseCount;
    private double salePrice;
    private int orderid;
    private String isPaid;
    private String description;
    private Set<TermInfo> term = new HashSet<TermInfo>();
    private Set<CustomerInfo> cust = new HashSet<CustomerInfo>();

    public Set<CustomerInfo> getCust() {
        return cust;
    }

    public void setCust(Set<CustomerInfo> cust) {
        this.cust = cust;
    }

    public Set<TermInfo> getTerm() {
        return term;
    }

    public void setTerm(Set<TermInfo> term) {
        this.term = term;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getGardenName() {
        return gardenName;
    }

    public void setGardenName(String gardenName) {
        this.gardenName = gardenName;
    }

    public String getGardenCode() {
        return gardenCode;
    }

    public void setGardenCode(String gardenCode) {
        this.gardenCode = gardenCode;
    }

    public double getBuildArea() {
        return buildArea;
    }

    public void setBuildArea(double buildArea) {
        this.buildArea = buildArea;
    }

    public double getUseArea() {
        return useArea;
    }

    public void setUseArea(double useArea) {
        this.useArea = useArea;
    }

    public double getViewArea() {
        return viewArea;
    }

    public void setViewArea(double viewArea) {
        this.viewArea = viewArea;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getHourseCount() {
        return hourseCount;
    }

    public void setHourseCount(double hourseCount) {
        this.hourseCount = hourseCount;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public String getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(String isPaid) {
        this.isPaid = isPaid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
