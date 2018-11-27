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

public class HourseInfoAction extends ActionSupport {
    private BaseDAO baseDAO=(BaseDAO) WebApplicationContextUtil.createService("dao");
    private PageObject pageObject=new PageObject();
    private HourseInfo hourseInfo;
    private List<HourseInfo> hourseInfoList=new ArrayList<>();
    private List<TermInfo> termInfoList=new ArrayList<>();
    private List<ProjectInfo> projectInfoList=new ArrayList<>();
    private List<Housemodel> housemodelList=new ArrayList<>();
    private List<SaleState> saleStateList=new ArrayList<>();

    private Map condition=new HashMap();

    public List<SaleState> getSaleStateList() {
        return saleStateList;
    }
    public void setSaleStateList(List<SaleState> saleStateList) {
        this.saleStateList = saleStateList;
    }
    public List<Housemodel> getHousemodelList() {
        return housemodelList;
    }
    public void setHousemodelList(List<Housemodel> housemodelList) {
        this.housemodelList = housemodelList;
    }
    public List<ProjectInfo> getProjectInfoList() {
        return projectInfoList;
    }
    public void setProjectInfoList(List<ProjectInfo> projectInfoList) {
        this.projectInfoList = projectInfoList;
    }
    public List<TermInfo> getTermInfoList() {
        return termInfoList;
    }
    public void setTermInfoList(List<TermInfo> termInfoList) {
        this.termInfoList = termInfoList;
    }

    public List<HourseInfo> getHourseInfoList() {
        return hourseInfoList;
    }
    public void setHourseInfoList(List<HourseInfo> hourseInfoList) {
        this.hourseInfoList = hourseInfoList;
    }
    public HourseInfo getHourseInfo() {
        return hourseInfo;
    }
    public void setHourseInfo(HourseInfo hourseInfo) {
        this.hourseInfo = hourseInfo;
    }
    public BaseDAO getBaseDAO() {
        return baseDAO;
    }
    public void setBaseDAO(BaseDAO baseDAO) {
        this.baseDAO = baseDAO;
    }
    public PageObject getPageObject() {
        return pageObject;
    }
    public void setPageObject(PageObject pageObject) {
        this.pageObject = pageObject;
    }

    HttpServletResponse hsr=ServletActionContext.getResponse();
    HttpServletRequest request=ServletActionContext.getRequest();


    public String  list() throws Exception {
        System.out.println("ok");
        DetachedCriteria dc=DetachedCriteria.forClass(TermInfo.class);
        termInfoList=baseDAO.findByDetach(dc);
        dc=DetachedCriteria.forClass(ProjectInfo.class);
        projectInfoList=baseDAO.findByDetach(dc);
        dc=DetachedCriteria.forClass(Housemodel.class);
        housemodelList=baseDAO.findByDetach(dc);
        dc=DetachedCriteria.forClass(SaleState.class);
        saleStateList=baseDAO.findByDetach(dc);
        return "list";
    }

    public void listjson() throws IOException {
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
        DetachedCriteria dc=DetachedCriteria.forClass(HourseInfo.class);
        Map m=(Map)ActionContext.getContext().getSession().get("tj");
        int count=0;
        if(m!=null&&m.size()>0){
            count=baseDAO.findGetCount(dc,m);
        }else{
            count=(int)baseDAO.getCount(dc);
        }
        pageObject.setTotalRows(count);
        hourseInfoList=baseDAO.findPageByDetach(dc,pageObject);
        String json=JSON.toJSONString(hourseInfoList);
        hsr.setContentType("text/html;charset=UTF-8");
        hsr.getWriter().write("{\"total\":"+count+",\"rows\":"+json+"}");
        System.out.println("{\"total\":"+count+",\"rows\":"+json+"}");
    }
    public String add(){
        baseDAO.save(hourseInfo);
        return "listinit";
    }
    public String save(){
        baseDAO.saveOrUpdate(hourseInfo);
        return "listinit";
    }
    public void delete(){
        String hourseId=request.getParameter("hourseId");
        hourseInfo=(HourseInfo) baseDAO.findObjById(HourseInfo.class,Integer.parseInt(hourseId));
        baseDAO.delete(hourseInfo);
    }

    //条件查询
    public void findbyCondition() throws IOException {
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

        String find_termName=request.getParameter("find_termName");
        String find_saleState=request.getParameter("find_saleState");
        String find_termType=request.getParameter("find_termType");
        String find_unit=request.getParameter("find_unit");
        String find_floor=request.getParameter("find_floor");
        if(find_termName!=null&&!find_termName.equals("")){
            condition.put("hourseName",find_termName);
        }
        if(find_saleState!=null&&!find_saleState.equals("")){
            condition.put("saleState",find_saleState);
        }
        if(find_termType!=null){
            condition.put("termType",find_termType);
        }
        if(find_unit!=null&&!find_unit.equals("")){
            condition.put("unit",find_unit);
        }
        if(find_floor!=null&&!find_floor.equals("")){
            condition.put("floor",find_floor);
        }
        ActionContext.getContext().getSession().put("tj",condition);
        DetachedCriteria dc=DetachedCriteria.forClass(HourseInfo.class);
        int count=baseDAO.findGetCount(dc,condition);
        pageObject.setTotalRows(count);
        hourseInfoList=baseDAO.findByConditionfz(dc,condition,pageObject);
        String json=JSON.toJSONString(hourseInfoList);
        hsr.setContentType("text/html;charset=UTF-8");
        hsr.getWriter().write("{\"total\":"+count+",\"rows\":"+json+"}");
        System.out.println("{\"total\":"+count+",\"rows\":"+json+"}");
    }

}
