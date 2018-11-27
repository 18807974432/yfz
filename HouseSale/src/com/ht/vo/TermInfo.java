package com.ht.vo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class TermInfo {
    private int termId;
    private int projectId;
    private String termName;
    private String termCode;
    private String termType;
    private String prid;
    private Timestamp prPlanTime;
    private Timestamp prFactTime;
    private String auditno;
    private String contractno;
    private double saleArea;
    private double useArea;
    private double viewArea;
    private Timestamp startTime;
    private Timestamp endTime;
    private Timestamp saleTime;
    private Timestamp liveTime;
    private double hourseCount;
    private double salePrice;
    private String isPaid;
    private double hourseHeight;
    private int floorcount;
    private double buildHeight;
    private int perCount;
    private String developer;
    private String buildadultno;
    private String buildtype;
    private String description;



    private ProjectInfo pinfo;

    public ProjectInfo getProject() {
        return project;
    }

    public void setProject(ProjectInfo project) {
        this.project = project;
    }

    private ProjectInfo project;


    private Set<HourseInfo> hour = new HashSet<HourseInfo>();
    public Set<HourseInfo> getHour() {
        return hour;
    }

    public void setHour(Set<HourseInfo> hour) {
        this.hour = hour;
    }

    public ProjectInfo getPinfo() { return pinfo; }

    public void setPinfo(ProjectInfo pinfo) { this.pinfo = pinfo; }

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public String getTermCode() {
        return termCode;
    }

    public void setTermCode(String termCode) {
        this.termCode = termCode;
    }

    public String getTermType() {
        return termType;
    }

    public void setTermType(String termType) {
        this.termType = termType;
    }

    public String getPrid() {
        return prid;
    }

    public void setPrid(String prid) {
        this.prid = prid;
    }

    public Timestamp getPrPlanTime() {
        return prPlanTime;
    }

    public void setPrPlanTime(Timestamp prPlanTime) {
        this.prPlanTime = prPlanTime;
    }

    public Timestamp getPrFactTime() {
        return prFactTime;
    }

    public void setPrFactTime(Timestamp prFactTime) {
        this.prFactTime = prFactTime;
    }

    public String getAuditno() {
        return auditno;
    }

    public void setAuditno(String auditno) {
        this.auditno = auditno;
    }

    public String getContractno() {
        return contractno;
    }

    public void setContractno(String contractno) {
        this.contractno = contractno;
    }

    public double getSaleArea() {
        return saleArea;
    }

    public void setSaleArea(double saleArea) {
        this.saleArea = saleArea;
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

    public Timestamp getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(Timestamp saleTime) {
        this.saleTime = saleTime;
    }

    public Timestamp getLiveTime() {
        return liveTime;
    }

    public void setLiveTime(Timestamp liveTime) {
        this.liveTime = liveTime;
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

    public String getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(String isPaid) {
        this.isPaid = isPaid;
    }

    public double getHourseHeight() {
        return hourseHeight;
    }

    public void setHourseHeight(double hourseHeight) {
        this.hourseHeight = hourseHeight;
    }

    public int getFloorcount() {
        return floorcount;
    }

    public void setFloorcount(int floorcount) {
        this.floorcount = floorcount;
    }

    public double getBuildHeight() {
        return buildHeight;
    }

    public void setBuildHeight(double buildHeight) {
        this.buildHeight = buildHeight;
    }

    public int getPerCount() {
        return perCount;
    }

    public void setPerCount(int perCount) {
        this.perCount = perCount;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getBuildadultno() {
        return buildadultno;
    }

    public void setBuildadultno(String buildadultno) {
        this.buildadultno = buildadultno;
    }

    public String getBuildtype() {
        return buildtype;
    }

    public void setBuildtype(String buildtype) {
        this.buildtype = buildtype;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
