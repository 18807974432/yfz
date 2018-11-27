package bean;

import java.io.Serializable;

public class businessVo implements Serializable{
	public int businessId;
	public String busDate;
	public String prodName;
	public String chatContent;
	public String chatResult;
	public int custid;
	public String custContact;
	public String phone;
	public int userid;
	public String module;
	public String moduleState;
	public double moduleMoney;
	public String remark;
	public int getBusinessId() {
		return businessId;
	}
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}
	public String getBusDate() {
		return busDate;
	}
	public void setBusDate(String busDate) {
		this.busDate = busDate;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getChatContent() {
		return chatContent;
	}
	public void setChatContent(String chatContent) {
		this.chatContent = chatContent;
	}
	public String getChatResult() {
		return chatResult;
	}
	public void setChatResult(String chatResult) {
		this.chatResult = chatResult;
	}
	public int getCustid() {
		return custid;
	}
	public void setCustid(int custid) {
		this.custid = custid;
	}
	public String getCustContact() {
		return custContact;
	}
	public void setCustContact(String custContact) {
		this.custContact = custContact;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getModuleState() {
		return moduleState;
	}
	public void setModuleState(String moduleState) {
		this.moduleState = moduleState;
	}
	public double getModuleMoney() {
		return moduleMoney;
	}
	public void setModuleMoney(double moduleMoney) {
		this.moduleMoney = moduleMoney;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
