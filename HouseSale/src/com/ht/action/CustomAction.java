package com.ht.action;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ht.dao.IBaseDAO;
import com.ht.dao.PageObject;
import com.ht.dao.WebApplicationContextUtil;
import com.ht.vo.*;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.hibernate.FetchMode;
import org.hibernate.criterion.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

/**
 * Created by rainbow on 2018/10/19.
 */
public class CustomAction extends ActionSupport{
  private IBaseDAO base= (IBaseDAO) WebApplicationContextUtil.createService("dao");
  private HttpServletResponse response = ServletActionContext.getResponse();
  public List<CustomerInfo> cusList;
  public CustomerInfo custom;
  public List<ProjectInfo> prolist;
  private HttpSession session = null;
  private ProjectInfo pro;
  private Users  user;

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

    private EventType eventType;
  private List<EventType> eventTypeList;
    private String province;
    private String city;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    private String county;



    private  List<Users> usersList;


    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<Users> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Users> usersList) {
        this.usersList = usersList;
    }

    public ProjectInfo getPro() {
        return pro;
    }

    public void setPro(ProjectInfo pro) {
        this.pro = pro;
    }

    //客户类型
  public Custstate custstate;
  public List<Custstate> custstateList;

    public Custstate getCuststate() {
        return custstate;
    }

    public void setCuststate(Custstate custstate) {
        this.custstate = custstate;
    }

    public List<Custstate> getCuststateList() {
        return custstateList;
    }

    public void setCuststateList(List<Custstate> custstateList) {
        this.custstateList = custstateList;
    }

    public PageObject pager=new PageObject();


    public PageObject getPager() {
        return pager;
    }

    public void setPager(PageObject pager) {
        this.pager = pager;
    }


    public  String init(){
        //查询项目列表
        DetachedCriteria dc1=DetachedCriteria.forClass(ProjectInfo.class);
        prolist =base.findByDetach(dc1);
        //查询客户类型列表
        DetachedCriteria dc4=DetachedCriteria.forClass(Custstate.class);
        custstateList=base.findByDetach(dc4);
        DetachedCriteria dc5=DetachedCriteria.forClass(Users.class);
        usersList=base.findByDetach(dc5);
        DetachedCriteria dc6=DetachedCriteria.forClass(EventType.class);
        eventTypeList=base.findByDetach(dc6);
        return INPUT;

    }
    //客户列表页面 显示所有信息
  public String list(){
      //查询项目列表
      DetachedCriteria dc1=DetachedCriteria.forClass(ProjectInfo.class);
      prolist =base.findByDetach(dc1);
      //查询客户类型列表
      DetachedCriteria dc4=DetachedCriteria.forClass(Custstate.class);
      custstateList=base.findByDetach(dc4);

      //把查询条件保存到session里
      HttpSession session = ServletActionContext.getRequest().getSession();
      //点击了查询按钮进入action
      //第一次查询
      if(custom!=null){
          session.setAttribute("custom",custom);

      }
     /* if(session.getAttribute("custom")==null&&custom==null)){


      }*/
      //已经查询了  但想保留查询条件
      if(session.getAttribute("custom")!=null&&custom==null){
          custom= (CustomerInfo) session.getAttribute("custom");
      }
      //查询客户信息列表
      DetachedCriteria dc=DetachedCriteria.forClass(CustomerInfo.class);
      dc.createAlias("pro","p");

      dc.setFetchMode("p", FetchMode.JOIN);
      //设置条件查询
      if(custom!=null){
          if(custom.getProjectid()>0){
           dc.add(Restrictions.eq("p.projectId",custom.getProjectid()));

          }
          if(custom.getCustname()!=null&&!custom.getCustname().equals("")){
              dc.add(Restrictions.like("custname",custom.getCustname(), MatchMode.ANYWHERE));
          }
          if(custom.getCuststate()!=null&&!custom.getCuststate().equals("")){
              dc.add(Restrictions.eq("custstate",custom.getCuststate()));
          }

      }
      //设置统计
      dc.setProjection(Projections.rowCount());

      Number count =base.getCount(dc);
      pager.setTotalRows(count);
      //取消统计功能
      dc.setProjection(null);
      dc.addOrder(Order.asc("custId"));
      cusList =base.findPageByDetach(dc,pager);
      return SUCCESS;
  }
    public String prov()
            throws Exception
    {
        System.out.println("province=" + this.province);
        System.out.println("city=" + this.city);
        System.out.println("county=" + this.county);
        List<City> list = getProv();
        getdata(list);
        return null;
    }
    public List<City> getProv()
    {
        DetachedCriteria dc = DetachedCriteria.forClass(City.class);
        dc.add(Restrictions.like("cityid", "%0000"));
        List<City> list = this.base.findByDetached(dc);
        return list;
    }

    public List<City> getCity(String prov) throws Exception {
        DetachedCriteria dc = DetachedCriteria.forClass(City.class);
        dc.add(Restrictions.like("cityid", prov + "%00"));
        dc.add(Restrictions.ne("cityid", prov + "0000"));
        List<City> list = this.base.findByDetached(dc);
        return list;
    }

    public List<City> getCounty(String city)
    {
        DetachedCriteria dc = DetachedCriteria.forClass(City.class);
        dc.add(Restrictions.like("cityid", city + "%"));
        dc.add(Restrictions.ne("cityid", city + "00"));
        List<City> list = this.base.findByDetached(dc);
        return list;
    }


    public String getjson() throws  Exception{
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        custom= (CustomerInfo) base.findObjById(CustomerInfo.class,custom.getCustId());
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("custom",custom);
        JSONObject.DEFFAULT_DATE_FORMAT="yyyy-MM-dd";
        out.print(jsonObject.toJSONString());
//        String json=jsonObject.toJSONString();
        out.flush();
        out.close();
//        System.out.println(json);
       return null;
    }
      public String save(){


      if(custom.getCustId()==0){
          Date date=new Date();

          pro= (ProjectInfo) base.findObjById(ProjectInfo.class,custom.getProjectid());
          custom.setPro(pro);
          custom.setSigndate(date);
          base.save(custom);
      }else {
          Date date=new Date();
          pro= (ProjectInfo) base.findObjById(ProjectInfo.class,custom.getProjectid());
          custom.setPro(pro);
          custom.setSigndate(date);
          base.update(custom);
      }
          return "list";
      }
      public String update() throws Exception {
          //查询项目列表
          DetachedCriteria dc1=DetachedCriteria.forClass(ProjectInfo.class);
          prolist =base.findByDetach(dc1);
          //查询客户类型列表
          DetachedCriteria dc4=DetachedCriteria.forClass(Custstate.class);
          custstateList=base.findByDetach(dc4);
          DetachedCriteria dc5=DetachedCriteria.forClass(Users.class);
          usersList=base.findByDetach(dc5);
          DetachedCriteria dc6=DetachedCriteria.forClass(EventType.class);
          eventTypeList=base.findByDetach(dc6);
          custom= (CustomerInfo) base.findObjById(CustomerInfo.class,custom.getCustId());
          return  INPUT;
      }
      public String del(){
          base.delete(custom);
          return "list";

      }
    public String city()
            throws Exception
    {
        this.province = this.province.substring(0, this.province.length() - 4);
        System.out.println("pro=" + this.province);
        List<City> list = getCity(this.province);
        getdata(list);
        return null;
    }

    public String county()
            throws Exception
    {
        this.city = this.city.substring(0, this.city.length() - 2);
        System.out.println("pro=" + this.city);
        List<City> list = getCounty(this.city);
        getdata(list);

        return null;
    }
    private void getdata(List<City> list)
            throws Exception
    {
        String datas = "";
        datas = "{\"items\":[";
        for (int i = 0; i < list.size(); i++)
        {
            City c = (City)list.get(i);
            datas = datas + "{";
            datas = datas + "\"cityid\":\"" + c.getCityid() + "\",";
            datas = datas + "\"cityname\":\"" + c.getCityname() + "\"";
            datas = datas + "},";
        }
        datas = datas.substring(0, datas.length() - 1);
        datas = datas + "]";
        datas = datas + "}";
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        out.write(datas);
    }

    public List<CustomerInfo> getCusList() {
        return cusList;
    }

    public void setCusList(List<CustomerInfo> cusList) {
        this.cusList = cusList;
    }

    public CustomerInfo getCustom() {
        return custom;
    }

    public void setCustom(CustomerInfo custom) {
        this.custom = custom;
    }

    public List<ProjectInfo> getProlist() {
        return prolist;
    }

    public void setProlist(List<ProjectInfo> prolist) {
        this.prolist = prolist;
    }
}

