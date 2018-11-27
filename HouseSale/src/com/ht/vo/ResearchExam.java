package com.ht.vo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;
import com.ht.vo.ProjectInfo;


public class ResearchExam {
    private int reseachid;
    private int projectid;
    private String examName;
    private String invalid;
    private int orderid;
    private int examCount;
    private String description;
    private String userid;
    private Date oprtime;
    private ProjectInfo proj;
    private ResearchExam research;
    private ExamMaster exam;

    public ResearchExam getResearch() {
        return research;
    }

    public ExamMaster getExam() {
        return exam;
    }

    public void setExam(ExamMaster exam) {
        this.exam = exam;
    }

    public void setResearch(ResearchExam research) {
        this.research = research;
    }

    public int getReseachid() {
        return reseachid;
    }

    public void setReseachid(int reseachid) {
        this.reseachid = reseachid;
    }

    public int getProjectid() {
        return projectid;
    }

    public void setProjectid(int projectid) {
        this.projectid = projectid;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getInvalid() {
        return invalid;
    }

    public void setInvalid(String invalid) {
        this.invalid = invalid;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getExamCount() {
        return examCount;
    }

    public void setExamCount(int examCount) {
        this.examCount = examCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Date getOprtime() {
        return oprtime;
    }

    public void setOprtime(Date oprtime) {
        this.oprtime = oprtime;
    }

    public ProjectInfo getProj() {
        return proj;
    }

    public void setProj(ProjectInfo proj) {
        this.proj = proj;
    }
}
