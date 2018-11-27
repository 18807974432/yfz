package bean;

import java.io.Serializable;

public class jobRecordVo implements Serializable{
	public int jobId;
	public String orderId;
	public String jobDate;
	public String prodName;
	public String custid;
	public String jobContent;
	public String callback;
	public String userid;
	public String custEval;
	public String custSign;
	public String startTime;
	public String endTime;
	public String workDay;
	public String busMoney;
	public String attachMoney;
	
	
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getJobDate() {
		return jobDate;
	}
	public void setJobDate(String jobDate) {
		this.jobDate = jobDate;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}
	public String getJobContent() {
		return jobContent;
	}
	public void setJobContent(String jobContent) {
		this.jobContent = jobContent;
	}
	public String getCallback() {
		return callback;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getCustEval() {
		return custEval;
	}
	public void setCustEval(String custEval) {
		this.custEval = custEval;
	}
	public String getCustSign() {
		return custSign;
	}
	public void setCustSign(String custSign) {
		this.custSign = custSign;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getWorkDay() {
		return workDay;
	}
	public void setWorkDay(String workDay) {
		this.workDay = workDay;
	}
	public String getBusMoney() {
		return busMoney;
	}
	public void setBusMoney(String busMoney) {
		this.busMoney = busMoney;
	}
	public String getAttachMoney() {
		return attachMoney;
	}
	public void setAttachMoney(String attachMoney) {
		this.attachMoney = attachMoney;
	}

}
