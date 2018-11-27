package bean;

import java.io.Serializable;

public class TicketVo implements Serializable{
	public int id;
	public String ticketDate;
	public String orderid;
	public int custid;
	public String ticketMoney;
	public String ticketComp;
	public String username;
	public String oprtime;
	public String remark;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTicketDate() {
		return ticketDate;
	}
	public void setTicketDate(String ticketDate) {
		this.ticketDate = ticketDate;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public int getCustid() {
		return custid;
	}
	public void setCustid(int custid) {
		this.custid = custid;
	}
	public String getTicketMoney() {
		return ticketMoney;
	}
	public void setTicketMoney(String ticketMoney) {
		this.ticketMoney = ticketMoney;
	}
	public String getTicketComp() {
		return ticketComp;
	}
	public void setTicketComp(String ticketComp) {
		this.ticketComp = ticketComp;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
