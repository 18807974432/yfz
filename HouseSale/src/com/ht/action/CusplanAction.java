package com.ht.action;

import com.alibaba.fastjson.JSONObject;
import com.ht.dao.ContextUtils;
import com.ht.dao.IBaseDAO;
import com.ht.dao.WebApplicationContextUtil;
import com.ht.vo.*;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by rainbow on 2018/11/5.
 */
public class CusplanAction extends ActionSupport {
    private IBaseDAO base= (IBaseDAO) WebApplicationContextUtil.createService("dao");
    private HttpServletResponse response = ServletActionContext.getResponse();
    private CustPlan custPlan;
    private List<CustPlan> custPlanList;
    private CustomerInfo custom;
    private TermTypeInfo termTypeInfo;
    private List<TermTypeInfo> termTypeInfoList;

    public TermTypeInfo getTermTypeInfo() {
        return termTypeInfo;
    }

    public void setTermTypeInfo(TermTypeInfo termTypeInfo) {
        this.termTypeInfo = termTypeInfo;
    }

    public List<TermTypeInfo> getTermTypeInfoList() {
        return termTypeInfoList;
    }

    public void setTermTypeInfoList(List<TermTypeInfo> termTypeInfoList) {
        this.termTypeInfoList = termTypeInfoList;
    }

    private Housemodel housemodel;
    private List<Housemodel>  housemodelList;
    private PaidType paidType;
    private List<PaidType> paidTypeList;

    public Housemodel getHousemodel() {
        return housemodel;
    }

    public void setHousemodel(Housemodel housemodel) {
        this.housemodel = housemodel;
    }

    public List<Housemodel> getHousemodelList() {
        return housemodelList;
    }

    public void setHousemodelList(List<Housemodel> housemodelList) {
        this.housemodelList = housemodelList;
    }

    public PaidType getPaidType() {
        return paidType;
    }

    public void setPaidType(PaidType paidType) {
        this.paidType = paidType;
    }

    public List<PaidType> getPaidTypeList() {
        return paidTypeList;
    }

    public void setPaidTypeList(List<PaidType> paidTypeList) {
        this.paidTypeList = paidTypeList;
    }


    public CustomerInfo getCustom() {
        return custom;
    }

    public void setCustom(CustomerInfo custom) {
        this.custom = custom;
    }
    public  String list(){
        DetachedCriteria dc=DetachedCriteria.forClass(Housemodel.class);
        housemodelList=base.findByDetach(dc);
        DetachedCriteria dc2=DetachedCriteria.forClass(PaidType.class);
        dc2.add(Restrictions.eq("invalid","有效"));
        paidTypeList=base.findByDetach(dc2);
        DetachedCriteria dc3=DetachedCriteria.forClass(TermTypeInfo.class);
        termTypeInfoList=base.findByDetach(dc3);
        return INPUT;

    }
    public  String  save() throws IOException {

       /* if (custPlan.getPlanId() > 0) {
            ContextUtils.saveUserLog(custPlan, 2);
        } else {
            ContextUtils.saveUserLog(custPlan, 1);
        }*/
        custPlan.setCustid(custom.getCustId());
        base.saveOrUpdate(custPlan);
        getjson();
       return null;
    }
    public String del() throws IOException {
        custPlan= (CustPlan) base.findObjById(CustPlan.class,custPlan.getPlanId());
        base.delete(custPlan);
        getjson();
        return null;
    }
    public String update(){
        DetachedCriteria dc=DetachedCriteria.forClass(Housemodel.class);
        housemodelList=base.findByDetach(dc);
        DetachedCriteria dc2=DetachedCriteria.forClass(PaidType.class);
        dc2.add(Restrictions.eq("invalid","有效"));
        paidTypeList=base.findByDetach(dc2);
        DetachedCriteria dc3=DetachedCriteria.forClass(TermTypeInfo.class);
        termTypeInfoList=base.findByDetach(dc3);
        custPlan= (CustPlan) base.findObjById(CustPlan.class,custPlan.getPlanId());
        return  INPUT;
    }
    public  String getjson() throws IOException {
       response.setContentType("text/html;charset=utf-8");
       PrintWriter out = response.getWriter();
       DetachedCriteria dc4=DetachedCriteria.forClass(CustPlan.class);
       dc4.add(Restrictions.eq("custid",custom.getCustId()));
       custPlanList=base.findByDetach(dc4);
       int count=custPlanList.size();
       JSONObject jsonObject=new JSONObject();
       jsonObject.put("total",count);
       jsonObject.put("rows",custPlanList);
       out.print(jsonObject.toJSONString());
       out.flush();
       out.close();

       return null;
   }
    public CustPlan getCustPlan() {
        return custPlan;
    }

    public void setCustPlan(CustPlan custPlan) {
        this.custPlan = custPlan;
    }

    public List<CustPlan> getCustPlanList() {
        return custPlanList;
    }

    public void setCustPlanList(List<CustPlan> custPlanList) {
        this.custPlanList = custPlanList;
    }



}
