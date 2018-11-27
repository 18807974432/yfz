package bean;

import java.io.Serializable;

public class PaidTypeVo implements Serializable{
	public int paidtypeid;
	public String paidtypename;
	public int getPaidtypeid() {
		return paidtypeid;
	}
	public void setPaidtypeid(int paidtypeid) {
		this.paidtypeid = paidtypeid;
	}
	public String getPaidtypename() {
		return paidtypename;
	}
	public void setPaidtypename(String paidtypename) {
		this.paidtypename = paidtypename;
	}

}
