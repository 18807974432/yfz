package com.ht.vo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public class CustomerInfo {
    private int custId;//客户编号
    private int projectid;//项目编号
    private String custname;//客户名称
    private String custnamecode;//拼音代码
    private String custtype;//客户性质
    private String sex;//性别

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getSigndate() {
        return signdate;
    }

    public void setSigndate(Date signdate) {
        this.signdate = signdate;
    }

    private String chargeperson;//主管业务员
    private String country;//国家
    private String nation;//民族
    private String babyaddr;//户籍
    private String cardname;//证件名称
    private String cardno;//证件号码
    private String degree;//文化程度
    private String custstate;//客户类型
    private String age;//年龄
    private String custVip;//客户身份类别
    private Date birthday;//出生日期
    private Date signdate;//注册日期
    private String signAddr;//登记地点
    private String familyTel;//家庭电话
    private String primaryTel;//首选联系电话
    private String officeTel;//办公电话
    private String mobile;//手机号码
    private String fax;//传真号码
    private String postcode;//邮政编码
    private String email;//电子邮件
    private String addr;//家庭地址
    private String saleperson;//业务员
    private String activeid;//信息来源
    private String salePercent;//成交几率
    private String chance;//机会阶段
    private String eventtypeid;//事件类型
    //多对一配置
    private ProjectInfo pro;

    public ProjectInfo getPro() {
        return pro;
    }

    public void setPro(ProjectInfo pro) {
        this.pro = pro;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public int getProjectid() {
        return projectid;
    }

    public void setProjectid(int projectid) {
        this.projectid = projectid;
    }

    public String getCustname() {
        return custname;
    }

    public void setCustname(String custname) {
        this.custname = custname;
    }

    public String getCustnamecode() {
        return custnamecode;
    }

    public void setCustnamecode(String custnamecode) {
        this.custnamecode = custnamecode;
    }

    public String getCusttype() {
        return custtype;
    }

    public void setCusttype(String custtype) {
        this.custtype = custtype;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getChargeperson() {
        return chargeperson;
    }

    public void setChargeperson(String chargeperson) {
        this.chargeperson = chargeperson;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getBabyaddr() {
        return babyaddr;
    }

    public void setBabyaddr(String babyaddr) {
        this.babyaddr = babyaddr;
    }

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getCuststate() {
        return custstate;
    }

    public void setCuststate(String custstate) {
        this.custstate = custstate;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCustVip() {
        return custVip;
    }

    public void setCustVip(String custVip) {
        this.custVip = custVip;
    }





    public String getSignAddr() {
        return signAddr;
    }

    public void setSignAddr(String signAddr) {
        this.signAddr = signAddr;
    }

    public String getFamilyTel() {
        return familyTel;
    }

    public void setFamilyTel(String familyTel) {
        this.familyTel = familyTel;
    }

    public String getPrimaryTel() {
        return primaryTel;
    }

    public void setPrimaryTel(String primaryTel) {
        this.primaryTel = primaryTel;
    }

    public String getOfficeTel() {
        return officeTel;
    }

    public void setOfficeTel(String officeTel) {
        this.officeTel = officeTel;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getSaleperson() {
        return saleperson;
    }

    public void setSaleperson(String saleperson) {
        this.saleperson = saleperson;
    }

    public String getActiveid() {
        return activeid;
    }

    public void setActiveid(String activeid) {
        this.activeid = activeid;
    }

    public String getSalePercent() {
        return salePercent;
    }

    public void setSalePercent(String salePercent) {
        this.salePercent = salePercent;
    }

    public String getChance() {
        return chance;
    }

    public void setChance(String chance) {
        this.chance = chance;
    }

    public String getEventtypeid() {
        return eventtypeid;
    }

    public void setEventtypeid(String eventtypeid) {
        this.eventtypeid = eventtypeid;
    }

}
