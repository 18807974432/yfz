package com.ht.action;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ht.dao.BaseDAO;
import com.ht.dao.PageObject;
import com.ht.dao.WebApplicationContextUtil;
import com.ht.vo.ProjectInfo;
import com.ht.vo.TermInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TermInfoAction extends ActionSupport {
    private BaseDAO base=(BaseDAO) WebApplicationContextUtil.createService("dao");
    private TermInfo term=new TermInfo();
    private List<TermInfo> termList=new ArrayList<>();
    private List<ProjectInfo> projectList=new ArrayList<>();
    private ProjectInfo project;
    PageObject pager=new PageObject();
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

    //条件查询
    public void findlist() throws Exception{
        String find_termName=request.getParameter("find_termName");
        if(find_termName!=null){
                condition.put("termName",find_termName);
        }
        ActionContext.getContext().getSession().put("tj",condition);
        DetachedCriteria dc=DetachedCriteria.forClass(TermInfo.class);
        int count=base.TerminFocount(dc,condition);
        pageObject.setTotalRows(count);
        DetachedCriteria dc2=DetachedCriteria.forClass(TermInfo.class);
        termList=base.TerminFolist(dc2,pageObject,condition);
        String json=JSON.toJSONString(termList);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write("{\"total\":"+count+",\"rows\":"+json+"}");
        System.out.println("{\"total\":"+count+",\"rows\":"+json+"}");

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
        DetachedCriteria dc=DetachedCriteria.forClass(TermInfo.class);
        Map m=(Map)ActionContext.getContext().getSession().get("tj");
        int count=0;
        if(m!=null&&m.size()>0){
            count=base.TerminFocount(dc,m);
        }else{
            count=(int)base.getCount(dc);
        }
        pageObject.setTotalRows(count);
        DetachedCriteria dc2=DetachedCriteria.forClass(TermInfo.class);
        termList=base.TerminFolist(dc2,pageObject,m);
        String json=JSON.toJSONString(termList);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write("{\"total\":"+count+",\"rows\":"+json+"}");
        System.out.println("{\"total\":"+count+",\"rows\":"+json+"}");
    }
    public void add() throws Exception{
        System.out.println("新增名称======="+request.getParameter("gardenName"));
        String gardenName=request.getParameter("gardenName");
        ProjectInfo projectInfo=new ProjectInfo();
        projectInfo.setProjectId(Integer.parseInt(gardenName));
        String termName=request.getParameter("termName");
        String termCode=request.getParameter("termCode");
        String termType=request.getParameter("termType");
        String prid=request.getParameter("prid");
        String  prPlanTime=request.getParameter("prPlanTime");
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String formateDate=sdf.format(sdf.parse(prPlanTime));
        String prFactTime=request.getParameter("prFactTime");
//        SimpleDateFormat sdf1=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        String formateDate1=sdf1.format(sdf1.parse(prFactTime));
        String auditno=request.getParameter("auditno");
        String contractno=request.getParameter("contractno");
        Double saleArea=new Double(request.getParameter("saleArea"));
        Double useArea=new Double(request.getParameter("useArea"));
        Double viewArea=new Double(request.getParameter("viewArea"));
        String startTime=request.getParameter("startTime");
//        SimpleDateFormat sdf2=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        String formateDate2=sdf2.format(sdf2.parse(startTime));
        String endTime=request.getParameter("endTime");
//        SimpleDateFormat sdf3=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        String formateDate3=sdf3.format(sdf3.parse(endTime));
        String saleTime=request.getParameter("saleTime");
//        SimpleDateFormat sdf4=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        String formateDate4=sdf4.format(sdf4.parse(saleTime));
        String liveTime=request.getParameter("liveTime");
//        SimpleDateFormat sdf5=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        String formateDate5=sdf5.form at(sdf5.parse(liveTime));
        Double hourseCount=new Double(request.getParameter("hourseCount"));
        Double salePrice=new Double(request.getParameter("salePrice"));
        String isPaid=request.getParameter("isPaid");
        Double hourseHeight=new Double(request.getParameter("hourseHeight"));
        int floorcount=Integer.parseInt(request.getParameter("floorcount"));
        Double buildHeight=new Double(request.getParameter("buildHeight"));
        int perCount=Integer.parseInt(request.getParameter("perCount"));
        String developer=request.getParameter("developer");
        String buildadultno=request.getParameter("buildadultno");
        String buildtype=request.getParameter("buildtype");
        String description=request.getParameter("description");

        term.setProject(projectInfo);
        term.setTermName(termName);
        term.setTermCode(termCode);
        term.setTermType(termType);
        term.setPrid(prid);
        term.setPrPlanTime(Timestamp.valueOf(prPlanTime));
        term.setPrFactTime(Timestamp.valueOf(prFactTime));
        term.setAuditno(auditno);
        term.setContractno(contractno);
        term.setSaleArea(saleArea);
        term.setUseArea(useArea);
        term.setViewArea(viewArea);
        term.setStartTime(Timestamp.valueOf(startTime));
        term.setEndTime(Timestamp.valueOf(endTime));
        term.setSaleTime(Timestamp.valueOf(saleTime));
        term.setLiveTime(Timestamp.valueOf( liveTime));
        term.setHourseCount(hourseCount);
        term.setSalePrice(salePrice);
        term.setIsPaid(isPaid);
        term.setHourseHeight(hourseHeight);
        term.setFloorcount(floorcount);
        term.setBuildHeight(buildHeight);
        term.setPerCount(perCount);
        term.setDeveloper(developer);
        term.setBuildadultno(buildadultno);
        term.setBuildtype(buildtype);
        term.setDescription(description);
        base.save(term);
    }
    public void save() throws Exception{
        System.out.println("修改");
        int termId=Integer.parseInt(request.getParameter("termId"));
        String gardenName=request.getParameter("gardenName");
        ProjectInfo projectInfo=new ProjectInfo();
        projectInfo.setProjectId(Integer.parseInt(gardenName));
        String  termName=request.getParameter("termName");
        String termCode=request.getParameter("termCode");
        String termType=request.getParameter("termType");
        String prid=request.getParameter("prid");
        String  prPlanTime=request.getParameter("prPlanTime");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formateDate=sdf.format(sdf.parse(prPlanTime));
        String prFactTime=request.getParameter("prFactTime");
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formateDate1=sdf1.format(sdf1.parse(prFactTime));
        String auditno=request.getParameter("auditno");
        String contractno=request.getParameter("contractno");
        Double saleArea=new Double(request.getParameter("saleArea"));
        Double useArea=new Double(request.getParameter("useArea"));
        Double viewArea=new Double(request.getParameter("viewArea"));
        String startTime=request.getParameter("startTime");
        SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formateDate2=sdf2.format(sdf2.parse(startTime));
        String endTime=request.getParameter("endTime");
        SimpleDateFormat sdf3=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formateDate3=sdf3.format(sdf3.parse(endTime));
        String saleTime=request.getParameter("saleTime");
        SimpleDateFormat sdf4=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formateDate4=sdf4.format(sdf4.parse(saleTime));
        String liveTime=request.getParameter("liveTime");
        SimpleDateFormat sdf5=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formateDate5=sdf5.format(sdf5.parse(liveTime));
        Double hourseCount=new Double(request.getParameter("hourseCount"));
        Double salePrice=new Double(request.getParameter("salePrice"));
        String isPaid=request.getParameter("isPaid");
        Double hourseHeight=new Double(request.getParameter("hourseHeight"));
        int floorcount=Integer.parseInt(request.getParameter("floorcount"));
        Double buildHeight=new Double(request.getParameter("buildHeight"));
        int perCount=Integer.parseInt(request.getParameter("perCount"));
        String developer=request.getParameter("developer");
        String buildadultno=request.getParameter("buildadultno");
        String buildtype=request.getParameter("buildtype");
        String description=request.getParameter("description");

        term.setTermId(termId);
        term.setPinfo(projectInfo);
        term.setTermName(termName);
        term.setTermCode(termCode);
        term.setTermType(termType);
        term.setPrid(prid);
        term.setPrPlanTime(Timestamp.valueOf(prPlanTime));
        term.setPrFactTime(Timestamp.valueOf(prFactTime));
        term.setAuditno(auditno);
        term.setContractno(contractno);
        term.setSaleArea(saleArea);
        term.setUseArea(useArea);
        term.setViewArea(viewArea);
        term.setStartTime(Timestamp.valueOf(startTime));
        term.setEndTime(Timestamp.valueOf(endTime));
        term.setSaleTime(Timestamp.valueOf(saleTime));
        term.setLiveTime(Timestamp.valueOf(liveTime));
        term.setHourseCount(hourseCount);
        term.setSalePrice(salePrice);
        term.setIsPaid(isPaid);
        term.setHourseHeight(hourseHeight);
        term.setFloorcount(floorcount);
        term.setBuildHeight(buildHeight);
        term.setPerCount(perCount);
        term.setDeveloper(developer);
        term.setBuildadultno(buildadultno);
        term.setBuildtype(buildtype);
        term.setDescription(description);
        base.saveOrUpdate(term);
    }
    public void delete() throws Exception{
        String termId=request.getParameter("termId");
        System.out.print("要删除的id:======="+termId);
        TermInfo term=(TermInfo) base.findObjById(TermInfo.class,Integer.parseInt(termId));
        base.delete(term);
    }

    public String listProject(){
        DetachedCriteria pdc=DetachedCriteria.forClass(ProjectInfo.class);
        projectList=base.findByDetach(pdc);

        return "success";
    }


    public TermInfo getTerm() {
        return term;
    }

    public void setTerm(TermInfo term) {
        this.term = term;
    }

    public List<TermInfo> getTermList() {
        return termList;
    }

    public void setTermList(List<TermInfo> termList) {
        this.termList = termList;
    }

    public List<ProjectInfo> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<ProjectInfo> projectList) {
        this.projectList = projectList;
    }

    public ProjectInfo getProject() {
        return project;
    }

    public void setProject(ProjectInfo project) {
        this.project = project;
    }

    public PageObject getPager() {
        return pager;
    }

    public void setPager(PageObject pager) {
        this.pager = pager;
    }
}
