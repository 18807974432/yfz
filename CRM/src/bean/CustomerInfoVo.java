package bean;

import java.io.Serializable;

public class CustomerInfoVo implements Serializable{
	public int custId;
	public String custname;
	public String custtype;
	public String bankAccount;
	public String bankName;
	public String contact;
	public String phone;
	public String ticketName;
	public String ticketAddr;
	public String ticketTel;
	public String taxNo;
	public String custState;
	public String username;
	public String source;
	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	public String getCustname() {
		return custname;
	}
	public void setCustname(String custname) {
		this.custname = custname;
	}
	public String getCusttype() {
		return custtype;
	}
	public void setCusttype(String custtype) {
		this.custtype = custtype;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTicketName() {
		return ticketName;
	}
	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}
	public String getTicketAddr() {
		return ticketAddr;
	}
	public void setTicketAddr(String ticketAddr) {
		this.ticketAddr = ticketAddr;
	}
	public String getTicketTel() {
		return ticketTel;
	}
	public void setTicketTel(String ticketTel) {
		this.ticketTel = ticketTel;
	}
	public String getTaxNo() {
		return taxNo;
	}
	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}
	public String getCustState() {
		return custState;
	}
	public void setCustState(String custState) {
		this.custState = custState;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}

}
