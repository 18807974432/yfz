package com.ht.action;

import com.alibaba.fastjson.JSONObject;
import com.ht.dao.*;
import com.ht.vo.CustomerInfo;
import com.ht.vo.Email;
import com.ht.vo.Send;
import com.ht.vo.Users;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.*;
import org.springframework.http.HttpRequest;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

/**
 * Created by rainbow on 2018/11/5.
 */
public class EmailAction extends ActionSupport {
    private IBaseDAO base= (IBaseDAO) WebApplicationContextUtil.createService("dao");
    private HttpServletResponse response = ServletActionContext.getResponse();
    private Email email;
    private List<Email> emailList;
    public Send send;
    private Users users;

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public List<Users> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Users> usersList) {
        this.usersList = usersList;
    }

    private List<Users> usersList;

    public Send getSend() {
        return send;
    }

    public void setSend(Send send) {
        this.send = send;
    }

    public List<Email> getEmailList1() {
        return emailList1;
    }

    public void setEmailList1(List<Email> emailList1) {
        this.emailList1 = emailList1;
    }

    private List<Email> emailList1;
    public PageObject pager=new PageObject();

    public PageObject getPager1() {
        return pager1;
    }

    public void setPager1(PageObject pager1) {
        this.pager1 = pager1;
    }

    public PageObject pager1=new PageObject();
    private HttpSession session;

    public PageObject getPager() {
        return pager;
    }

    public void setPager(PageObject pager) {
        this.pager = pager;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public List<Email> getEmailList() {
        return emailList;
    }

    public void setEmailList(List<Email> emailList) {
        this.emailList = emailList;
    }

    public  String list(){

       //获取登录用户
        String userid = ContextUtils.getUserInfo().getUsername();
        DetachedCriteria  dc=DetachedCriteria.forClass(Email.class);
        dc.add(Restrictions.eq("userid",userid));
        //设置统计
        dc.setProjection(Projections.rowCount());
        Number count=base.getCount(dc);
        pager.setTotalRows(count);
        //取消统计
        dc.setProjection(null);
        dc.addOrder(Order.desc("sendtime"));
        emailList=base.findPageByDetach(dc,pager);

        DetachedCriteria  dc1=DetachedCriteria.forClass(Email.class);
        dc1.add(Restrictions.eq("receid",userid));
        //设置统计
        dc1.setProjection(Projections.rowCount());
        Number count1=base.getCount(dc1);
        pager1.setTotalRows(count1);
        //取消统计
        dc1.setProjection(null);
        dc1.addOrder(Order.desc("sendtime"));
        emailList1=base.findPageByDetach(dc1,pager1);

        return SUCCESS;
    }
    public String add(){
        DetachedCriteria dc=DetachedCriteria.forClass(Users.class);
        usersList=base.findByDetach(dc);
        return INPUT;
    }
    public String save() throws MessagingException {
        send.setHead(email.getTopic());
        send.setText(email.getContent());
        //发送邮件
        Mail mail=new Mail();
        mail.setHead(send.getHead());
        mail.setQq(send.getQq());
        mail.setText(send.getText());
        mail.send();
        Date date=new Date();
        String userid = ContextUtils.getUserInfo().getUsername();
        email.setUserid(userid);
        email.setSendtime(date);
        email.setIsRead("未读");

        base.save(email);

        return "list";
    }

    public String del(){
        email= (Email) base.findObjById(Email.class,email.getEmailid());
        base.delete(email);
        return "list";
    }
    public String read(){
       email = (Email) base.findObjById(Email.class,email.getEmailid());
       email.setIsRead("已读");
       base.update(email);
       return "list";
    }
    public String getjson() throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        email= (Email) base.findObjById(Email.class,email.getEmailid());
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("email",email);
        out.print(jsonObject.toJSONString());
//        String json=jsonObject.toJSONString();
        out.flush();
        out.close();
//        System.out.println(json);
        return null;
    }

}
