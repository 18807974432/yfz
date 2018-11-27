package com.ht.vo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

public class HistoryHouse {
    private int historyid;
    private Integer buyId;
    private int custid;
    private int hourseid;
    private int psid;
    private int processid;
    private String processname;
    private int dicountid;
    private BigDecimal unitPrice;
    private BigDecimal totalMoney;
    private String bankid;
    private BigDecimal firstMoney;
    private BigDecimal bankmoney;
    private BigDecimal bankAccrual;
    private Integer bankYear;
    private BigDecimal bankPercent;
    private String lawyer;
    private Timestamp buyTime;
    private String salePerson;
    private String userid;
    private Timestamp oprtime;
    private String invalid;
    private String oprType;
    private Integer hourseid2;
    private Timestamp signTime;
    private Timestamp bankTime;
    private BigDecimal cashMoney;

    public int getHistoryid() {
        return historyid;
    }

    public void setHistoryid(int historyid) {
        this.historyid = historyid;
    }

    public Integer getBuyId() {
        return buyId;
    }

    public void setBuyId(Integer buyId) {
        this.buyId = buyId;
    }

    public int getCustid() {
        return custid;
    }

    public void setCustid(int custid) {
        this.custid = custid;
    }

    public int getHourseid() {
        return hourseid;
    }

    public void setHourseid(int hourseid) {
        this.hourseid = hourseid;
    }

    public int getPsid() {
        return psid;
    }

    public void setPsid(int psid) {
        this.psid = psid;
    }

    public int getProcessid() {
        return processid;
    }

    public void setProcessid(int processid) {
        this.processid = processid;
    }

    public String getProcessname() {
        return processname;
    }

    public void setProcessname(String processname) {
        this.processname = processname;
    }

    public int getDicountid() {
        return dicountid;
    }

    public void setDicountid(int dicountid) {
        this.dicountid = dicountid;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getBankid() {
        return bankid;
    }

    public void setBankid(String bankid) {
        this.bankid = bankid;
    }

    public BigDecimal getFirstMoney() {
        return firstMoney;
    }

    public void setFirstMoney(BigDecimal firstMoney) {
        this.firstMoney = firstMoney;
    }

    public BigDecimal getBankmoney() {
        return bankmoney;
    }

    public void setBankmoney(BigDecimal bankmoney) {
        this.bankmoney = bankmoney;
    }

    public BigDecimal getBankAccrual() {
        return bankAccrual;
    }

    public void setBankAccrual(BigDecimal bankAccrual) {
        this.bankAccrual = bankAccrual;
    }

    public Integer getBankYear() {
        return bankYear;
    }

    public void setBankYear(Integer bankYear) {
        this.bankYear = bankYear;
    }

    public BigDecimal getBankPercent() {
        return bankPercent;
    }

    public void setBankPercent(BigDecimal bankPercent) {
        this.bankPercent = bankPercent;
    }

    public String getLawyer() {
        return lawyer;
    }

    public void setLawyer(String lawyer) {
        this.lawyer = lawyer;
    }

    public Timestamp getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Timestamp buyTime) {
        this.buyTime = buyTime;
    }

    public String getSalePerson() {
        return salePerson;
    }

    public void setSalePerson(String salePerson) {
        this.salePerson = salePerson;
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

    public String getInvalid() {
        return invalid;
    }

    public void setInvalid(String invalid) {
        this.invalid = invalid;
    }

    public String getOprType() {
        return oprType;
    }

    public void setOprType(String oprType) {
        this.oprType = oprType;
    }

    public Integer getHourseid2() {
        return hourseid2;
    }

    public void setHourseid2(Integer hourseid2) {
        this.hourseid2 = hourseid2;
    }

    public Timestamp getSignTime() {
        return signTime;
    }

    public void setSignTime(Timestamp signTime) {
        this.signTime = signTime;
    }

    public Timestamp getBankTime() {
        return bankTime;
    }

    public void setBankTime(Timestamp bankTime) {
        this.bankTime = bankTime;
    }

    public BigDecimal getCashMoney() {
        return cashMoney;
    }

    public void setCashMoney(BigDecimal cashMoney) {
        this.cashMoney = cashMoney;
    }

}
