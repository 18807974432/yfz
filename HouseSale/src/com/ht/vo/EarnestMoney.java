package com.ht.vo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

public class EarnestMoney {
    private int earnestId;
    private int buyId;
    private int ticketNo;
    private String ticketFlow;
    private String paidtypeid;
    private Double paidMoney;
    private Timestamp paidTime;
    private String isTranslate;

    public Double getPaidMoney() {
        return paidMoney;
    }

    public void setPaidMoney(Double paidMoney) {
        this.paidMoney = paidMoney;
    }

    public Double getTransMoney() {
        return transMoney;
    }

    public void setTransMoney(Double transMoney) {
        this.transMoney = transMoney;
    }

    private Double transMoney;
    private String status;
    private String userid;
    private Timestamp oprtime;
    private String invalid;

    private BuyHourse buyHourse;
    private Ticket ticket;

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public BuyHourse getBuyHourse() {
        return buyHourse;
    }

    public void setBuyHourse(BuyHourse buyHourse) {
        this.buyHourse = buyHourse;
    }

    public int getEarnestId() {
        return earnestId;
    }

    public void setEarnestId(int earnestId) {
        this.earnestId = earnestId;
    }

    public int getBuyId() {
        return buyId;
    }

    public void setBuyId(int buyId) {
        this.buyId = buyId;
    }

    public int getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(int ticketNo) {
        this.ticketNo = ticketNo;
    }

    public String getTicketFlow() {
        return ticketFlow;
    }

    public void setTicketFlow(String ticketFlow) {
        this.ticketFlow = ticketFlow;
    }

    public String getPaidtypeid() {
        return paidtypeid;
    }

    public void setPaidtypeid(String paidtypeid) {
        this.paidtypeid = paidtypeid;
    }

    public Timestamp getPaidTime() {
        return paidTime;
    }

    public void setPaidTime(Timestamp paidTime) {
        this.paidTime = paidTime;
    }

    public String getIsTranslate() {
        return isTranslate;
    }

    public void setIsTranslate(String isTranslate) {
        this.isTranslate = isTranslate;
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

    public String getInvalid() {
        return invalid;
    }

    public void setInvalid(String invalid) {
        this.invalid = invalid;
    }

}
