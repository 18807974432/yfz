package com.ht.vo;



import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class ExamMaster {
    private int examid;
    private int reseachid;
    private int unitNO;
    private int optCnt;
    private String unitName;
    private String invalid;
    private String unitType;
    private String question1;
    private String question2;
    private String question3;
    private String question4;
    private String question5;
    private String question6;
    private String question7;
    private String question8;
    private String value1;
    private String value2;
    private String value3;
    private String value4;
    private String value5;
    private String value6;
    private String value7;
    private String value8;
    private String userid;
    private Date oprtime;
    private List<Options> item = new ArrayList();
    private ResearchExam research;

    public int getExamid() {
        return examid;
    }

    public void setExamid(int examid) {
        this.examid = examid;
    }

    public int getReseachid() {
        return reseachid;
    }

    public void setReseachid(int reseachid) {
        this.reseachid = reseachid;
    }


    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getInvalid() {
        return invalid;
    }

    public void setInvalid(String invalid) {
        this.invalid = invalid;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public Integer getOptCnt() {
        return optCnt;
    }

    public void setOptCnt(Integer optCnt) {
        this.optCnt = optCnt;
    }

    public String getQuestion1() {
        return question1;
    }

    public void setQuestion1(String question1) {
        this.question1 = question1;
    }

    public String getQuestion2() {
        return question2;
    }

    public void setQuestion2(String question2) {
        this.question2 = question2;
    }

    public String getQuestion3() {
        return question3;
    }

    public void setQuestion3(String question3) {
        this.question3 = question3;
    }

    public String getQuestion4() {
        return question4;
    }

    public void setQuestion4(String question4) {
        this.question4 = question4;
    }

    public String getQuestion5() {
        return question5;
    }

    public void setQuestion5(String question5) {
        this.question5 = question5;
    }

    public String getQuestion6() {
        return question6;
    }

    public void setQuestion6(String question6) {
        this.question6 = question6;
    }

    public String getQuestion7() {
        return question7;
    }

    public void setQuestion7(String question7) {
        this.question7 = question7;
    }

    public String getQuestion8() {
        return question8;
    }

    public void setQuestion8(String question8) {
        this.question8 = question8;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public String getValue3() {
        return value3;
    }

    public void setValue3(String value3) {
        this.value3 = value3;
    }

    public String getValue4() {
        return value4;
    }

    public void setValue4(String value4) {
        this.value4 = value4;
    }

    public String getValue5() {
        return value5;
    }

    public void setValue5(String value5) {
        this.value5 = value5;
    }

    public String getValue6() {
        return value6;
    }

    public void setValue6(String value6) {
        this.value6 = value6;
    }

    public String getValue7() {
        return value7;
    }

    public void setValue7(String value7) {
        this.value7 = value7;
    }

    public String getValue8() {
        return value8;
    }

    public void setValue8(String value8) {
        this.value8 = value8;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getUnitNO() {
        return unitNO;
    }

    public void setUnitNO(int unitNO) {
        this.unitNO = unitNO;
    }

    public void setOptCnt(int optCnt) {
        this.optCnt = optCnt;
    }

    public Date getOprtime() {
        return oprtime;
    }

    public void setOprtime(Date oprtime) {
        this.oprtime = oprtime;
    }

    public void setOprtime(Timestamp oprtime) {
        this.oprtime = oprtime;
    }

    public ResearchExam getResearch() {
        return research;
    }

    public void setResearch(ResearchExam research) {
        this.research = research;
    }
    public List<Options> getItem() {
        Options op = null;
        for (int i = 0; i < this.optCnt; i++) {
            switch (i)
            {
                case 0:
                    op = new Options();
                    op.setOrderid("A");
                    op.setQuestion(this.question1);
                    op.setAnswer(this.value1);
                    this.item.add(op);
                    break;
                case 1:
                    op = new Options();
                    op.setOrderid("B");
                    op.setQuestion(this.question2);
                    op.setAnswer(this.value2);
                    this.item.add(op);
                    break;
                case 2:
                    op = new Options();
                    op.setOrderid("C");
                    op.setQuestion(this.question3);
                    op.setAnswer(this.value3);
                    this.item.add(op);
                    break;
                case 3:
                    op = new Options();
                    op.setOrderid("D");
                    op.setQuestion(this.question4);
                    op.setAnswer(this.value4);
                    this.item.add(op);
                    break;
                case 4:
                    op = new Options();
                    op.setOrderid("E");
                    op.setQuestion(this.question5);
                    op.setAnswer(this.value5);
                    this.item.add(op);
                    break;
                case 5:
                    op = new Options();
                    op.setOrderid("F");
                    op.setQuestion(this.question6);
                    op.setAnswer(this.value6);
                    this.item.add(op);
                    break;
                case 6:
                    op = new Options();
                    op.setOrderid("G");
                    op.setQuestion(this.question7);
                    op.setAnswer(this.value7);
                    this.item.add(op);
                    break;
                case 7:
                    op = new Options();
                    op.setOrderid("H");
                    op.setQuestion(this.question8);
                    op.setAnswer(this.value8);
                    this.item.add(op);
            }
        }
        return this.item;
    }

    public void setItem(List<Options> item) {
        this.item = item;
    }
}
