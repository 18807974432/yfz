package com.ht.action;

import com.alibaba.fastjson.JSON;
import com.ht.dao.BaseDAO;
import com.ht.dao.PageObject;
import com.ht.dao.WebApplicationContextUtil;
import com.ht.vo.BuyHourse;
import com.ht.vo.EarnestMoney;
import com.ht.vo.PaidType;
import com.ht.vo.Ticket;
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

public class EarnestMoneyAction extends ActionSupport {
    private BaseDAO baseDAO=(BaseDAO) WebApplicationContextUtil.createService("dao");
    private PageObject pageObject=new PageObject();
    private List<Ticket> ticketList=new ArrayList<>();
    private List<PaidType> paidTypeList=new ArrayList<>();
    private EarnestMoney earnestMoney=new EarnestMoney();


    public EarnestMoney getEarnestMoney() {
        return earnestMoney;
    }

    public void setEarnestMoney(EarnestMoney earnestMoney) {
        this.earnestMoney = earnestMoney;
    }

    public List<PaidType> getPaidTypeList() {
        return paidTypeList;
    }

    public void setPaidTypeList(List<PaidType> paidTypeList) {
        this.paidTypeList = paidTypeList;
    }

    private String custname;
    private String housename;
    private String userid;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCustname() {
        return custname;
    }

    public void setCustname(String custname) {
        this.custname = custname;
    }

    public String getHousename() {
        return housename;
    }

    public void setHousename(String housename) {
        this.housename = housename;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }
    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    HttpServletResponse hsr=ServletActionContext.getResponse();
    HttpServletRequest request=ServletActionContext.getRequest();
    //诚意金明细
    public void list() throws IOException {
        String buyId=request.getParameter("buyId");
        if(buyId==null){
            return;
        }else{
            pageObject.setCur_page(1);
            pageObject.setPageRow(100);
            DetachedCriteria dc=DetachedCriteria.forClass(EarnestMoney.class);
            dc.add(Restrictions.eq("buyHourse.buyId",Integer.parseInt(buyId)));
            int count=baseDAO.EarnestCount(dc);
            pageObject.setTotalRows(count);
            DetachedCriteria dc2=DetachedCriteria.forClass(EarnestMoney.class);
            dc2.add(Restrictions.eq("buyHourse.buyId",Integer.parseInt(buyId)));
            List list=baseDAO.findPageByDetach3(dc2,pageObject);
            String json=JSON.toJSONString(list);
            hsr.setContentType("text/html;charset=UTF-8");
            hsr.getWriter().write("{\"total\":"+count+",\"rows\":"+json+"}");
            System.out.println("{\"total\":"+count+",\"rows\":"+json+"}");
        }
    }
    public String  set(){
        int buyId=Integer.parseInt(request.getParameter("buyId"));
        custname=request.getParameter("custname");
        housename=request.getParameter("hoursename");
        userid=request.getParameter("userid");
        ActionContext.getContext().getSession().put("userid",userid);
        ActionContext.getContext().getSession().put("buyId",buyId);
        DetachedCriteria dc1=DetachedCriteria.forClass(EarnestMoney.class);
        dc1.add(Restrictions.eq("buyHourse.buyId",buyId));
        baseDAO.findByDetach(dc1);

        DetachedCriteria dc=DetachedCriteria.forClass(Ticket.class);
        ticketList=baseDAO.findByDetach(dc);
        dc=DetachedCriteria.forClass(PaidType.class);
        paidTypeList=baseDAO.findByDetach(dc);
        return "list";
    }
    public void list2() throws IOException {
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
        int buyId=(int)ActionContext.getContext().getSession().get("buyId");
        DetachedCriteria dc=DetachedCriteria.forClass(EarnestMoney.class);
        dc.add(Restrictions.eq("buyHourse.buyId",buyId));
        int count=baseDAO.EarnestCount(dc);
        pageObject.setTotalRows(count);
        DetachedCriteria dc2=DetachedCriteria.forClass(EarnestMoney.class);
        dc2.add(Restrictions.eq("buyHourse.buyId",buyId));
        List list=baseDAO.findPageByDetach3(dc2,pageObject);
        String json=JSON.toJSONString(list);
        hsr.setContentType("text/html;charset=UTF-8");
        hsr.getWriter().write("{\"total\":"+count+",\"rows\":"+json+"}");
        System.out.println("{\"total\":"+count+",\"rows\":"+json+"}");
    }


    //转签约
    public void turn(){
        String earnestId=request.getParameter("earnestId");
        DetachedCriteria dc=DetachedCriteria.forClass(EarnestMoney.class);
        dc.add(Restrictions.eq("earnestId",earnestId));
        EarnestMoney earnestMoney=(EarnestMoney) baseDAO.findObjById(EarnestMoney.class,Integer.parseInt(earnestId));
        earnestMoney.setTransMoney(earnestMoney.getPaidMoney());
        earnestMoney.setStatus("转签约");
        earnestMoney.setIsTranslate("是");
        earnestMoney.setOprtime(new Timestamp((new Date()).getTime()));
        baseDAO.saveOrUpdate(earnestMoney);
    }
    //作废
    public void del(){
        String earnestId=request.getParameter("earnestId");
        DetachedCriteria dc=DetachedCriteria.forClass(EarnestMoney.class);
        dc.add(Restrictions.eq("earnestId",earnestId));
        EarnestMoney earnestMoney=(EarnestMoney) baseDAO.findObjById(EarnestMoney.class,Integer.parseInt(earnestId));
        earnestMoney.setTransMoney(earnestMoney.getPaidMoney());
        earnestMoney.setInvalid("作废");
        earnestMoney.setOprtime(new Timestamp((new Date()).getTime()));
        baseDAO.saveOrUpdate(earnestMoney);
    }

    //保存
    public void add() throws ParseException {
        int buyId=(int)ActionContext.getContext().getSession().get("buyId");
        BuyHourse buyHourse=new BuyHourse();
        buyHourse.setBuyId(buyId);
        earnestMoney.setBuyHourse(buyHourse);
        String ticket=request.getParameter("ticket");
        Ticket ticket1=new Ticket();
        ticket1.setTicketid(Integer.parseInt(ticket));
        earnestMoney.setTicket(ticket1);
        String ticketNo=request.getParameter("ticketNo");
        earnestMoney.setTicketFlow(ticketNo);
        String paidType=request.getParameter("paidType");
        earnestMoney.setPaidtypeid(paidType);
        String paidMoney=request.getParameter("paidMoney");
        earnestMoney.setPaidMoney(Double.parseDouble(paidMoney));
        String paidTime=request.getParameter("paidTime");
        SimpleDateFormat out = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        paidTime=out.format(out.parse(paidTime));
        earnestMoney.setPaidTime(Timestamp.valueOf(paidTime));
        earnestMoney.setIsTranslate("否");
        earnestMoney.setTransMoney(0.00);
        earnestMoney.setStatus("收取");
        earnestMoney.setUserid(ActionContext.getContext().getSession().get("userid").toString());
        earnestMoney.setOprtime(Timestamp.valueOf(paidTime));
        earnestMoney.setInvalid("有效");
        baseDAO.save(earnestMoney);
    }

}
