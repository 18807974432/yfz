package bean;

import java.io.Serializable;

public class ProductVo implements Serializable{
	public String prodid;
	public String prodname;
	public String prodModel;
	public int prodUnit;
	public String prodStyle;
	public float prodCount;
	public double inPrice;
	public double salePrice;
	public double lowSalePrice;
	public String prodSerial;
	public String cdKey;
	public String saleStatus;
	public int supplierId;
	public String remark;
	public String getProdid() {
		return prodid;
	}
	public void setProdid(String prodid) {
		this.prodid = prodid;
	}
	public String getProdname() {
		return prodname;
	}
	public void setProdname(String prodname) {
		this.prodname = prodname;
	}
	public String getProdModel() {
		return prodModel;
	}
	public void setProdModel(String prodModel) {
		this.prodModel = prodModel;
	}
	public int getProdUnit() {
		return prodUnit;
	}
	public void setProdUnit(int prodUnit) {
		this.prodUnit = prodUnit;
	}
	public String getProdStyle() {
		return prodStyle;
	}
	public void setProdStyle(String prodStyle) {
		this.prodStyle = prodStyle;
	}
	public float getProdCount() {
		return prodCount;
	}
	public void setProdCount(float prodCount) {
		this.prodCount = prodCount;
	}
	public double getInPrice() {
		return inPrice;
	}
	public void setInPrice(double inPrice) {
		this.inPrice = inPrice;
	}
	public double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}
	public double getLowSalePrice() {
		return lowSalePrice;
	}
	public void setLowSalePrice(double lowSalePrice) {
		this.lowSalePrice = lowSalePrice;
	}
	public String getProdSerial() {
		return prodSerial;
	}
	public void setProdSerial(String prodSerial) {
		this.prodSerial = prodSerial;
	}
	public String getCdKey() {
		return cdKey;
	}
	public void setCdKey(String cdKey) {
		this.cdKey = cdKey;
	}
	public String getSaleStatus() {
		return saleStatus;
	}
	public void setSaleStatus(String saleStatus) {
		this.saleStatus = saleStatus;
	}
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
