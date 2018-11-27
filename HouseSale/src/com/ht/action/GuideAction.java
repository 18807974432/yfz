package com.ht.action;

import com.alibaba.fastjson.JSONObject;
import com.ht.dao.IBaseDAO;
import com.ht.dao.PageObject;
import com.ht.dao.WebApplicationContextUtil;
import com.ht.vo.*;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class GuideAction extends ActionSupport {

    HttpServletResponse response= ServletActionContext.getResponse();
    private PageObject pager = new PageObject();
    private IBaseDAO base = (IBaseDAO) WebApplicationContextUtil.createService("dao");
    private List<SaleState> saleState;
    private HourseInfo houseInfo;
    private TermInfo termInfo;
    private ProjectInfo proj;
    private List<TermInfo> termList;
    private List<Housemodel> houseModel;

    private List<ProjectInfo> projInfo;
    private CustomerInfo custInfo;
    private List<WorkProcess> wpInfo;

    public HourseInfo getHouseInfo() {
        return houseInfo;
    }

    public void setHouseInfo(HourseInfo houseInfo) {
        this.houseInfo = houseInfo;
    }

    public TermInfo getTermInfo() {
        return termInfo;
    }

    public void setTermInfo(TermInfo termInfo) {
        this.termInfo = termInfo;
    }

    public ProjectInfo getProj() {
        return proj;
    }

    public void setProj(ProjectInfo proj) {
        this.proj = proj;
    }

    public List<TermInfo> getTermList() {
        return termList;
    }

    public void setTermList(List<TermInfo> termList) {
        this.termList = termList;
    }

    public List<Housemodel> getHouseModel() {
        return houseModel;
    }

    public void setHouseModel(List<Housemodel> houseModel) {
        this.houseModel = houseModel;
    }



    public List<ProjectInfo> getProjInfo() {
        return projInfo;
    }

    public void setProjInfo(List<ProjectInfo> projInfo) {
        this.projInfo = projInfo;
    }

    public CustomerInfo getCustInfo() {
        return custInfo;
    }

    public void setCustInfo(CustomerInfo custInfo) {
        this.custInfo = custInfo;
    }

    public List<WorkProcess> getWpInfo() {
        return wpInfo;
    }

    public void setWpInfo(List<WorkProcess> wpInfo) {
        this.wpInfo = wpInfo;
    }

    public List<HouseEvent> getHeInfo() {
        return heInfo;
    }

    public void setHeInfo(List<HouseEvent> heInfo) {
        this.heInfo = heInfo;
    }

    private List<HouseEvent> heInfo;

    public List<SaleState> getSaleState() {
        return saleState;
    }

    public void setSaleState(List<SaleState> saleState) {
        this.saleState = saleState;
    }

    public PageObject getPager() { return pager; }

    public void setPager(PageObject pager) { this.pager = pager; }

    public IBaseDAO getBase() {
        return base;
    }

    public void setBase(IBaseDAO base) {
        this.base = base;
    }

    public String list(){
        return "guide";
    }

    public String guide()throws Exception {
        getSaleData();
        return "guide";
    }

    public void getSaleData() {
        DetachedCriteria dc1 = DetachedCriteria.forClass(ProjectInfo.class);
        this.projInfo = this.base.findByDetached(dc1);
        int proj_id = 0;
        if (this.proj != null){
            proj_id = this.proj.getProjectId();
        }else{
            proj_id = ((ProjectInfo)this.projInfo.get(0)).getProjectId();
            this.proj = new ProjectInfo();
        }
        this.proj.setProjectId(proj_id);

        DetachedCriteria dc2 = DetachedCriteria.forClass(TermInfo.class);
        dc2.add(Restrictions.eq("pinfo.projectId", Integer.valueOf(proj_id)));
        this.termList = this.base.findByDetached(dc2);

        DetachedCriteria dc3 = DetachedCriteria.forClass(SaleState.class);
        saleState = this.base.findByDetached(dc3);
        if (this.termList.size() <= 0) {
            return;
        }
        int term_id = 0;
        if (this.termInfo == null){
            term_id = ((TermInfo)this.termList.get(0)).getTermId();
            this.termInfo = new TermInfo();
        }else{
            this.termInfo = ((TermInfo)this.base.findObjByID(TermInfo.class, Integer.valueOf(this.termInfo.getTermId())));
            if (proj_id != this.termInfo.getPinfo().getProjectId()){
                term_id = ((TermInfo)this.termList.get(0)).getTermId();
                this.termInfo = ((TermInfo)this.termList.get(0));
            }else{
                term_id = this.termInfo.getTermId();
            }
        }
        this.termInfo.setTermId(term_id);
        DetachedCriteria dc = DetachedCriteria.forClass(HourseInfo.class);
        dc.add(Restrictions.eq("termInfo.termId", Integer.valueOf(term_id)));

        ProjectionList plist = Projections.projectionList();
        plist.add(Projections.groupProperty("unitid"));
        plist.add(Projections.groupProperty("floor"));
        plist.add(Projections.count("hourseno"));
        dc.setProjection(plist);
        dc.addOrder(Order.asc("unitid"));
        dc.addOrder(Order.desc("floor"));
        List list = this.base.findByDetached(dc);


        int[][] termarr = (int[][])null;
        if (list.size() > 0){
            int[][] temp = new int[list.size()][2];
            for (int i = 0; i < list.size(); i++)
            {
                Object[] o = (Object[])list.get(i);
                Integer key = (Integer)o[0];
                String val = o[2] + "";
                temp[i][0] = key.intValue();
                temp[i][1] = (Integer.parseInt(val)) ;
            }
            int unitid = temp[0][0];
            int ncount = 1;
            for (int i = 1; i < list.size(); i++) {
                if (unitid == temp[i][0]){
                    temp[i][0] = 0;
                }else{
                    unitid = temp[i][0];
                    ncount++;
                }
            }
            termarr = new int[ncount][2];
            ncount = 0;
            for (int i = 0; i < list.size(); i++) {
                if (temp[i][0] != 0){
                    termarr[ncount][0] = temp[i][0];
                    termarr[ncount][1] = temp[i][1];
                    ncount++;
                }
            }
        }
        DetachedCriteria dc4 = DetachedCriteria.forClass(HourseInfo.class);
        dc4.add(Restrictions.eq("termInfo.termId", Integer.valueOf(term_id)));
        dc4.addOrder(Order.asc("unitid"));
        dc4.addOrder(Order.desc("floor"));
        dc4.addOrder(Order.asc("hourseno"));
        List houseData = this.base.findByDetached(dc4);
        System.out.println("houseData.size=" + houseData.size());
        ServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("maps", termarr);
        request.setAttribute("houseData", houseData);
    }

    public void json() throws Exception{
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String tds = "";
        this.houseInfo = ((HourseInfo)this.base.findObjByID(HourseInfo.class, Integer.valueOf(this.houseInfo.getHourseId())));
        if (this.houseInfo.getCustid() > 0)
        {
            this.custInfo = ((CustomerInfo)this.base.findObjByID(CustomerInfo.class, Integer.valueOf(this.houseInfo.getCustid())));

            DetachedCriteria dc = DetachedCriteria.forClass(BuyHourse.class);
            dc.add(Restrictions.eq("hourseInfo.hourseId", Integer.valueOf(this.houseInfo.getHourseId())));
            dc.add(Restrictions.eq("customerInfo.custId", Integer.valueOf(this.custInfo.getCustId())));
            dc.add(Restrictions.eq("invalid", "有效"));
            dc.add(Restrictions.eq("oprType", "买房"));
            List list = this.base.findByDetached(dc);
            if (list.size() > 0)
            {
                BuyHourse buy = (BuyHourse)list.get(0);

                DetachedCriteria dc1 = DetachedCriteria.forClass(WorkProcess.class);
                dc1.add(Restrictions.eq("buy.buyId", Integer.valueOf(buy.getBuyId())));
                dc1.addOrder(Order.asc("orderid"));
                this.wpInfo = this.base.findByDetached(dc1);
                if(wpInfo!=null){
                    tds="<tr><td align='center'>序号</td><td align='center'>名称</td><td align='center'>应交金额</td><td align='center'>计划时间</td><td align='center'>完成情况</td></tr>";
                    for(WorkProcess v : wpInfo){
                        String a = v.getStartTime()+"";
                        tds+="<tr>";
                        tds+="<td align='center'>"+v.getOrderid()+"</td>";
                        tds+="<td align='center'>"+v.getFlowname()+"</td>";
                        tds+="<td align='center'>"+v.getPlanMoney()+"</td>";
                        tds+="<td align='center'>"+a.substring(0,10)+"</td>";
                        tds+="<td align='center'>"+v.getStatus()+"</td>";
                        tds+="</tr>";
                    }
                }else{
                    tds="<tr><td align='center'><font size='3' color='blue'><b>"+houseInfo.getSaleState()+"</b></font></td></tr>";
                }

            }
        }else{
            tds="<tr><td align='center'><font size='3' color='blue'><b>"+houseInfo.getSaleState()+"</b></font></td></tr>";
        }
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("houseInfo",houseInfo);
        jsonObject.put("custInfo",custInfo);
        jsonObject.put("wpInfo",wpInfo);
        jsonObject.put("tds",tds);
        out.print(jsonObject.toJSONString());
        out.flush();
        out.close();
    }
}
