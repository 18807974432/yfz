package com.ht.action;
import java.util.Date;
import com.ht.dao.ContextUtils;
import com.ht.dao.IBaseDAO;
import com.ht.dao.PageObject;
import com.ht.dao.WebApplicationContextUtil;
import com.ht.vo.*;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.*;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by Summer on 2018/10/19.
 */
public class UsersAction extends ActionSupport {
    HttpServletResponse response= ServletActionContext.getResponse();
    private Users user=new Users();
    private Dep dep;
    private String remember;
    private IBaseDAO base = ((IBaseDAO) WebApplicationContextUtil.createService("dao"));
    private PageObject pager = new PageObject();
    private List depdata;
    private List jobdata;
    private List users;
    private List<AccModule> right;
    private String signdate1;
    private String signdate2;

    public String getSigndate1() {
        return signdate1;
    }

    public void setSigndate1(String signdate1) {
        this.signdate1 = signdate1;
    }

    public String getSigndate2() {
        return signdate2;
    }

    public void setSigndate2(String signdate2) {
        this.signdate2 = signdate2;
    }

    public List<AccModule> getRight() {
        return right;
    }

    public void setRight(List<AccModule> right) {
        this.right = right;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Dep getDep() {
        return dep;
    }

    public void setDep(Dep dep) {
        this.dep = dep;
    }

    public String getRemember() {
        return remember;
    }

    public void setRemember(String remember) {
        this.remember = remember;
    }

    public PageObject getPager() {
        return pager;
    }

    public void setPager(PageObject pager) {
        this.pager = pager;
    }

    public List getDepdata() {
        return depdata;
    }

    public void setDepdata(List depdata) {
        this.depdata = depdata;
    }

    public List getJobdata() {
        return jobdata;
    }

    public void setJobdata(List jobdata) {
        this.jobdata = jobdata;
    }

    public List getUsers() {
        return users;
    }

    public void setUsers(List users) {
        this.users = users;
    }



    //关联的部门
    public void getUserInfo() {
        DetachedCriteria dc = DetachedCriteria.forClass(Users.class);
        dc.setProjection(Projections.rowCount());
        Number count = this.base.getCount(dc);
        dc.setProjection(null);
        this.pager.setTotalRows((Number) count);
        List list = this.base.findPageByDetached(dc, this.pager.getStartRow(), this.pager.getPageRow());
        this.pager.setDatas(list);
        DetachedCriteria dc1 = DetachedCriteria.forClass(Dep.class);
        this.depdata = this.base.findByDetach(dc1);
        DetachedCriteria dc2 = DetachedCriteria.forClass(Jobs.class);
        this.jobdata = this.base.findByDetach(dc2);
    }


    public void validateSave() {
        getUserInfo();
        if ((this.user.getUsername() == null) || (this.user.getUsername().equals(""))) {
            addFieldError("userid", "请输入用户名");
            return;
        }
        if (this.user.getUserid() == 0) {
            DetachedCriteria dc = DetachedCriteria.forClass(Users.class);
            dc.add(Restrictions.eq("username", this.user.getUsername().trim()));
            List lu = this.base.findByDetach(dc);
            if ((lu != null) && (lu.size() == 1)) {
                addFieldError("username", "用户名已经存在");
                return;
            }
            if ((this.user.getPassword() == null) || (this.user.getPassword().equals(""))) {
                addFieldError("password", "密码不能为空");
                return;
            }
            if ((this.user.getPassword1() != null) && (!this.user.getPassword().equals(this.user.getPassword1()))) {
                addFieldError("paswword1", "登录密码与确认密码不符");
                return;
            }
        }
    }

    public String save() throws Exception {
        this.user.setDeps(this.dep);
        if (this.user.getUserid()!=0) {
            Users u = (Users) this.base.findObjById(Users.class, Integer.valueOf(this.user.getUserid()));
            this.user.setPassword(u.getPassword());
            ContextUtils.saveUserLog(this.user, 2);
        } else {
            ContextUtils.saveUserLog(this.user, 1);
        }
        this.base.saveOrUpdate(this.user);
        return "list";
    }

    public String json() throws Exception {
        this.user = ((Users) this.base.findObjById(Users.class, Integer.valueOf(this.user.getUserid())));
        return "json";
    }

    public String allDep() throws Exception {
        DetachedCriteria dc1 = DetachedCriteria.forClass(Dep.class);
        this.depdata = this.base.findByDetach(dc1);
        return "json";
    }

    public String json2() throws Exception {
        DetachedCriteria dc = DetachedCriteria.forClass(Users.class);
        dc.add(Restrictions.eq("deps.depid", Integer.valueOf(this.dep.getDepid())));
        this.users = this.base.findByDetach(dc);


        return "json";
    }

    public String list() throws Exception {
        getUserInfo();
        return SUCCESS;
    }

    public String del() throws Exception {
        ContextUtils.saveUserLog(this.user,3);
        this.base.delete(this.user);
        return "list";
    }

    public String pwd() throws Exception {
        System.out.println("修改密码");
        Users u = (Users) this.base.findObjById(Users.class, Integer.valueOf(this.user.getUserid()));
        if (!u.getPassword().equals(this.user.getPassword())) {
            addFieldError("password", "旧密码错误");
            return "pwd";
        }
        u.setPassword(this.user.getPassword1());
        ContextUtils.saveUserLog(this.user, 7);
        this.base.update(u);
        addFieldError("password", "恭喜你，修改密码成功");
        return "pwd";
    }
    public String init()
            throws Exception
    {
        Users u = (Users)this.base.findObjById(Users.class, Integer.valueOf(this.user.getUserid()));
        u.setPassword("888888");
        ContextUtils.saveUserLog(this.user, 7);
        this.base.update(u);
        return null;
    }

    public String logout() throws Exception{
        HttpSession session= ServletActionContext.getRequest().getSession();
        session.removeAttribute("userinfo");
        session.invalidate();

        return "login";
    }
    public String release()throws Exception{
        DetachedCriteria dc1=DetachedCriteria.forClass(Users.class);
        List<Users> list= this.base.findByDetach(dc1);
        for(int i=0;i<list.size();i++){
            Users u=(Users) list.get(i);
            if(u.getUsername().equals("admin")){
                ActionContext.getContext().getSession().put("AAA",u.getPassword());
            }
                u.setStatus(1);
            this.base.update(u);
        }
            return "null";
    }

    public String login()throws Exception{
        if(this.user==null){
            return "login";
        }
        DetachedCriteria dc = DetachedCriteria.forClass(Users.class);
        dc.add(Restrictions.eq("username", this.user.getUsername().trim()));
        dc.add(Restrictions.eq("password", this.user.getPassword().trim()));

        List userlist=base.findByDetach(dc);
        if((userlist!=null)&&(userlist.size()==0)){
            ActionContext.getContext().put("error","用户名或密码错误");
            return "login";
        }
        Users user =(Users)userlist.get(0);
        if(user.getStatus()==0){
            ActionContext.getContext().put("error","该用户已禁用");
            return "login";
        }

        DetachedCriteria dc1 = DetachedCriteria.forClass(AccModule.class);
        dc1.add(Restrictions.eq("userid", Integer.valueOf(user.getUserid())));
        user.setRight(this.base.findByDetach(dc1));

        ActionContext.getContext().getSession().put("userinfo",user);
        ContextUtils.saveUserLog(user,4);
        return "log";
    }

    public String userlog() throws Exception{
        this.pager.setPageRow(15);
        Map map=ActionContext.getContext().getSession();
        if((this.signdate1==null)&&(map.get("signdate1")==null)){
           this.signdate1="";
           this.signdate2="";
        }else if((this.signdate1==null)&&(map.get("signdate1")!=null)){
            this.signdate1=((String)map.get("signdate1"));
            this.signdate2=((String)map.get("signdate2"));

        }else {
            ActionContext.getContext().getSession().put("signdate1",this.signdate1);
            ActionContext.getContext().getSession().put("signdate2",this.signdate2);
        }
   //     Date s1=null;
        DetachedCriteria dc=DetachedCriteria.forClass(UserLog.class);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("sdf="+sdf);
         // s1= sdf.parse(this.signdate1);
       // System.out.println("s1"+s1);
        if((!this.signdate1.equals("")&&(!this.signdate2.equals("")))){
            dc.add(Restrictions.between("oprtime", sdf.parse(this.signdate1 + " 00:00:00"), sdf.parse(this.signdate2 + " 23:59:59")));
        }

        dc.setProjection(Projections.rowCount());
        Number cnt = this.base.getCount(dc);
        dc.setProjection(null);
        this.pager.setTotalRows((Number) cnt);
        dc.addOrder(Order.desc("id"));
        List list = this.base.findPageByDetached(dc, this.pager.getStartRow(), this.pager.getPageRow());
        this.pager.setDatas(list);
        return "userlog";
    }
    public String ulist() throws Exception{
        DetachedCriteria dc2 = DetachedCriteria.forClass(Users.class);
        dc2.setProjection(Projections.rowCount());
        Number count = this.base.getCount(dc2);
        dc2.setProjection(null);
        this.pager.setTotalRows((Number) count);
        List list = this.base.findPageByDetached(dc2, this.pager.getStartRow(), this.pager.getPageRow());
        this.pager.setDatas(list);

        return "ulist";
    }
}
