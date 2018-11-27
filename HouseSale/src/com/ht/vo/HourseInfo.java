package com.ht.vo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public class HourseInfo {
    private int hourseId;
    private int termId;
    private String hourseName;
    private String hourseType;
    private String termType;
    private String fitment;
    private int unitid;
    private int floor;
    private String hourseno;
    private String contractno;
    private String saleState;
    private String direction;
    private String struts;
    private String buildstyle;
    private String modelname;
    private String lift;
    private String isbalance;
    private Double saleArea;
    private Double inArea;
    private Double buildArea;
    private Double viewArea;
    private Double downArea;
    private Double publicArea;
    private Double tableArea;
    private Double outArea;
    private Double usePercent;
    private Double facttableArea;
    private Double factinArea;
    private Double factbuildArea;
    private Double factviewArea;
    private Double factdownArea;
    private Double factpublicArea;
    private Double unitPrice;
    private Double inunitPrice;
    private Double buildunitPrice;
    private Double totalPrice;
    private Double oldtotalPrice;
    private Double oldunitPrice;
    private Double unitlowPrice;
    private Double totallowPrice;
    private Date saleTime;
    private String doorno;
    private Double discount;
    private Double discountPrice;
    private Double commisionPercent;
    private Double commisionMoney;
    private Double commisionpaid;
    private int custid;
    private String description;
    private TermInfo term;
    private Housemodel housemodel;
    private TermTypeInfo termTypeInfo;
    private SaleState sale;
    public TermTypeInfo getTermTypeInfo() {
        return termTypeInfo;
    }

    public void setTermTypeInfo(TermTypeInfo termTypeInfo) {
        this.termTypeInfo = termTypeInfo;
    }

    public SaleState getSale() {
        return sale;
    }

    public void setSale(SaleState sale) {
        this.sale = sale;
    }

    public TermInfo getTerm() {
        return term;
    }

    public void setTerm(TermInfo term) {
        this.term = term;
    }

    public Housemodel getHousemodel() {
        return housemodel;
    }

    public void setHousemodel(Housemodel housemodel) {
        this.housemodel = housemodel;
    }

    public int getHourseId() {
        return hourseId;
    }

    public void setHourseId(int hourseId) {
        this.hourseId = hourseId;
    }

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }

    public String getHourseName() {
        return hourseName;
    }

    public void setHourseName(String hourseName) {
        this.hourseName = hourseName;
    }

    public String getHourseType() {
        return hourseType;
    }

    public void setHourseType(String hourseType) {
        this.hourseType = hourseType;
    }

    public String getTermType() {
        return termType;
    }

    public void setTermType(String termType) {
        this.termType = termType;
    }

    public String getFitment() {
        return fitment;
    }

    public void setFitment(String fitment) {
        this.fitment = fitment;
    }

    public int getUnitid() {
        return unitid;
    }

    public void setUnitid(int unitid) {
        this.unitid = unitid;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getHourseno() {
        return hourseno;
    }

    public void setHourseno(String hourseno) {
        this.hourseno = hourseno;
    }

    public String getContractno() {
        return contractno;
    }

    public void setContractno(String contractno) {
        this.contractno = contractno;
    }

    public String getSaleState() {
        return saleState;
    }

    public void setSaleState(String saleState) {
        this.saleState = saleState;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getStruts() {
        return struts;
    }

    public void setStruts(String struts) {
        this.struts = struts;
    }

    public String getBuildstyle() {
        return buildstyle;
    }

    public void setBuildstyle(String buildstyle) {
        this.buildstyle = buildstyle;
    }

    public String getModelname() {
        return modelname;
    }

    public void setModelname(String modelname) {
        this.modelname = modelname;
    }

    public String getLift() {
        return lift;
    }

    public void setLift(String lift) {
        this.lift = lift;
    }

    public String getIsbalance() {
        return isbalance;
    }

    public void setIsbalance(String isbalance) {
        this.isbalance = isbalance;
    }

    public Double getSaleArea() {
        return saleArea;
    }

    public void setSaleArea(Double saleArea) {
        this.saleArea = saleArea;
    }

    public Double getInArea() {
        return inArea;
    }

    public void setInArea(Double inArea) {
        this.inArea = inArea;
    }

    public Double getBuildArea() {
        return buildArea;
    }

    public void setBuildArea(Double buildArea) {
        this.buildArea = buildArea;
    }

    public Double getViewArea() {
        return viewArea;
    }

    public void setViewArea(Double viewArea) {
        this.viewArea = viewArea;
    }

    public Double getDownArea() {
        return downArea;
    }

    public void setDownArea(Double downArea) {
        this.downArea = downArea;
    }

    public Double getPublicArea() {
        return publicArea;
    }

    public void setPublicArea(Double publicArea) {
        this.publicArea = publicArea;
    }

    public Double getTableArea() {
        return tableArea;
    }

    public void setTableArea(Double tableArea) {
        this.tableArea = tableArea;
    }

    public Double getOutArea() {
        return outArea;
    }

    public void setOutArea(Double outArea) {
        this.outArea = outArea;
    }

    public Double getUsePercent() {
        return usePercent;
    }

    public void setUsePercent(Double usePercent) {
        this.usePercent = usePercent;
    }

    public Double getFacttableArea() {
        return facttableArea;
    }

    public void setFacttableArea(Double facttableArea) {
        this.facttableArea = facttableArea;
    }

    public Double getFactinArea() {
        return factinArea;
    }

    public void setFactinArea(Double factinArea) {
        this.factinArea = factinArea;
    }

    public Double getFactbuildArea() {
        return factbuildArea;
    }

    public void setFactbuildArea(Double factbuildArea) {
        this.factbuildArea = factbuildArea;
    }

    public Double getFactviewArea() {
        return factviewArea;
    }

    public void setFactviewArea(Double factviewArea) {
        this.factviewArea = factviewArea;
    }

    public Double getFactdownArea() {
        return factdownArea;
    }

    public void setFactdownArea(Double factdownArea) {
        this.factdownArea = factdownArea;
    }

    public Double getFactpublicArea() {
        return factpublicArea;
    }

    public void setFactpublicArea(Double factpublicArea) {
        this.factpublicArea = factpublicArea;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getInunitPrice() {
        return inunitPrice;
    }

    public void setInunitPrice(Double inunitPrice) {
        this.inunitPrice = inunitPrice;
    }

    public Double getBuildunitPrice() {
        return buildunitPrice;
    }

    public void setBuildunitPrice(Double buildunitPrice) {
        this.buildunitPrice = buildunitPrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getOldtotalPrice() {
        return oldtotalPrice;
    }

    public void setOldtotalPrice(Double oldtotalPrice) {
        this.oldtotalPrice = oldtotalPrice;
    }

    public Double getOldunitPrice() {
        return oldunitPrice;
    }

    public void setOldunitPrice(Double oldunitPrice) {
        this.oldunitPrice = oldunitPrice;
    }

    public Double getUnitlowPrice() {
        return unitlowPrice;
    }

    public void setUnitlowPrice(Double unitlowPrice) {
        this.unitlowPrice = unitlowPrice;
    }

    public Double getTotallowPrice() {
        return totallowPrice;
    }

    public void setTotallowPrice(Double totallowPrice) {
        this.totallowPrice = totallowPrice;
    }

    public Date getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(Date saleTime) {
        this.saleTime = saleTime;
    }

    public String getDoorno() {
        return doorno;
    }

    public void setDoorno(String doorno) {
        this.doorno = doorno;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Double getCommisionPercent() {
        return commisionPercent;
    }

    public void setCommisionPercent(Double commisionPercent) {
        this.commisionPercent = commisionPercent;
    }

    public Double getCommisionMoney() {
        return commisionMoney;
    }

    public void setCommisionMoney(Double commisionMoney) {
        this.commisionMoney = commisionMoney;
    }

    public Double getCommisionpaid() {
        return commisionpaid;
    }

    public void setCommisionpaid(Double commisionpaid) {
        this.commisionpaid = commisionpaid;
    }

    public int getCustid() {
        return custid;
    }

    public void setCustid(int custid) {
        this.custid = custid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
