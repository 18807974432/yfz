package com.ht.action;

import com.alibaba.fastjson.JSON;
import com.ht.dao.BaseDAO;
import com.ht.dao.PageObject;
import com.ht.dao.WebApplicationContextUtil;
import com.ht.vo.PaidScheme;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PaidSchemeAction extends ActionSupport {
    private BaseDAO baseDAO=(BaseDAO) WebApplicationContextUtil.createService("dao");
    private PageObject pageObject=new PageObject();
    private List<PaidScheme> paidSchemeList=new ArrayList<PaidScheme>();
    private PaidScheme paidScheme=new PaidScheme();

    HttpServletResponse hsr=ServletActionContext.getResponse();
    HttpServletRequest request=ServletActionContext.getRequest();

    public PageObject getPageObject() {
        return pageObject;
    }
    public void setPageObject(PageObject pageObject) {
        this.pageObject = pageObject;
    }
    public List<PaidScheme> getPaidSchemeList() {
        return paidSchemeList;
    }
    public void setPaidSchemeList(List<PaidScheme> paidSchemeList) {
        this.paidSchemeList = paidSchemeList;
    }
    public PaidScheme getPaidScheme() {
        return paidScheme;
    }
    public void setPaidScheme(PaidScheme paidScheme) {
        this.paidScheme = paidScheme;
    }

    //收款方式列表
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
        DetachedCriteria dc=DetachedCriteria.forClass(PaidScheme.class);
        Number count=baseDAO.getCount2(dc);
        System.out.println("总记录数："+count);
        pageObject.setTotalRows(count);
        paidSchemeList=baseDAO.findPageByDetach(dc,pageObject);
        String json=JSON.toJSONString(paidSchemeList);
        hsr.setContentType("text/html;charset=UTF-8");
        hsr.getWriter().write("{\"total\":"+count+",\"rows\":"+json+"}");
        System.out.println("{\"total\":"+count+",\"rows\":"+json+"}");
    }
    //删除
    public void  delete() throws Exception {
        String psid=request.getParameter("psid");
        System.out.println("要删除的id：==================="+psid);
        PaidScheme s=(PaidScheme) baseDAO.findObjById(PaidScheme.class,Integer.parseInt(psid));
        baseDAO.delete(s);
    }

    //    //新增
    public void add(){
        System.out.println("新增");
        String psname=request.getParameter("psname");
        String projectid=request.getParameter("projectid");
        String isBank=request.getParameter("isBank");
        System.out.println(psname+projectid+isBank);
        paidScheme.setPsname(psname);
        paidScheme.setProjectid(Integer.parseInt(projectid));
        paidScheme.setIsBank(isBank);
        paidScheme.setPstime(new Timestamp(new Date().getTime()));
        paidScheme.setUserid("admin");
        baseDAO.save(paidScheme);
    }

    public void save(){
        System.out.println("修改");
        int psid=Integer.parseInt(request.getParameter("psid"));
        PaidScheme p=(PaidScheme) baseDAO.findObjById(PaidScheme.class,psid);
        String psname=request.getParameter("psname");
        String projectid=request.getParameter("projectid");
        String isBank=request.getParameter("isBank");
        p.setPsname(psname);
        p.setProjectid(Integer.parseInt(projectid));
        p.setIsBank(isBank);
        baseDAO.saveOrUpdate(p);
    }

}
