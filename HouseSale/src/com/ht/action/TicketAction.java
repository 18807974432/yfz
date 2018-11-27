package com.ht.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ht.dao.BaseDAO;
import com.ht.dao.PageObject;
import com.ht.dao.WebApplicationContextUtil;
import com.ht.vo.BuyHourse;
import com.ht.vo.Dep;
import com.ht.vo.Ticket;
import com.ht.vo.Users;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TicketAction extends ActionSupport {
    private BaseDAO baseDAO=(BaseDAO) WebApplicationContextUtil.createService("dao");
    private PageObject pageObject=new PageObject();
    private Ticket ticket=new Ticket();
    private List<Ticket> ticketList=new ArrayList<>();
    private Dep dep=new Dep();
    private List<Dep> depList=new ArrayList<>();
    private List<Users> usersList=new ArrayList<>();
    private Map condition=new HashMap();


    public List<Users> getUsersList() {
        return usersList;
    }
    public void setUsersList(List<Users> usersList) {
        this.usersList = usersList;
    }
    public List<Dep> getDepList() {
        return depList;
    }
    public void setDepList(List<Dep> depList) {
        this.depList = depList;
    }
    public Dep getDep() {
        return dep;
    }
    public void setDep(Dep dep) {
        this.dep = dep;
    }

    HttpServletResponse hsr=ServletActionContext.getResponse();
    HttpServletRequest request=ServletActionContext.getRequest();

    public Ticket getTicket() {
        return ticket;
    }
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
    public List<Ticket> getTicketList() {
        return ticketList;
    }
    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }
    public PageObject getPageObject() {
        return pageObject;
    }
    public void setPageObject(PageObject pageObject) {
        this.pageObject = pageObject;
    }

    public void list() throws Exception {
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
        DetachedCriteria dc=DetachedCriteria.forClass(Ticket.class);
        Map m=(Map)ActionContext.getContext().getSession().get("tj");
        Number count=0;
        if(m!=null&&m.size()>0){
            count=baseDAO.findGetCount(dc,m);
        }else{
            count=baseDAO.getCount(dc);
        }
        pageObject.setTotalRows(count);
        ticketList=baseDAO.findByCondition(dc,m,pageObject);
        String json=JSON.toJSONString(ticketList);
        hsr.setContentType("text/html;charset=UTF-8");
        hsr.getWriter().write("{\"total\":"+count+",\"rows\":"+json+"}");
        System.out.println("{\"total\":"+count+",\"rows\":"+json+"}");

    }

    //付款方式列表
    public void listall() throws  Exception{
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
        DetachedCriteria dc=DetachedCriteria.forClass(Ticket.class);
        Number count=baseDAO.getCount(dc);
        System.out.println("总记录数："+count);
        pageObject.setTotalRows(count);
        ticketList=baseDAO.findPageByDetach(dc,pageObject);
        String json=JSON.toJSONString(ticketList);
        hsr.setContentType("text/html;charset=UTF-8");
        hsr.getWriter().write("{\"total\":"+count+",\"rows\":"+json+"}");
        System.out.println("{\"total\":"+count+",\"rows\":"+json+"}");
    }

    //新增
    public void add() throws ParseException {
        System.out.println("新增");
        String ticketNo=request.getParameter("ticketNo");
        String username=request.getParameter("username");
        String useTime =request.getParameter("useTime");
        String ticketType=request.getParameter("ticketType");
        String prefix=request.getParameter("prefix");
        String startNo=request.getParameter("startNo");
        String endNo=request.getParameter("endNo");
        String invalid=request.getParameter("invalid");
        String buyTime=request.getParameter("buyTime");
        String isAudit=request.getParameter("isAudit");
        String isEnd=request.getParameter("isEnd");
        String demo=request.getParameter("demo");
        ticket.setTicketNo(ticketNo);
        ticket.setUsePerson(username);
        SimpleDateFormat out = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        useTime=out.format(out.parse(useTime));
        buyTime=out.format(out.parse(buyTime));
        ticket.setUseTime(Timestamp.valueOf(useTime));
        ticket.setTicketType(ticketType);
        ticket.setPrefix(prefix);
        ticket.setStartNo(startNo);
        ticket.setEndNo(endNo);
        ticket.setInvalid(invalid);
        ticket.setBuyTime(Timestamp.valueOf(buyTime));
        ticket.setIsAudit(isAudit);
        ticket.setIsEnd(isEnd);
        if(demo==null){
            ticket.setDemo("");
        }
        ticket.setDemo(demo);
        ticket.setNextNo(0);
        baseDAO.save(ticket);
    }
    //删除
    public void delete(){
        String ticketid=request.getParameter("ticketid");
        ticket=(Ticket) baseDAO.findObjById(Ticket.class,Integer.parseInt(ticketid));
        baseDAO.delete(ticket);
    }
    //修改
    public void save() throws ParseException {
        System.out.println("修改");
        String ticketid=request.getParameter("ticketid");
        String ticketNo=request.getParameter("ticketNo");
        String username=request.getParameter("username");
        String useTime =request.getParameter("useTime");
        String ticketType=request.getParameter("ticketType");
        String prefix=request.getParameter("prefix");
        String startNo=request.getParameter("startNo");
        String endNo=request.getParameter("endNo");
        String invalid=request.getParameter("invalid");
        String buyTime=request.getParameter("buyTime");
        String isAudit=request.getParameter("isAudit");
        String isEnd=request.getParameter("isEnd");
        String demo=request.getParameter("demo");
        ticket=(Ticket) baseDAO.findObjById(Ticket.class,Integer.parseInt(ticketid));
        ticket.setTicketNo(ticketNo);
        ticket.setUsePerson(username);
        SimpleDateFormat out = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        useTime=out.format(out.parse(useTime));
        buyTime=out.format(out.parse(buyTime));
        ticket.setUseTime(Timestamp.valueOf(useTime));
        ticket.setTicketType(ticketType);
        ticket.setPrefix(prefix);
        ticket.setStartNo(startNo);
        ticket.setEndNo(endNo);
        ticket.setInvalid(invalid);
        ticket.setBuyTime(Timestamp.valueOf(buyTime));
        ticket.setIsAudit(isAudit);
        ticket.setIsEnd(isEnd);
        if(demo==null){
            ticket.setDemo("");
        }
        ticket.setDemo(demo);
        ticket.setNextNo(0);
        baseDAO.saveOrUpdate(ticket);
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

        String find_ticketNo=request.getParameter("find_ticketNo");
        String find_usePerson=request.getParameter("find_usePerson");
        String find_invalid=request.getParameter("find_invalid");
        String find_isAudit=request.getParameter("find_isAudit");
        String find_isEnd=request.getParameter("find_isEnd");
        if(find_ticketNo!=null){
            condition.put("ticketNo",find_ticketNo);
        }
        if(find_usePerson!=null){
            condition.put("usePerson",find_usePerson);
        }
        if(find_invalid!=null){
            if(!find_invalid.equals("----")){
                condition.put("invalid",find_invalid);
            }
        }
        if(find_isAudit!=null){
            if(!find_isAudit.equals("----")){
                condition.put("isAudit",find_isAudit);
            }
        }
        if(find_isEnd!=null){
            if(!find_isEnd.equals("----")){
                condition.put("isEnd",find_isEnd);
            }
        }
        ActionContext.getContext().getSession().put("tj",condition);
        DetachedCriteria dc=DetachedCriteria.forClass(Ticket.class);
        int count=baseDAO.findGetCount(dc,condition);
        pageObject.setTotalRows(count);
        ticketList=baseDAO.findByCondition(dc,condition,pageObject);
        String json=JSON.toJSONString(ticketList);
        hsr.setContentType("text/html;charset=UTF-8");
        hsr.getWriter().write("{\"total\":"+count+",\"rows\":"+json+"}");
        System.out.println("{\"total\":"+count+",\"rows\":"+json+"}");
    }


    //显示操作人员列表
    public void listDep() throws IOException {
        DetachedCriteria dc=DetachedCriteria.forClass(Dep.class);
        Number count=baseDAO.getCount(dc);
        depList=baseDAO.findByDetach(dc);
        JSONArray jsonArray=new JSONArray();//最后总数据
        for(Dep d:depList){
            if(d.getParentid()==d.getDepid()){
                JSONObject j=new JSONObject();
                j.put("id",d.getDepid());
                j.put("name",d.getDepname());
                j.put("parendId",0);
                jsonArray.add(j);
            }else{
                JSONObject j=new JSONObject();
                j.put("id",d.getDepid());
                j.put("name",d.getDepname());
                j.put("parentId",d.getParentid());
                jsonArray.add(j);
            }

        }
        String json=jsonArray.toJSONString();
        System.out.println(json);
        hsr.setContentType("text/html;charset=UTF-8");
        hsr.getWriter().write(json);
    }

    //查找该部门ID的人
    public void findUsers() throws IOException {
        System.out.println("查找===========");
        String depid=request.getParameter("depid");
        if(depid==null){
            return;
        }
        System.out.println("id:"+depid);
        DetachedCriteria dc=DetachedCriteria.forClass(Users.class);
        dc.add(Restrictions.eq("depid",Integer.parseInt(depid)));
        usersList=baseDAO.findByDetach(dc);
        if(usersList==null){
            return;
        }
        String json=JSON.toJSONString(usersList);
        hsr.setContentType("text/html;charset=UTF-8");
        hsr.getWriter().print(json);

    }

}
