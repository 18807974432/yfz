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

public class BuyHourse {
    private int buyId;
    private int custid;
    private int hourseid;
    private int psid;
    private int processid;
    private String processname;
    private int dicountid;
    private double unitPrice;
    private double totalMoney;
    private String bankid;
    private double firstMoney;
    private double bankmoney;
    private double bankAccrual;
    private int bankYear;
    private double bankPercent;
    private String lawyer;
    private Timestamp buyTime;
    private String salePerson;
    private String userid;
    private Timestamp oprtime;
    private String invalid;
    private int hourseid2;
    private String oprType;
    private Timestamp signTime;
    private Timestamp bankTime;
    private double cashMoney;
    //配置多对一
    private  CustomerInfo customerInfo;
    private  HourseInfo hourseInfo;
    private  Discount discount;

    private Set<WorkProcess> work = new HashSet<WorkProcess>();

    public Set<WorkProcess> getWork() {
        return work;
    }

    public void setWork(Set<WorkProcess> work) {
        this.work = work;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public HourseInfo getHourseInfo() {
        return hourseInfo;
    }

    public void setHourseInfo(HourseInfo hourseInfo) {
        this.hourseInfo = hourseInfo;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public int getBuyId() {
        return buyId;
    }

    public void setBuyId(int buyId) {
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

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getBankid() {
        return bankid;
    }

    public void setBankid(String bankid) {
        this.bankid = bankid;
    }

    public double getFirstMoney() {
        return firstMoney;
    }

    public void setFirstMoney(double firstMoney) {
        this.firstMoney = firstMoney;
    }

    public double getBankmoney() {
        return bankmoney;
    }

    public void setBankmoney(double bankmoney) {
        this.bankmoney = bankmoney;
    }

    public double getBankAccrual() {
        return bankAccrual;
    }

    public void setBankAccrual(double bankAccrual) {
        this.bankAccrual = bankAccrual;
    }

    public int getBankYear() {
        return bankYear;
    }

    public void setBankYear(int bankYear) {
        this.bankYear = bankYear;
    }

    public double getBankPercent() {
        return bankPercent;
    }

    public void setBankPercent(double bankPercent) {
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

    public int getHourseid2() {
        return hourseid2;
    }

    public void setHourseid2(int hourseid2) {
        this.hourseid2 = hourseid2;
    }

    public String getOprType() {
        return oprType;
    }

    public void setOprType(String oprType) {
        this.oprType = oprType;
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

    public double getCashMoney() {
        return cashMoney;
    }

    public void setCashMoney(double cashMoney) {
        this.cashMoney = cashMoney;
    }

}
