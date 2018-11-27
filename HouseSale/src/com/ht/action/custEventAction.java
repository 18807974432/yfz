package com.ht.action;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ht.dao.IBaseDAO;
import com.ht.dao.PageObject;
import com.ht.dao.WebApplicationContextUtil;
import com.ht.vo.CustEvent;
import com.ht.vo.EventType;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by rainbow on 2018/10/24.
 */
public class custEventAction extends ActionSupport{
    private IBaseDAO base= (IBaseDAO) WebApplicationContextUtil.createService("dao");
    private CustEvent custEvent;
    private List<CustEvent> custEventList;
    private PageObject pager=new PageObject();

    public List<EventType> getEventTypeList() {
        return eventTypeList;
    }

    public void setEventTypeList(List<EventType> eventTypeList) {
        this.eventTypeList = eventTypeList;
    }

    HttpServletResponse response= ServletActionContext.getResponse();
    private List<EventType> eventTypeList;



    public String list(){
        DetachedCriteria dc=DetachedCriteria.forClass(CustEvent.class);
      /*  custEventList=base.findPageByDetach(dc,)*/
        dc.setProjection(Projections.rowCount());
         Number count=base.getCount(dc);
        pager.setTotalRows(count);
        //取消统计功能
        dc.setProjection(null);
        dc.addOrder(Order.asc("eventId"));
        custEventList=base.findPageByDetach(dc,pager);

        return SUCCESS;
    }
   public String add(){
    DetachedCriteria dc=DetachedCriteria.forClass(EventType.class);
    eventTypeList=base.findByDetach(dc);

    return SUCCESS;
   }
    public String getjson() throws Exception {

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        DetachedCriteria dc1=DetachedCriteria.forClass(CustEvent.class);
        dc1.add(Restrictions.eq("custid",custEvent.getCustid()));
        custEventList= base.findByDetach(dc1);


        int count = custEventList.size();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("total",count);
        jsonObject.put("rows",custEventList);
        JSONObject.DEFFAULT_DATE_FORMAT="yyyy-MM-dd";
        out.print(jsonObject.toJSONString(custEventList, SerializerFeature.WriteDateUseDateFormat));
        out.flush();
        out.close();
        return null;
    }
    public String  save() throws Exception {

       base.saveOrUpdate(custEvent);
       getjson();
       return null;
    }
     public String del() throws Exception {
       base.delete(custEvent);
       getjson();
       return null;
     }
     public String getdata(){
         DetachedCriteria dc=DetachedCriteria.forClass(EventType.class);
         eventTypeList=base.findByDetach(dc);
         custEvent= (CustEvent) base.findObjById(CustEvent.class,custEvent.getEventId());
         return SUCCESS;
     }


    public CustEvent getCustEvent() {
        return custEvent;
    }

    public void setCustEvent(CustEvent custEvent) {
        this.custEvent = custEvent;
    }

    public List<CustEvent> getCustEventList() {
        return custEventList;
    }

    public void setCustEventList(List<CustEvent> custEventList) {
        this.custEventList = custEventList;
    }

    public PageObject getPager() {
        return pager;
    }

    public void setPager(PageObject pager) {
        this.pager = pager;
    }



}
