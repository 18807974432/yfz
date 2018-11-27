package com.ht.action;

import com.alibaba.fastjson.JSON;
import com.ht.dao.BaseDAO;
import com.ht.dao.IBaseDAO;
import com.ht.dao.PageObject;
import com.ht.dao.WebApplicationContextUtil;
import com.ht.vo.Housemodel;
import com.ht.vo.ProjectInfo;
import com.ht.vo.TermInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.ehcache.transaction.DeadLockException;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;

public class ProjectInfoAction extends ActionSupport {
    private BaseDAO base= (BaseDAO)WebApplicationContextUtil.createService("dao");
    private ProjectInfo project=new ProjectInfo();
    private List<ProjectInfo> projectList=new ArrayList<ProjectInfo>() ;
    private int page;
    private int rows;
    private TermInfo term;
    private List<TermInfo> termList;
    private Map condition=new HashMap();
    HttpServletRequest request= ServletActionContext.getRequest();
    HttpServletResponse response=ServletActionContext.getResponse();

    private PageObject pageObject=new PageObject();

    public PageObject getPageObject() {
        return pageObject;
    }

    public void setPageObject(PageObject pageObject) {
        this.pageObject = pageObject;
    }
    //条件查询
    public void findlist() throws IOException {
        String find_gardenName=request.getParameter("find_gardenName");
        if(find_gardenName!=null){
            if(!find_gardenName.equals("----")){
                condition.put("gardenName",find_gardenName);
            }
        }
        ActionContext.getContext().getSession().put("tj",condition);
        DetachedCriteria dc=DetachedCriteria.forClass(ProjectInfo.class);
        int count=base.findGetCount(dc,condition);
        pageObject.setTotalRows(count);
        projectList=base.findByConditionfz(dc,condition,pageObject);
        String json=JSON.toJSONString(projectList);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write("{\"total\":"+count+",\"rows\":"+json+"}");
        System.out.println("{\"total\":"+count+",\"rows\":"+json+"}");

    }

    public String list() throws Exception{
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
        DetachedCriteria dc=DetachedCriteria.forClass(ProjectInfo.class);
        Map m=(Map) ActionContext.getContext().getSession().get("tj");
        int count=0;
        if(m!=null&&m.size()>0){
            count=base.findGetCount(dc,m);
        }else{
            count=(int)base.getCount(dc);
        }
        pageObject.setTotalRows(count);
        projectList=base.findByConditionfz(dc,m,pageObject);
        String json=JSON.toJSONString(projectList);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print("{\"total\":"+count+",\"rows\":"+json+"}");
        System.out.println("{\"total\":"+count+",\"rows\":"+json+"}");
        return null;
    }

    String date1="",year="",month="",day="",hour="",miu="",s="";
    public void add() throws Exception {
        System.out.println("新增名称======="+request.getParameter("projectName"));
        String projectName=request.getParameter("projectName");
        String gardenName=request.getParameter("gardenName");
        String gardenCode=request.getParameter("gardenCode");
        Double buildArea= new Double(request.getParameter("buildArea"));
        Double useArea= new Double(request.getParameter("useArea"));
        Double viewArea= new Double(request.getParameter("viewArea"));
        String startTime=request.getParameter("startTime");
        SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formateDate2=sdf2.format(sdf2.parse(startTime));
        String endTime=request.getParameter("endTime");
        SimpleDateFormat sdf3=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formateDate3=sdf3.format(sdf3.parse(endTime));
        String location=request.getParameter("location");
        Double hourseCount= new Double(request.getParameter("hourseCount"));
        Double salePrice= new Double(request.getParameter("salePrice"));
        int orderid= Integer.parseInt(request.getParameter("orderid"));
        String isPaid=request.getParameter("isPaid");
        String description=request.getParameter("description");

        project.setProjectName(projectName);
        project.setGardenName(gardenName);
        project.setGardenCode(gardenCode);
        project.setBuildArea(buildArea);
        project.setUseArea(useArea);
        project.setViewArea(viewArea);
        project.setStartTime(Timestamp.valueOf(startTime));
        project.setEndTime(Timestamp.valueOf(endTime));
        project.setLocation(location);
        project.setHourseCount(hourseCount);
        project.setSalePrice(salePrice);
        project.setOrderid(orderid);
        project.setIsPaid(isPaid);
        project.setDescription(description);
        base.save(project);
    }

    public void save() throws Exception{
        System.out.println("修改");
        int projectId=Integer.parseInt(request.getParameter("projectId"));
        String projectName=request.getParameter("projectName");
        String gardenName=request.getParameter("gardenName");
        String gardenCode=request.getParameter("gardenCode");
        Double buildArea= new Double(request.getParameter("buildArea"));
        Double useArea= new Double(request.getParameter("useArea"));
        Double viewArea= new Double(request.getParameter("viewArea"));
        String startTime=request.getParameter("startTime");
        SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formateDate2=sdf2.format(sdf2.parse(startTime));
        String endTime=request.getParameter("endTime");
        SimpleDateFormat sdf3=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formateDate3=sdf3.format(sdf3.parse(endTime));
        String location=request.getParameter("location");
        Double hourseCount= new Double(request.getParameter("hourseCount"));
        Double salePrice= new Double(request.getParameter("salePrice"));
        int orderid= Integer.parseInt(request.getParameter("orderid"));
        String isPaid=request.getParameter("isPaid");
        String description=request.getParameter("description");

        project.setProjectId(projectId);
        project.setProjectName(projectName);
        project.setGardenName(gardenName);
        project.setGardenCode(gardenCode);
        project.setBuildArea(buildArea);
        project.setUseArea(useArea);
        project.setViewArea(viewArea);
        project.setStartTime(Timestamp.valueOf(startTime));
        project.setEndTime(Timestamp.valueOf(endTime));
        project.setLocation(location);
        project.setHourseCount(hourseCount);
        project.setSalePrice(salePrice);
        project.setOrderid(orderid);
        project.setIsPaid(isPaid);
        project.setDescription(description);
        base.saveOrUpdate(project);
    }

    public void delete() throws Exception{
        String projectId=request.getParameter("projectId");
        System.out.print("要删除的id:======="+projectId);
        ProjectInfo pro=(ProjectInfo) base.findObjById(ProjectInfo.class,Integer.parseInt(projectId));
        base.delete(pro);
    }

    public BaseDAO getBase() {
        return base;
    }

    public void setBase(BaseDAO base) {
        this.base = base;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public List<ProjectInfo> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<ProjectInfo> projectList) {
        this.projectList = projectList;
    }
}
