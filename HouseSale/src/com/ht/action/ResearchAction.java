package com.ht.action;

import com.alibaba.fastjson.JSON;
import com.ht.dao.ContextUtils;
import com.ht.dao.IBaseDAO;
import com.ht.dao.PageObject;
import com.ht.dao.WebApplicationContextUtil;
import com.ht.vo.*;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ResearchAction extends ActionSupport {
    private IBaseDAO base = ((IBaseDAO)WebApplicationContextUtil.createService("dao"));;
    private PageObject pager = new PageObject();//分页对象
    private ResearchExam researchExam;
    private List<ResearchExam> researchExamList;
    private String ids; //删除id
    private String msg;
    private String find;//模糊查询
    private ProjectInfo proj;
    private ResearchExam research;
    private ExamMaster exam;
    private List<ExamMaster> rows;
    private List<ProjectInfo> projdata;
    private List<Options> examResult;
    private List<ExamResult> answerList;
    private String custid;

    HttpServletResponse hsr=ServletActionContext.getResponse();
    HttpServletRequest request=ServletActionContext.getRequest();

    public void setExamResult(List<Options> examResult) {
        this.examResult = examResult;
    }

    public List<Options> getExamResult() {
        return this.examResult;
    }
    public PageObject getPager() {
        return pager;
    }
    public List<ProjectInfo> getProjdata() {
        return this.projdata;
    }

    public void setProjdata(List<ProjectInfo> projdata) {
        this.projdata = projdata;
    }

    public void setPager(PageObject pager) {
        this.pager = pager;
    }

    public ResearchExam getResearchExam() {
        return researchExam;
    }

    public void setResearchExam(ResearchExam researchExam) {
        this.researchExam = researchExam;
    }

    public List<ResearchExam> getResearchExamList() {
        return researchExamList;
    }

    public void setResearchExamList(List<ResearchExam> researchExamList) {
        this.researchExamList = researchExamList;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFind() {
        return find;
    }

    public void setFind(String find) {
        this.find = find;
    }

    public ProjectInfo getProj() {
        return proj;
    }

    public void setProj(ProjectInfo proj) {
        this.proj = proj;
    }

    public ResearchExam getResearch() {
        return research;
    }

    public void setResearch(ResearchExam research) {
        this.research = research;
    }

    public ExamMaster getExam() {
        return exam;
    }

    public void setExam(ExamMaster exam) {
        this.exam = exam;
    }

    public List<ExamMaster> getRows() {
        return this.rows;
    }

    public void setRows(List<ExamMaster> rows) {
        this.rows = rows;
    }

    public List<ExamResult> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<ExamResult> answerList) {
        this.answerList = answerList;
    }

    public String getCustid() {
        return custid;
    }

    public void setCustid(String custid) {
        this.custid = custid;
    }
    //保存
    public String save()throws Exception {
        research.setProj(proj);
        if (research.getReseachid() == 0) {
            SimpleDateFormat format =  new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss ");
            Date date=new Date();
            research.setOprtime(Timestamp.valueOf(format.format(date)));
            research.setUserid("admin");
            //research.setUserid(ContextUtils.getUserInfo().getUsername());
        }
        else
        //修改
        {
            ResearchExam re = (ResearchExam)base.findObjById(ResearchExam.class, Integer.valueOf(research.getReseachid()));
            research.setExamCount(re.getExamCount());
            research.setUserid(re.getUserid());
            research.setOprtime(re.getOprtime());
        }
        base.saveOrUpdate(research);
        return "list";
    }
    //保存验证
    public void validateSave() {
        getdata();
        if ((research.getExamName() == null) || (research.getExamName().equals(""))) {
            addFieldError("research.reseachid", "答卷名称不能为空！");
            return;
        }
    }

    //修改试题
    public String update() throws Exception {
        exam = ((ExamMaster)base.findObjById(ExamMaster.class, Integer.valueOf(exam.getExamid())));
        return "succexam";
    }

    //保存试题
    public String saveExam() throws Exception {
        exam.setResearch(research);
        if (exam.getExamid() == 0) {
            SimpleDateFormat format =  new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss ");
            Date s=new Date();
            exam.setOprtime(Timestamp.valueOf(format.format(s)));
            exam.setUserid("admin");
            //exam.setUserid(ContextUtils.getUserInfo().getUsername());
        }
        else {
            ExamMaster em = (ExamMaster)base.findObjById(ExamMaster.class, Integer.valueOf(exam.getExamid()));
            exam.setOprtime(em.getOprtime());
            exam.setUserid(em.getUserid());
        }
        base.saveOrUpdate(exam);
        return "list";
    }
    //删除
    public String del() throws Exception {
        base.delete(this.research);
        return "list";
    }
    //删除试题
    public String delexam() throws Exception {
        base.delete(exam);
        return "list";
    }
    public String list() throws Exception {
        getdata();
        return "success";
    }
    private void getdata() {
        DetachedCriteria dc = DetachedCriteria.forClass(ResearchExam.class);
        dc.setProjection(Projections.rowCount());
        Object cnt = base.getCount(dc);
        pager.setTotalRows((Number) cnt);
        dc.setProjection(null);
        List list = this.base.findPageByDetached(dc, this.pager.getStartRow(), this.pager.getPageRow());
        pager.setDatas(list);
        DetachedCriteria dc1 = DetachedCriteria.forClass(ProjectInfo.class);
        projdata =base.findByDetached(dc1);
    }
    public String json() throws Exception {

        research = ((ResearchExam)base.findObjById(ResearchExam.class, Integer.valueOf(research.getReseachid())));
        return "json";
    }
    public String json2() throws Exception {
        DetachedCriteria dc = DetachedCriteria.forClass(ExamMaster.class);
        dc.add(Restrictions.eq("research.reseachid", Integer.valueOf(research.getReseachid())));
        dc.addOrder(Order.asc("unitNO"));
        rows = base.findByDetached(dc);

        ServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(getJson(rows));
        return null;
    }
    //把调研答卷列表显示在客户资料页面下面
    public String json3() throws Exception {
        DetachedCriteria dc = DetachedCriteria.forClass(ResearchExam.class);
        dc.add(Restrictions.eq("invalid", "启用"));
        rows = base.findByDetached(dc);
        ServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(getExamJson(rows, custid));
        return null;
    }
    //把试卷对象转换为符合datagrid的json格式
    public String getJson(List list) throws Exception {
        //分页
//        String page=request.getParameter("page");
//        String rowss=request.getParameter("rows");//每页显示记录数
//        if(page==null||page.equals("")){
//            pager.setCur_page(1);
//        }else{
//            pager.setCur_page(Integer.parseInt(page));
//        }
//        if(rows==null||rows.equals("")){
//            pager.setPageRow(5);
//        }else {
//            pager.setPageRow(Integer.parseInt(rowss));
//        }

//        DetachedCriteria dc=DetachedCriteria.forClass(ExamMaster.class);
//        int count=(int)base.getCount(dc);
//        System.out.println("总记录数===================="+count);
//        pager.setTotalRows(count);
//        rows=base.findPageByDetach(dc,pager);
//        System.out.println("rows对象============="+rows.size());
//        String json=JSON.toJSONString(rows);//把List对象传入json得到json格式

        int size = list.size();
        String json = "{\"total\":" + list.size() + ",\"rows\":[";
        for (int i = 0; i < size; i++) {
            ExamMaster exam = (ExamMaster)list.get(i);
            json = json + "{\"examid\":\"" + exam.getExamid() + "\",";
            json = json + "\"unitNO\":\"" + exam.getUnitNO() + "\",";
            json = json + "\"unitName\":\"" + exam.getUnitName() + "\",";
            json = json + "\"unitType\":\"" + exam.getUnitType() + "\",";
            json = json + "\"optCnt\":\"" + exam.getOptCnt() + "\",";
            json = json + "\"question1\":\"" + exam.getQuestion1() + "\",";
            json = json + "\"question2\":\"" + exam.getQuestion2() + "\",";
            json = json + "\"question3\":\"" + exam.getQuestion3() + "\",";
            json = json + "\"question4\":\"" + exam.getQuestion4() + "\",";
            json = json + "\"question5\":\"" + exam.getQuestion5() + "\",";
            json = json + "\"question6\":\"" + exam.getQuestion6() + "\",";
            json = json + "\"question7\":\"" + exam.getQuestion7() + "\",";
            json = json + "\"question8\":\"" + exam.getQuestion8() + "\",";
            json = json + "\"value1\":\"" + exam.getValue1() + "\",";
            json = json + "\"value2\":\"" + exam.getValue2() + "\",";
            json = json + "\"value3\":\"" + exam.getValue3() + "\",";
            json = json + "\"value4\":\"" + exam.getValue4() + "\",";
            json = json + "\"value5\":\"" + exam.getValue5() + "\",";
            json = json + "\"value6\":\"" + exam.getValue6() + "\",";
            json = json + "\"value7\":\"" + exam.getValue7() + "\",";
            json = json + "\"value8\":\"" + exam.getValue8() + "\",";
            if (i + 1 == size) {
                json = json + "\"reseachid\":\"" + exam.getResearch().getReseachid() + "\"}";
            } else {
                json = json + "\"reseachid\":\"" + exam.getResearch().getReseachid() + "\"},";
            }
        }
        json = json + "]}";
        //json = "{\"total\":"+count+",\"rows\":"+json+"}";
        return json;
    }
    //取出试卷上的题目
    public void examdata() {
        research = ((ResearchExam)base.findObjById(ResearchExam.class, Integer.valueOf(research.getReseachid())));
        System.out.println("researchid=="+research.getReseachid());
        DetachedCriteria dc = DetachedCriteria.forClass(ExamMaster.class);
        dc.add(Restrictions.eq("research.reseachid", Integer.valueOf(research.getReseachid())));
        dc.addOrder(Order.asc("unitNO"));
        rows = base.findByDetached(dc);
        System.out.println("rows====================="+rows.size());
    }
    //显示调研试卷信息
    public String examlist() throws Exception {
        examdata();
        //客户以前的答题结果
        DetachedCriteria dc = DetachedCriteria.forClass(ExamResult.class);
        dc.add(Restrictions.eq("research.reseachid", Integer.valueOf(this.research.getReseachid())));
        dc.add(Restrictions.eq("custid", Integer.valueOf(Integer.parseInt(this.custid))));
        answerList = base.findByDetached(dc);
        return "examlist";
    }
    //阅览试卷
    public String examlistSample() throws Exception {
        examdata();
        return "examlistSample";
    }
    //答题
    public String answer() throws Exception {
        int cnt = examResult.size();
        Options op = null;
        if (cnt > 0) {
            op = (Options)examResult.get(0);
            //得到试卷id
            research.setReseachid(Integer.parseInt(op.getQuestion()));
            //得到客户id
            custid = op.getOrderid();
            DetachedCriteria dc = DetachedCriteria.forClass(ExamResult.class);
            dc.add(Restrictions.eq("research.reseachid", Integer.valueOf(this.research.getReseachid())));
            dc.add(Restrictions.eq("custid", Integer.valueOf(Integer.parseInt(this.custid))));
            List list = this.base.findByDetached(dc);
        }
        for (int i = 0; i < cnt; i++) {
            op = (Options)this.examResult.get(i);
            if (op.getResult() != null)
            {
                exam = new ExamMaster();
                exam.setExamid(op.getExamid());
                ExamResult ans = new ExamResult();
                ans.setCustid(Integer.parseInt(op.getOrderid()));
                ans.setExamid(op.getExamid());

                String str = "";
                for (int j = 0; j < op.getResult().length; j++) {
                    str = str + op.getResult()[j] + ",";
                }
                str = str.substring(0, str.length() - 1);
                ans.setResult(str);
                ans.setUserid(ContextUtils.getUserInfo().getUsername());

                SimpleDateFormat format =  new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss ");
                Date s=new Date();
                ans.setOprtime(Timestamp.valueOf(format.format(s)));
                ans.setResearch(research);
                ans.setExam(exam);
                base.saveOrUpdate(ans);
            }
        }
        return examlist();
    }

    public String getExamJson(List list, String custid) throws Exception {

//        int size = list.size();
//        IBaseDAO base = (IBaseDAO)WebApplicationContextUtil.createService("BaseDAO");
        //分页
//        String page=request.getParameter("page");
//        String rowss=request.getParameter("rows");//每页显示记录数
//        if(page==null||page.equals("")){
//            pager.setCur_page(1);
//        }else{
//            pager.setCur_page(Integer.parseInt(page));
//        }
//        if(rows==null||rows.equals("")){
//            pager.setPageRow(5);
//        }else {
//            pager.setPageRow(Integer.parseInt(rowss));
//        }

//        DetachedCriteria dc = DetachedCriteria.forClass(ExamResult.class);
//        //在考试中成绩表中，找出试卷中已做题目的数量
//        dc.add(Restrictions.eq("research.reseachid", Integer.valueOf(exam.getReseachid())));
//        dc.add(Restrictions.eq("custid", Integer.valueOf(Integer.parseInt(custid))));
//        dc.setProjection(Projections.rowCount());
//
//        int count=(int)base.getCount(dc);
//        System.out.println("总记录数："+count);
//
//        pager.setTotalRows(count);
//        answerList=base.findPageByDetach(dc,pager);
//        String json=JSON.toJSONString(answerList);


        int size = list.size();
        String json = "{\"total\":" + list.size() + ",\"rows\":[";
        for (int i = 0; i < size; i++) {
            ResearchExam exam = (ResearchExam)list.get(i);
            //在试卷表中找到该试卷已做的题目
            DetachedCriteria dc = DetachedCriteria.forClass(ExamResult.class);
            dc.add(Restrictions.eq("research.reseachid", Integer.valueOf(exam.getReseachid())));
            dc.add(Restrictions.eq("custid", Integer.valueOf(Integer.parseInt(custid))));
            dc.setProjection(Projections.rowCount());
            //获取做题数目
            Number cnt =base.getCount(dc);
            json = json + "{\"reseachid\":\"" + exam.getReseachid() + "\",";
            json = json + "\"projectid\":\"" + exam.getProj().getProjectId() + "\",";
            json = json + "\"examName\":\"<a href='javascript:void(0)' onclick=openExam(" + exam.getReseachid() + ");>" + exam.getExamName() + "</a>\",";
            json = json + "\"invalid\":\"" + exam.getInvalid() + "\",";
            json = json + "\"orderid\":\"" + exam.getOrderid() + "\",";
            json = json + "\"examCount\":\"" + cnt + "/" + exam.getExamCount() + "\",";
            json = json + "\"description\":\"" + exam.getDescription() + "\",";
            json = json + "\"userid\":\"" + exam.getUserid() + "\",";
            if (i + 1 == size) {
                json = json + "\"oprtime\":\"" + exam.getOprtime() + "\"}";
            } else {
                json = json + "\"oprtime\":\"" + exam.getOprtime() + "\"},";
            }
        }
        json = json + "]}";
        return json;
    }
}
