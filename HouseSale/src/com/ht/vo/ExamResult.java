package com.ht.vo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

public class ExamResult {
    private int resultid;
    private int reseachid;
    private int examid;
    private String result;
    private Integer custid;
    private String userid;
    private Timestamp oprtime;
    private ResearchExam research;
    private ExamMaster exam;

    public ExamMaster getExam() {
        return exam;
    }

    public void setExam(ExamMaster exam) {
        this.exam = exam;
    }

    public ResearchExam getResearch() {
        return research;
    }

    public void setResearch(ResearchExam research) {
        this.research = research;
    }

    public int getResultid() {
        return resultid;
    }

    public void setResultid(int resultid) {
        this.resultid = resultid;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getCustid() {
        return custid;
    }

    public void setCustid(Integer custid) {
        this.custid = custid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Timestamp getOprtime() {
        return oprtime;
    }

    public void setOprtime(Timestamp oprtime) {
        this.oprtime = oprtime;
    }

}
