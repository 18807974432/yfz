package com.ht.vo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

public class Finance {
    private int financeId;
    private int buyId;
    private int ticketNo;
    private String ticketFlow;
    private String paidtypeid;
    private BigDecimal paidMoney;
    private String paidPerson;
    private String paidsortid;
    private String inbank;
    private String bankAccount;
    private String outbank;
    private String warrant;
    private Timestamp paidTime;
    private Timestamp paidinTime;
    private String invalid;
    private String userid;
    private Timestamp oprtime;
    private String oprType;

    public int getFinanceId() {
        return financeId;
    }

    public void setFinanceId(int financeId) {
        this.financeId = financeId;
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

    public BigDecimal getPaidMoney() {
        return paidMoney;
    }

    public void setPaidMoney(BigDecimal paidMoney) {
        this.paidMoney = paidMoney;
    }

    public String getPaidPerson() {
        return paidPerson;
    }

    public void setPaidPerson(String paidPerson) {
        this.paidPerson = paidPerson;
    }

    public String getPaidsortid() {
        return paidsortid;
    }

    public void setPaidsortid(String paidsortid) {
        this.paidsortid = paidsortid;
    }

    public String getInbank() {
        return inbank;
    }

    public void setInbank(String inbank) {
        this.inbank = inbank;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getOutbank() {
        return outbank;
    }

    public void setOutbank(String outbank) {
        this.outbank = outbank;
    }

    public String getWarrant() {
        return warrant;
    }

    public void setWarrant(String warrant) {
        this.warrant = warrant;
    }

    public Timestamp getPaidTime() {
        return paidTime;
    }

    public void setPaidTime(Timestamp paidTime) {
        this.paidTime = paidTime;
    }

    public Timestamp getPaidinTime() {
        return paidinTime;
    }

    public void setPaidinTime(Timestamp paidinTime) {
        this.paidinTime = paidinTime;
    }

    public String getInvalid() {
        return invalid;
    }

    public void setInvalid(String invalid) {
        this.invalid = invalid;
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

    public String getOprType() {
        return oprType;
    }

    public void setOprType(String oprType) {
        this.oprType = oprType;
    }

}
