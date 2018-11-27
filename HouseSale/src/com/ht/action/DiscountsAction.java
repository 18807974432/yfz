package com.ht.action;

import com.alibaba.fastjson.JSONObject;
import com.ht.dao.IBaseDAO;
import com.ht.dao.PageObject;
import com.ht.dao.WebApplicationContextUtil;
import com.ht.vo.Discount;
import com.ht.vo.Users;
import com.opensymphony.xwork2.ActionSupport;

import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DiscountsAction extends ActionSupport {
    HttpServletResponse response= ServletActionContext.getResponse();
    private PageObject pager = new PageObject();

    public PageObject getPager() {
        return pager;
    }

    public void setPager(PageObject pager) {
        this.pager = pager;
    }
    HttpServletRequest request=ServletActionContext.getRequest();

    private IBaseDAO base = (IBaseDAO) WebApplicationContextUtil.createService("dao");
    private Discount discount=new Discount();
    private List<Discount> list;

    public List<Discount> getList() {
        return list;
    }

    public void setList(List<Discount> list) {
        this.list = list;
    }

    public Discount getDiscount() {
        return discount;
    }
    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public String list() throws Exception {
        getData();
        return SUCCESS;
    }
    public void getData(){

        DetachedCriteria dc = DetachedCriteria.forClass(Discount.class);
        if ((discount.getInvalid() != null) && (!discount.getInvalid().equals(""))) {
            dc.add(Restrictions.eq("invalid", discount.getInvalid()));
        }
        dc.setProjection(Projections.rowCount());
        Number cnt= (int) base.getCount(dc);
        System.out.println(cnt);
        this.pager.setTotalRows(cnt);
        dc.setProjection(null);
        dc.addOrder(Order.desc("discountId"));
        list = this.base.findPageByDetach(dc, pager);
    }

    public void json() throws Exception {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        this.discount = ((Discount) this.base.findObjByID(Discount.class,
                Integer.valueOf(this.discount.getDiscountId())));
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("discount",discount);
        out.print(jsonObject.toJSONString());
        out.flush();
        out.close();
    }

    public String save() throws Exception{
        if (this.discount.getDiscountId() == 0) {
            SimpleDateFormat format =  new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss ");
            Date s = new Date();
            this.discount.setCreateTime(Timestamp.valueOf(format.format(s)));
            Users user= (Users) request.getAttribute("userinfo");

            this.discount.setUsername(user.getUsername());
        } else {
            Discount dis = (Discount) this.base.findObjByID(Discount.class, Integer.valueOf(this.discount.getDiscountId()));
            this.discount.setCreateTime(dis.getCreateTime());
            this.discount.setUsername(dis.getUsername());
        }
        this.base.saveOrUpdate(this.discount);
        return "list";
    }

    public String del() throws Exception {
        this.base.delete(this.discount);
        return list();
    }
}
