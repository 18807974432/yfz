package com.ht.action;

import com.alibaba.fastjson.JSON;
import com.ht.dao.BaseDAO;
import com.ht.dao.PageObject;
import com.ht.dao.WebApplicationContextUtil;
import com.ht.vo.PaidSort;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PaidSortAction extends ActionSupport {
    private BaseDAO baseDAO=(BaseDAO) WebApplicationContextUtil.createService("dao");
    private PageObject pageObject=new PageObject();
    private List<PaidSort> PaidSortList=new ArrayList<PaidSort>();
    private PaidSort PaidSort=new PaidSort();

    HttpServletResponse hsr=ServletActionContext.getResponse();
    HttpServletRequest request=ServletActionContext.getRequest();

    public PageObject getPageObject() {
        return pageObject;
    }
    public void setPageObject(PageObject pageObject) {
        this.pageObject = pageObject;
    }
    public List<PaidSort> getPaidSortList() {
        return PaidSortList;
    }
    public void setPaidSortList(List<PaidSort> PaidSortList) {
        this.PaidSortList = PaidSortList;
    }
    public PaidSort getPaidSort() {
        return PaidSort;
    }
    public void setPaidSort(PaidSort PaidSort) {
        this.PaidSort = PaidSort;
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
        DetachedCriteria dc=DetachedCriteria.forClass(PaidSort.class);
        Number count=baseDAO.getCount(dc);
        System.out.println("总记录数："+count);
        pageObject.setTotalRows(count);
        PaidSortList=baseDAO.findPageByDetach(dc,pageObject);
        String json=JSON.toJSONString(PaidSortList);
        hsr.setContentType("text/html;charset=UTF-8");
        hsr.getWriter().write("{\"total\":"+count+",\"rows\":"+json+"}");
        System.out.println("{\"total\":"+count+",\"rows\":"+json+"}");
    }
    //删除
    public void  delete() throws Exception {
        String paidsortid=request.getParameter("paidsortid");
        System.out.println("要删除的id：==================="+paidsortid);
        PaidSort s=(PaidSort) baseDAO.findObjById(PaidSort.class,Integer.parseInt(paidsortid));
        baseDAO.delete(s);
    }

    //    //新增
    public void add(){
        System.out.println("新增");
        String paidsortname=request.getParameter("paidsortname");
        String paidtype=request.getParameter("paidtype");
        String invalid=request.getParameter("invalid");
        PaidSort.setPaidsortname(paidsortname);
        PaidSort.setPaidtype(paidtype);
        PaidSort.setInvalid(invalid);
        baseDAO.save(PaidSort);
    }

    public void save(){
        System.out.println("修改");
        int paidsortid=Integer.parseInt(request.getParameter("paidsortid"));
        String paidsortname=request.getParameter("paidsortname");
        String paidtype=request.getParameter("paidtype");
        String invalid=request.getParameter("invalid");
        PaidSort.setPaidsortid(paidsortid);
        PaidSort.setPaidsortname(paidsortname);
        PaidSort.setPaidtype(paidtype);
        PaidSort.setInvalid(invalid);
        baseDAO.saveOrUpdate(PaidSort);
    }

}
