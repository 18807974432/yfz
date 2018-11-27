package com.ht.action;

import com.alibaba.fastjson.JSON;
import com.ht.dao.BaseDAO;
import com.ht.dao.PageObject;
import com.ht.dao.WebApplicationContextUtil;
import com.ht.vo.PaidType;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PaidTypeAction extends ActionSupport {
    private BaseDAO baseDAO=(BaseDAO) WebApplicationContextUtil.createService("dao");
    private PageObject pageObject=new PageObject();
    private List<PaidType> paidTypeList=new ArrayList<PaidType>();
    private PaidType paidType=new PaidType();


    HttpServletResponse hsr=ServletActionContext.getResponse();
    HttpServletRequest request=ServletActionContext.getRequest();

    public PaidType getPaidType() {
        return paidType;
    }
    public void setPaidType(PaidType paidType) {
        this.paidType = paidType;
    }
    public PageObject getPageObject() {
        return pageObject;
    }
    public void setPageObject(PageObject pageObject) {
        this.pageObject = pageObject;
    }
    public List<PaidType> getPaidTypeList() {
        return paidTypeList;
    }
    public void setPaidTypeList(List<PaidType> paidTypeList) {
        this.paidTypeList = paidTypeList;
    }

    //付款方式列表
    public void listPay() throws  Exception{
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
        DetachedCriteria dc=DetachedCriteria.forClass(PaidType.class);
        Number count=baseDAO.getCount2(dc);
        System.out.println("总记录数："+count);
        pageObject.setTotalRows(count);
        paidTypeList=baseDAO.findPageByDetach(dc,pageObject);
        String json=JSON.toJSONString(paidTypeList);
        hsr.setContentType("text/html;charset=UTF-8");
        hsr.getWriter().write("{\"total\":"+count+",\"rows\":"+json+"}");
        System.out.println("{\"total\":"+count+",\"rows\":"+json+"}");
    }
    //删除
    public void  delete() throws Exception {
//        HttpServletResponse hsr=ServletActionContext.getResponse();
//        HttpServletRequest request=ServletActionContext.getRequest();
        String paidtypeid=request.getParameter("paidtypeid");
        System.out.println("要删除的id：==================="+paidtypeid);
        PaidType s=(PaidType) baseDAO.findObjById(PaidType.class,Integer.parseInt(paidtypeid));
        baseDAO.delete(s);
    }

//    //新增
    public void add(){
        System.out.println("新增");
        String paidtypename=request.getParameter("paidtypename");
        String iscash=request.getParameter("iscash");
        String invalid=request.getParameter("invalid");
        System.out.println(paidtypename+iscash+invalid);
        paidType.setPaidtypename(paidtypename);
        paidType.setIscash(iscash);
        paidType.setInvalid(invalid);
        baseDAO.save(paidType);
    }

    public void save(){
        System.out.println("修改");
        int paidtypeid=Integer.parseInt(request.getParameter("paidtypeid"));
        String paidtypename=request.getParameter("paidtypename");
        String iscash=request.getParameter("iscash");
        String invalid=request.getParameter("invalid");
        System.out.println(paidtypeid+"=="+paidtypename+iscash+invalid);
        paidType.setPaidtypeid(paidtypeid);
        paidType.setPaidtypename(paidtypename);
        paidType.setIscash(iscash);
        paidType.setInvalid(invalid);
        baseDAO.saveOrUpdate(paidType);
    }
}
