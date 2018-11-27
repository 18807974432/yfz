package com.ht.action;

import com.alibaba.fastjson.JSONObject;
import com.ht.dao.IBaseDAO;
import com.ht.dao.PageObject;
import com.ht.dao.WebApplicationContextUtil;
import com.ht.vo.EventType;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by rainbow on 2018/10/25.
 */
public class EventtypeAction extends ActionSupport{
    private IBaseDAO base= (IBaseDAO) WebApplicationContextUtil.createService("dao");
    private EventType eventType;
    private List<EventType> eventTypeList;
    private PageObject pager=new PageObject();
    HttpServletResponse response= ServletActionContext.getResponse();


    public String list(){
        DetachedCriteria dc=DetachedCriteria.forClass(EventType.class);
      /*  custEventList=base.findPageByDetach(dc,)*/
        dc.setProjection(Projections.rowCount());
       Number count= base.getCount(dc);
        pager.setTotalRows(count);
        //取消统计功能
        dc.setProjection(null);
        dc.addOrder(Order.asc("eventtypeId"));
        eventTypeList=base.findPageByDetach(dc,pager);
        return SUCCESS;
    }
   public String getjson() throws IOException {
       response.setContentType("text/html;charset=utf-8");
       PrintWriter out = response.getWriter();
       eventType= (EventType) base.findObjById(EventType.class,eventType.getEventtypeId());
       JSONObject jsonObject=new JSONObject();
       jsonObject.put("eventlist",eventType);
       out.print(jsonObject.toJSONString());
//        String json=jsonObject.toJSONString();
       out.flush();
       out.close();
//        System.out.println(json);
        return null;
   }
   public String del(){
        eventType= (EventType) base.findObjById(EventType.class,eventType.getEventtypeId());
        base.delete(eventType);
       return "list";
   }
   public String save(){
       if(eventType.getEventtypeId()!=0) {


           eventType = (EventType) base.findObjById(EventType.class, eventType.getEventtypeId());
           base.saveOrUpdate(eventType);
       }else{
           base.save(eventType);
       }
       return "list";
   }




    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public List<EventType> getEventTypeList() {
        return eventTypeList;
    }

    public void setEventTypeList(List<EventType> eventTypeList) {
        this.eventTypeList = eventTypeList;
    }

    public PageObject getPager() {
        return pager;
    }

    public void setPager(PageObject pager) {
        this.pager = pager;
    }






}
