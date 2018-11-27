package bean;

import java.io.Serializable;

public class financeVo implements Serializable{
	public int financeId;
	public String orderId;
	public int prodid;
	public String paidtypeid;
	public String remainMoney;
	public String paidMoney;
	public String orderMoney;
	public String paidPerson;
	public String inbank;
	public String bankAccount;
	public String outbank;
	public String warrant;
	public String paidTime;
	public String paidinTime;
	public String invalid;
	public String username;
	public String oprtime;
	public String oprType;
	public int getFinanceId() {
		return financeId;
	}
	public void setFinanceId(int financeId) {
		this.financeId = financeId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public int getProdid() {
		return prodid;
	}
	public void setProdid(int prodid) {
		this.prodid = prodid;
	}
	public String getPaidtypeid() {
		return paidtypeid;
	}
	public void setPaidtypeid(String paidtypeid) {
		this.paidtypeid = paidtypeid;
	}
	public String getRemainMoney() {
		return remainMoney;
	}
	public void setRemainMoney(String remainMoney) {
		this.remainMoney = remainMoney;
	}
	public String getPaidMoney() {
		return paidMoney;
	}
	public void setPaidMoney(String paidMoney) {
		this.paidMoney = paidMoney;
	}
	public String getOrderMoney() {
		return orderMoney;
	}
	public void setOrderMoney(String orderMoney) {
		this.orderMoney = orderMoney;
	}
	public String getPaidPerson() {
		return paidPerson;
	}
	public void setPaidPerson(String paidPerson) {
		this.paidPerson = paidPerson;
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
	public String getPaidTime() {
		return paidTime;
	}
	public void setPaidTime(String paidTime) {
		this.paidTime = paidTime;
	}
	public String getPaidinTime() {
		return paidinTime;
	}
	public void setPaidinTime(String paidinTime) {
		this.paidinTime = paidinTime;
	}
	public String getInvalid() {
		return invalid;
	}
	public void setInvalid(String invalid) {
		this.invalid = invalid;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getOprtime() {
		return oprtime;
	}
	public void setOprtime(String oprtime) {
		this.oprtime = oprtime;
	}
	public String getOprType() {
		return oprType;
	}
	public void setOprType(String oprType) {
		this.oprType = oprType;
	}

}
