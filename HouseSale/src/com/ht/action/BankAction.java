package com.ht.action;

import com.alibaba.fastjson.JSON;
import com.ht.dao.BaseDAO;
import com.ht.dao.PageObject;
import com.ht.dao.WebApplicationContextUtil;
import com.ht.vo.Bank;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class BankAction extends ActionSupport {
    private BaseDAO baseDAO=(BaseDAO) WebApplicationContextUtil.createService("dao");
    private PageObject pageObject=new PageObject();
    private List<Bank> BankList=new ArrayList<Bank>();
    private Bank Bank=new Bank();


    HttpServletResponse hsr=ServletActionContext.getResponse();
    HttpServletRequest request=ServletActionContext.getRequest();

    public Bank getBank() {
        return Bank;
    }
    public void setBank(Bank Bank) {
        this.Bank = Bank;
    }
    public PageObject getPageObject() {
        return pageObject;
    }
    public void setPageObject(PageObject pageObject) {
        this.pageObject = pageObject;
    }
    public List<Bank> getBankList() {
        return BankList;
    }
    public void setBankList(List<Bank> BankList) {
        this.BankList = BankList;
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
        DetachedCriteria dc=DetachedCriteria.forClass(Bank.class);
        Number count=baseDAO.getCount(dc);
        System.out.println("总记录数："+count);
        pageObject.setTotalRows(count);
        BankList=baseDAO.findPageByDetach(dc,pageObject);
        String json=JSON.toJSONString(BankList);
        hsr.setContentType("text/html;charset=UTF-8");
        hsr.getWriter().write("{\"total\":"+count+",\"rows\":"+json+"}");
        System.out.println("{\"total\":"+count+",\"rows\":"+json+"}");
    }
    //删除
    public void  delete() throws Exception {
        String bankid=request.getParameter("bankid");
        System.out.println("要删除的id：==================="+bankid);
        Bank s=(Bank) baseDAO.findObjById(Bank.class,Integer.parseInt(bankid));
        baseDAO.delete(s);
    }

//    //新增
    public void add(){
        System.out.println("新增");
        String bankname=request.getParameter("bankname");
        Bank.setBankname(bankname);
        baseDAO.save(Bank);
    }

    public void save(){
        System.out.println("修改");
        int bankid=Integer.parseInt(request.getParameter("bankid"));
        String bankname=request.getParameter("bankname");
        Bank.setBankid(bankid);
        Bank.setBankname(bankname);
        baseDAO.saveOrUpdate(Bank);
    }
}
