package com.ht.action;

import com.alibaba.fastjson.JSON;
import com.ht.dao.IBaseDAO;
import com.ht.dao.PageObject;
import com.ht.dao.WebApplicationContextUtil;
import com.ht.vo.Housemodel;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class HouseModelAction extends ActionSupport {
    private IBaseDAO base= (IBaseDAO)WebApplicationContextUtil.createService("dao");
    private Housemodel house=new Housemodel();
    private List<Housemodel> houseList=new ArrayList<Housemodel>() ;
    private int page;
    private int rows;
    HttpServletRequest request= ServletActionContext.getRequest();
    HttpServletResponse response=ServletActionContext.getResponse();


    public String list() throws Exception{
        PageObject pager=new PageObject();
        System.out.println("list");
        if(pager==null||pager.equals("")){
            pager.setCur_page(1);
        }else {
            pager.setCur_page(page);
        }

        pager.setPageRow(rows);

        DetachedCriteria dc=DetachedCriteria.forClass(Housemodel.class);
        int count=(int)base.getCount(dc);
        System.out.println("总记录数："+count);
        pager.setTotalRows(count);
        houseList=base.findPageByDetach(dc,pager);
        String json=JSON.toJSONString(houseList);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print("{\"total\":"+count+",\"rows\":"+json+"}");
        System.out.println("{\"total\":"+count+",\"rows\":"+json+"}");
        return null;
    }

    //删除
    public void  delete() throws Exception {
        String modelId=request.getParameter("modelId");
        System.out.println("要删除的id：==================="+modelId);
        Housemodel s=(Housemodel) base.findObjById(Housemodel.class,Integer.parseInt(modelId));
        base.delete(s);
    }

    //新增
    public void add(){
        System.out.println("新增");
        String modelName=request.getParameter("modelName");
        house.setModelName(modelName);
        base.save(house);
    }

    public void save(){
        System.out.println("修改");
        int modelId=Integer.parseInt(request.getParameter("modelId"));
        String modelName=request.getParameter("modelName");
        house.setModelId(modelId);
        house.setModelName(modelName);
        base.saveOrUpdate(house);
    }

    public IBaseDAO getBase() {
        return base;
    }

    public void setBase(IBaseDAO base) {
        this.base = base;
    }


    public Housemodel getHouse() {
        return house;
    }

    public void setHouse(Housemodel house) {
        this.house = house;
    }

    public List<Housemodel> getHouseList() {
        return houseList;
    }

    public void setHouseList(List<Housemodel> houseList) {
        this.houseList = houseList;
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
}
