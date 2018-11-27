package com.ht.vo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

public class WorkProcess {
    private int processid;
    private int buyid;
    private int custid;
    private int hourseid;
    private String flowname;
    private String flowNode;
    private Timestamp startTime;
    private Timestamp finishTime;
    private double planMoney;
    private double factMoney;
    private int orderid;
    private String status;
    private String userid;
    private Timestamp oprtime;
    private BuyHourse buy;
    private HourseInfo hourseInfo;
    private CustomerInfo customerInfo;

    public HourseInfo getHourseInfo() {
        return hourseInfo;
    }

    public void setHourseInfo(HourseInfo hourseInfo) {
        this.hourseInfo = hourseInfo;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public BuyHourse getBuy() {
        return buy;
    }

    public void setBuy(BuyHourse buy) {
        this.buy = buy;
    }

    public int getProcessid() {
        return processid;
    }

    public void setProcessid(int processid) {
        this.processid = processid;
    }

    public int getBuyid() {
        return buyid;
    }

    public void setBuyid(int buyid) {
        this.buyid = buyid;
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

    public String getFlowname() {
        return flowname;
    }

    public void setFlowname(String flowname) {
        this.flowname = flowname;
    }

    public String getFlowNode() {
        return flowNode;
    }

    public void setFlowNode(String flowNode) {
        this.flowNode = flowNode;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Timestamp finishTime) {
        this.finishTime = finishTime;
    }

    public double getPlanMoney() {
        return planMoney;
    }

    public void setPlanMoney(double planMoney) {
        this.planMoney = planMoney;
    }

    public double getFactMoney() {
        return factMoney;
    }

    public void setFactMoney(double factMoney) {
        this.factMoney = factMoney;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
