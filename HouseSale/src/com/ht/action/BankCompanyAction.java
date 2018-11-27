package com.ht.action;

import com.alibaba.fastjson.JSON;
import com.ht.dao.BaseDAO;
import com.ht.dao.PageObject;
import com.ht.dao.WebApplicationContextUtil;
import com.ht.vo.Bank;
import com.ht.vo.BankCompany;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BankCompanyAction extends ActionSupport {
    private BaseDAO baseDAO=(BaseDAO) WebApplicationContextUtil.createService("dao");
    private PageObject pageObject=new PageObject();
    private Bank bank=new Bank();
    private List<Bank> bankList=new ArrayList<Bank>();
    private List<BankCompany> BankCompanyList=new ArrayList<BankCompany>();
    private BankCompany BankCompany=new BankCompany();

    HttpServletResponse hsr=ServletActionContext.getResponse();
    HttpServletRequest request=ServletActionContext.getRequest();

    public Bank getBank() {
        return bank;
    }
    public void setBank(Bank bank) {
        this.bank = bank;
    }
    public List<Bank> getBankList() {
        return bankList;
    }
    public void setBankList(List<Bank> bankList) {
        this.bankList = bankList;
    }
    public PageObject getPageObject() {
        return pageObject;
    }
    public void setPageObject(PageObject pageObject) {
        this.pageObject = pageObject;
    }
    public List<BankCompany> getBankCompanyList() {
        return BankCompanyList;
    }
    public void setBankCompanyList(List<BankCompany> BankCompanyList) {
        this.BankCompanyList = BankCompanyList;
    }
    public BankCompany getBankCompany() {
        return BankCompany;
    }
    public void setBankCompany(BankCompany BankCompany) {
        this.BankCompany = BankCompany;
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
        DetachedCriteria dc=DetachedCriteria.forClass(BankCompany.class);
        Number count=baseDAO.getCount(dc);
        System.out.println("总记录数："+count);
        pageObject.setTotalRows(count);
        BankCompanyList=baseDAO.findPageByDetach(dc,pageObject);
        String json=JSON.toJSONString(BankCompanyList);
        hsr.setContentType("text/html;charset=UTF-8");
        hsr.getWriter().write("{\"total\":"+count+",\"rows\":"+json+"}");
        System.out.println("{\"total\":"+count+",\"rows\":"+json+"}");
    }
    //删除
    public void  delete() throws Exception {
        String bankAccount=request.getParameter("bankAccount");
        System.out.println("要删除的id：==================="+bankAccount);
        DetachedCriteria dc=DetachedCriteria.forClass(BankCompany.class);
        dc.add(Restrictions.eq("bankAccount",bankAccount));
        BankCompany b=(BankCompany) baseDAO.getObject(dc);
        baseDAO.delete(b);
    }

    //新增初始数据
    public  String addinit(){
        DetachedCriteria dc=DetachedCriteria.forClass(Bank.class);
        bankList=baseDAO.findByDetach(dc);
        return "list";
    }

    //    //新增
    public void add(){
        System.out.println("新增");
        String bankAccount=request.getParameter("bankAccount");
        String bankname=request.getParameter("bankname");
        BankCompany.setBankAccount(bankAccount);
        BankCompany.setBankname(bankname);
        baseDAO.save(BankCompany);
    }
    //查找有没有这个账户
    public void invadadd() throws IOException {
        String bankAccount=request.getParameter("bankAccount");
        DetachedCriteria dc=DetachedCriteria.forClass(BankCompany.class);
        dc.add(Restrictions.eq("bankAccount",bankAccount));
        BankCompany b=(BankCompany) baseDAO.getObject(dc);
        if(b!=null){
            hsr.setContentType("text/html;charset=UTF-8");
            hsr.getWriter().write("1");
        }else {
            hsr.setContentType("text/html;charset=UTF-8");
            hsr.getWriter().write("0");
        }
    }

    public void save(){
        System.out.println("修改");
        String bankAccount=request.getParameter("bankAccount");
        String bankname=request.getParameter("bankname");

        System.out.println(bankAccount+"=========="+bankname);
        BankCompany.setBankAccount(bankAccount);
        BankCompany.setBankname(bankname);
        baseDAO.saveOrUpdate(BankCompany);
    }

}
