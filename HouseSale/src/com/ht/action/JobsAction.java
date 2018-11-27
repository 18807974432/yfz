package com.ht.action;

import com.alibaba.fastjson.JSON;
import com.ht.dao.*;
import com.ht.vo.Jobs;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Summer on 2018/10/19.
 */
public class JobsAction extends ActionSupport {
    private PageObject pager = new PageObject();
    private IBaseDAO base;
    private Jobs jobs;
    private List<Jobs> jobsList;

    public PageObject getPager() {
        return pager;
    }

    public void setPager(PageObject pager) {
        this.pager = pager;
    }

    public Jobs getJobs() {
        return jobs;
    }

    public void setJobs(Jobs jobs) {
        this.jobs = jobs;
    }

    public List<Jobs> getJobsList() {
        return jobsList;
    }

    public void setJobsList(List<Jobs> jobsList) {
        this.jobsList = jobsList;
    }
    //调用dao
    public JobsAction(){
        this.base=((IBaseDAO) WebApplicationContextUtil.createAppService("dao"));
    }
    //分页查询
    public void getdata()throws Exception{
        DetachedCriteria dc = DetachedCriteria.forClass(Jobs.class);
        dc.setProjection(Projections.rowCount());
        Number count = this.base.getCount(dc);
        dc.setProjection(null);
        this.pager.setTotalRows(count);
        List list = this.base.findPageByDetached(dc, this.pager.getStartRow(), this.pager.getPageRow());
        this.pager.setDatas(list);
    }
    //控制条件
    public void validateSave()throws Exception{
            getdata();
            if(jobs.getJobname()==null||jobs.getJobname().equals("")){
                addFieldError("jobs.jobName","请输入职位");
                return;
            }
    }

    public String save()throws Exception{
        if(this.jobs.getJobid()>0){
            ContextUtils.saveUserLog(this.jobs,2);
        }else {
            ContextUtils.saveUserLog(this.jobs,1);
        }
        this.base.saveOrUpdate(this.jobs);
        return list();
    }

    //删除
    public String del() throws Exception{
        ContextUtils.saveUserLog(this.jobs,3);
        this.base.delete(this.jobs);
        return "list";
    }
    //列表
    public String list() throws Exception{
        getdata();
        return SUCCESS;
    }
    public String json()throws Exception{
        this.jobs=(Jobs) this.base.findObjById(Jobs.class,Integer.valueOf(this.jobs.getJobid()));
        return "json";
    }

}
