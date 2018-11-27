package com.ht.action;

import com.alibaba.fastjson.JSON;
import com.ht.dao.BaseDAO;
import com.ht.dao.PageObject;
import com.ht.dao.WebApplicationContextUtil;
import com.ht.vo.*;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuyHourseAction  extends ActionSupport {
    private BaseDAO baseDAO=(BaseDAO) WebApplicationContextUtil.createService("dao");

    private BuyHourse buyHourse=new BuyHourse();
    private CustomerInfo customerInfo=new CustomerInfo();
    private HourseInfo hourseInfo=new HourseInfo();
    private PaidScheme paidScheme=new PaidScheme();
    private WorkProcess workProcess=new WorkProcess();
    private DiscountScheme discountScheme=new DiscountScheme();

    public DiscountScheme getDiscountScheme() {
        return discountScheme;
    }
    public void setDiscountScheme(DiscountScheme discountScheme) {
        this.discountScheme = discountScheme;
    }
    public WorkProcess getWorkProcess() {
        return workProcess;
    }
    public void setWorkProcess(WorkProcess workProcess) {
        this.workProcess = workProcess;
    }
    public HourseInfo getHourseInfo() {
        return hourseInfo;
    }
    public void setHourseInfo(HourseInfo hourseInfo) {
        this.hourseInfo = hourseInfo;
    }
    public PaidScheme getPaidScheme() {
        return paidScheme;
    }
    public void setPaidScheme(PaidScheme paidScheme) {
        this.paidScheme = paidScheme;
    }
    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }
    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    private PageObject pageObject=new PageObject();
    private List<BuyHourse> buyHourseList=new ArrayList<>();

    public PageObject getPageObject() {
        return pageObject;
    }
    public void setPageObject(PageObject pageObject) {
        this.pageObject = pageObject;
    }
    public BuyHourse getBuyHourse() {
        return buyHourse;
    }
    public void setBuyHourse(BuyHourse buyHourse) {
        this.buyHourse = buyHourse;
    }
    HttpServletResponse hsr=ServletActionContext.getResponse();
    HttpServletRequest request=ServletActionContext.getRequest();

    private Map condition=new HashMap();
    public void list() throws IOException {
        String page=request.getParameter("page");
        String rows=request.getParameter("rows");//每页显示记录数
        if(page==null||page.equals("")){
            pageObject.setCur_page(1);
        }else{
            pageObject.setCur_page(Integer.parseInt(page));
        }
        if(rows==null||rows.equals("")){
            pageObject.setPageRow(5);
        }else {
            pageObject.setPageRow(Integer.parseInt(rows));
        }
        DetachedCriteria dc=DetachedCriteria.forClass(BuyHourse.class);
        Map m=(Map)ActionContext.getContext().getSession().get("tj");
        Number count=0;
        if(m!=null&&m.size()>0){
            count=baseDAO.findGetcount(dc,m);
        }else{
            count=baseDAO.getCount(dc);
        }
        pageObject.setTotalRows(count);
        DetachedCriteria dc2=DetachedCriteria.forClass(BuyHourse.class);
        buyHourseList=baseDAO.findPageByDetach2(dc2,pageObject,m);
        String json=JSON.toJSONString(buyHourseList);
        hsr.setContentType("text/html;charset=UTF-8");
        hsr.getWriter().write("{\"total\":"+count+",\"rows\":"+json+"}");
        System.out.println("{\"total\":"+count+",\"rows\":"+json+"}");

    }
    //条件查询
    public void findlist() throws IOException {
        String page=request.getParameter("page");
        String rows=request.getParameter("rows");//每页显示记录数
        if(page==null||page.equals("")){
            pageObject.setCur_page(1);
        }else{
            pageObject.setCur_page(Integer.parseInt(page));
        }
        if(rows==null||rows.equals("")){
            pageObject.setPageRow(5);
        }else {
            pageObject.setPageRow(Integer.parseInt(rows));
        }
        String oprType=request.getParameter("oprType");
        String invalid=request.getParameter("invalid");
        String username=request.getParameter("username");
        String custname=request.getParameter("custname");
        String hoursename=request.getParameter("hoursename");
        if(oprType!=null){
            if(!oprType.equals("----")){
                condition.put("oprType",oprType);
            }
        }
        if(invalid!=null){
            if(!invalid.equals("----")){
                condition.put("invalid",invalid);
            }
        }
        if(username!=null&&!username.equals("")){
            condition.put("userid",username);
        }
        if(custname!=null&&!custname.equals("")){
            condition.put("customerInfo.custname",custname);
        }
        if(hoursename!=null&&!hoursename.equals("")){
            condition.put("hourseInfo.hourseName",hoursename);
        }
        ActionContext.getContext().getSession().put("tj",condition);
        DetachedCriteria dc=DetachedCriteria.forClass(BuyHourse.class);
        int count=baseDAO.findGetcount(dc,condition);
        pageObject.setTotalRows(count);
        DetachedCriteria dc2=DetachedCriteria.forClass(BuyHourse.class);
        buyHourseList=baseDAO.findPageByDetach2(dc2,pageObject,condition);
        String json=JSON.toJSONString(buyHourseList);
        hsr.setContentType("text/html;charset=UTF-8");
        hsr.getWriter().write("{\"total\":"+count+",\"rows\":"+json+"}");
        System.out.println("{\"total\":"+count+",\"rows\":"+json+"}");

    }


}
