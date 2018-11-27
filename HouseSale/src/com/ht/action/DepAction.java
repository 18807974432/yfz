package com.ht.action;

import com.ht.dao.*;
import com.ht.vo.Dep;
import com.ht.dao.ContextUtils;
import com.ht.vo.Users;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Summer on 2018/10/19.
 */
public class DepAction extends ActionSupport {
        HttpServletResponse response= ServletActionContext.getResponse();
        private IBaseDAO base = (IBaseDAO)WebApplicationContextUtil.createService("dao");;
        private Dep deps;
        private PageObject pager = new PageObject();

        public Dep getDeps() {
                return deps;
        }

        public void setDeps(Dep deps) {
                this.deps = deps;
        }

        public PageObject getPager() {
                return pager;
        }

        public void setPager(PageObject pager) {
                this.pager = pager;
        }

//        public void tree()throws Exception{
//                DetachedCriteria dc=DetachedCriteria.forClass(Dep.class);
//                int count=(int)base.getCount(dc);
//                depList=base.findByDetach(dc);
//
//                JSONArray jsonArray=new JSONArray();//最后总数据
//                for(Dep d:depList){
//                        if(d.getParentid()==d.getDepid()){
//                                JSONObject j=new JSONObject();
//                                j.put("id",d.getDepid());
//                                j.put("name",d.getDepname());
//                                j.put("parendId",0);
//                                jsonArray.add(j);
//                        }else{
//                                JSONObject j=new JSONObject();
//                                j.put("id",d.getDepid());
//                                j.put("name",d.getDepname());
//                                j.put("parentId",d.getParentid());
//                                jsonArray.add(j);
//                        }
//
//                }
//                String json=jsonArray.toJSONString();
//                System.out.println(json);
//                response.setContentType("text/html;charset=UTF-8");
//                response.getWriter().write(json);
//
//        }
        public void getdata(){
                DetachedCriteria dc = DetachedCriteria.forClass(Dep.class);
                dc.setProjection(Projections.rowCount());
               Number cnt = this.base.getCount(dc);

                this.pager.setTotalRows(cnt);
                dc.setProjection(null);
                dc.addOrder(Order.asc("deps.depid"));
                dc.addOrder(Order.asc("depid"));

                List list = this.base.findByDetach(dc);
                this.pager.setDatas(list);
        }

        public void validateSave(){
                if((this.deps.getDepname()==null)||(this.deps.getDepname().equals(""))){
                addFieldError("deps.depName","请输入部门");
                getdata();
                return;
                }
        }

        public String save() throws Exception{
                Dep d=new Dep();
                if(this.deps.getParentid()!=0){
                        d.setDepid(this.deps.getParentid());
                        ContextUtils.saveUserLog(this.deps,2);
                }else {
                        d=this.deps;
                        ContextUtils.saveUserLog(this.deps,1);
                }
                this.deps.setDeps(d);
                this.base.saveOrUpdate(this.deps);
                return "list";
        }

        //删除
        public String del() throws Exception{
                DetachedCriteria dc=DetachedCriteria.forClass(Users.class);
                dc.add(Restrictions.eq("deps.depid",Integer.valueOf(this.deps.getDepid())));
                List list = this.base.findByDetach(dc);
                if(list.size()==0){
                    ContextUtils.saveUserLog(this.deps,3);
                    this.base.delete(this.deps);
                }
                return null;
        }
        //列表
        public String list() throws Exception{
                getdata();
                return SUCCESS;
        }
        //列表
        public String list1() throws Exception{
                getdata();
                return "list1";
        }

        public static void main(String[] args) {
                IBaseDAO base=(IBaseDAO)WebApplicationContextUtil.createService("BaseDAO");
                DetachedCriteria dc=DetachedCriteria.forClass(Dep.class);
                dc.setProjection(Projections.rowCount());
                Number cnt=base.getCount(dc);
        }
        public String json()throws Exception{
                this.deps=(Dep) this.base.findObjById(Dep.class,Integer.valueOf(this.deps.getDepid()));
                return "json";
        }

}
