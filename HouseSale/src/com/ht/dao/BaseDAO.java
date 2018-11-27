package com.ht.dao;


import com.ht.action.ServiceConstants;
import com.ht.vo.City;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.io.Serializable;
import java.util.*;

/**
 * Created by rainbow on 2018/8/16.
 */
public  class BaseDAO extends HibernateDaoSupport implements IBaseDAO {

  public  List<City> citylist;

    public List<City> getCitylist() {
        return citylist;
    }

    public void setCitylist(List<City> citylist) {
        this.citylist = citylist;
    }

    //新增
    public void save(Object obj){
        getHibernateTemplate().save(obj);
    }
    //修改
    public void update(Object obj){
        getHibernateTemplate().update(obj);
    }
    //删除
    public void delete(Object obj){
        getHibernateTemplate().delete(obj);
    }
    //新增或修改
    public void saveOrUpdate(Object obj){
        getHibernateTemplate().saveOrUpdate(obj);
    }


    //查询一条记录
    public Object findObjById(Class clz,Serializable oid){
        return  getHibernateTemplate().get(clz,oid);
    }



    //使用QBC的离线查询，实现统一的统计记录数函数
    public Number getCount(DetachedCriteria dc){
        Number  count = 0;

        try{
            //清空session
            getHibernateTemplate().getSessionFactory().getCurrentSession().clear();
            Criteria cr = dc.getExecutableCriteria(getHibernateTemplate().getSessionFactory().getCurrentSession());
            count= (Number) cr.uniqueResult();
        }catch(Exception e){
            e.printStackTrace();
        }
        return count;

    }
    //使用QBC的离线查询，实现统计的记录数
    public  int  getCount2(DetachedCriteria dc){
        int count=0;

        try{
            //清空session
            getHibernateTemplate().getSessionFactory().getCurrentSession().clear();
            Criteria cr=dc.getExecutableCriteria(getHibernateTemplate().getSessionFactory().getCurrentSession());
            count=cr.list().size();
        }catch(Exception e){
            e.printStackTrace();
        }
        return count;
    }
    //使用QBC的离线查询，实现统一的统计记录数函数
    public Object  getObject(DetachedCriteria dc){
        Object o=null;
        try{
            //清空session
            getHibernateTemplate().getSessionFactory().getCurrentSession().clear();
            Criteria cr = dc.getExecutableCriteria(getHibernateTemplate().getSessionFactory().getCurrentSession());
            o =  cr.uniqueResult();
        }catch(Exception e){
            e.printStackTrace();
        }
        return o;


    }
    //多条件模糊查询
    public List findByConditionfz(DetachedCriteria dc, Map m,PageObject pager){
        List list=null;
        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        //清空缓存
        session.clear();
        try{
            Criteria cr = dc.getExecutableCriteria(session);
            if(m!=null&&m.size()>0){
                Set<Map.Entry<String, String>> set=m.entrySet();
                for(Map.Entry<String, String> me:set){
                    System.out.println(me.getKey()+"————"+me.getValue());
                    cr.add(Expression.like(me.getKey(),"%"+me.getValue()+"%"));
                }
            }
            cr.setFirstResult(pager.getStartRow());
            //每页显示记录数
            cr.setMaxResults(pager.getPageRow());
            list = cr.list();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

   /* public int getCount1(DetachedCriteria dc){
        int cnt = 0;
        try {
            //清空session
            getHibernateTemplate().getSessionFactory().getCurrentSession().clear();
            cnt =getHibernateTemplate().findByCriteria(dc).size();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return cnt;

    }*/
    //使用QBC的离线查询，实现统一的列表查询函数
    public List findByDetach(DetachedCriteria dc){
        List list=null;

        try{
            getHibernateTemplate().getSessionFactory().getCurrentSession().clear();
            list = getHibernateTemplate().findByCriteria(dc);
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;

    }
    public int TerminFocount(DetachedCriteria dc,Map m){
        int count=0;
        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        //清空缓存
        session.clear();
        try{
            Criteria cr=dc.getExecutableCriteria(session);
            cr.createAlias("project","project");
            cr.setProjection(
                    Projections.projectionList()
                            .add(Projections.property("termId"),"termId")
                            .add(Projections.property("termName"),"termName")
                            .add(Projections.property("termCode"),"termCode")
                            .add(Projections.property("termType"),"termType")
                            .add(Projections.property("project.projectName"),"projectName")
                            .add(Projections.property("project.gardenName"),"gardenName")
            );

            cr.addOrder(Order.asc("termId"));
            // 将多属性投影查询映射成一个Map对象
            List list=cr.setResultTransformer(cr.ALIAS_TO_ENTITY_MAP).list();
            if(m!=null&&m.size()>0){
                Set<Map.Entry<String, String>> set=m.entrySet();
                for(Map.Entry<String, String> me:set){
                    System.out.println(me.getKey()+"————"+me.getValue());
                    cr.add(Expression.like(me.getKey(),"%"+me.getValue()+"%"));
                }
            }
            count=cr.list().size();
        }catch(Exception e){
            e.printStackTrace();
        }
        return count;

    }
    public List TerminFolist(DetachedCriteria dc, PageObject p,Map m){
        List list=new ArrayList();
        Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
        //清空缓存
        session.clear();
        try{
            Criteria cr=dc.getExecutableCriteria(session);
            //加入分页设置
            //设置起始位置
            cr.setFirstResult(p.getStartRow());
            //设置每页显示记录数
            cr.setMaxResults(p.getPageRow());
            cr.createAlias("project","project");
            cr.setProjection(
                    Projections.projectionList()
                            .add(Projections.property("termId"),"termId")
                            .add(Projections.property("termName"),"termName")
                            .add(Projections.property("termCode"),"termCode")
                            .add(Projections.property("termType"),"termType")
                            .add(Projections.property("project.projectName"),"projectName")
                            .add(Projections.property("project.gardenName"),"gardenName")
            );

            cr.addOrder(Order.asc("termId"));
            // 将多属性投影查询映射成一个Map对象
            list=cr.setResultTransformer(cr.ALIAS_TO_ENTITY_MAP).list();
            if(m!=null&&m.size()>0){
                Set<Map.Entry<String, String>> set=m.entrySet();
                for(Map.Entry<String, String> me:set){
                    System.out.println(me.getKey()+"————"+me.getValue());
                    cr.add(Expression.like(me.getKey(),"%"+me.getValue()+"%"));
                }
            }
            list=cr.list();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    //查询一条记录
    public Object findclass(DetachedCriteria dc){


        Object classes=null;

        try{
            getHibernateTemplate().getSessionFactory().getCurrentSession().clear();
            classes = getHibernateTemplate().findByCriteria(dc);
        }catch(Exception e){
            e.printStackTrace();
        }
        return classes;

    }
    public Object findObjByID(Class clazz, Serializable id)
    {
        getHibernateTemplate().getSessionFactory().getCurrentSession().clear();
        return getHibernateTemplate().get(clazz, id);
    }
    //使用QBC的离线查询，实现统一的分页列表查询函数
    public List findPageByDetach(DetachedCriteria dc, PageObject pager){
        List list=null;
        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        //清空缓存
        session.clear();

        try{
            Criteria cr = dc.getExecutableCriteria(session);
            //加入分页设置
            //起始位置
            cr.setFirstResult(pager.getStartRow());
            //每页显示记录数
            cr.setMaxResults(pager.getPageRow());
            list = cr.list();

        }catch(Exception e){
            e.printStackTrace();
        }
        return list;

    }
    //实现统一分页
    public List findPageByDetached(DetachedCriteria dc,int firstResult,int maxResult){
        List list = null;
        try {
            getHibernateTemplate().getSessionFactory().getCurrentSession().clear();
            list=getHibernateTemplate().findByCriteria(dc,firstResult,maxResult);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return list;
    }
    public List findByDetached(DetachedCriteria dc) {
        List list = null;
        try
        {
            getHibernateTemplate().getSessionFactory().getCurrentSession().clear();
            list = getHibernateTemplate().findByCriteria(dc);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        return list;
    }
    //BuyHourse总表
    public List findPageByDetach2(DetachedCriteria dc, PageObject p,Map m){
        List list=new ArrayList();
        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        //清空缓存
        session.clear();
        try{
            Criteria cr=dc.getExecutableCriteria(session);
            //加入分页设置
            //设置起始位置
            cr.setFirstResult(p.getStartRow());
            //设置每页显示记录数
            cr.setMaxResults(p.getPageRow());
            cr.createAlias("customerInfo","customerInfo");
            cr.createAlias("hourseInfo","hourseInfo");
            cr.createAlias("hourseInfo2","hourseInfo2");
            cr.createAlias("paidScheme","paidScheme");
            cr.createAlias("workProcess","workProcess");
            cr.setProjection(
                    Projections.projectionList()
                            .add(Projections.property("buyId"),"buyId")
                            .add(Projections.property("customerInfo.custname"),"custname")
                            .add(Projections.property("hourseInfo.hourseName"),"hoursename")
                            .add(Projections.property("userid"),"userid")
                            .add(Projections.property("paidScheme.psname"),"psname")
                            .add(Projections.property("unitPrice"),"unitPrice")
                            .add(Projections.property("fitmentunitprice"),"fitmentunitprice")
                            .add(Projections.property("signunitprice"),"signunitprice")
                            .add(Projections.property("signunitprice"),"signunitprice")
                            .add(Projections.property("totalMoney"),"totalMoney")
                            .add(Projections.property("fitmenttotalprice"),"fitmenttotalprice")
                            .add(Projections.property("signtotalprice"),"signtotalprice")
                            .add(Projections.property("bankid"),"bankid")
                            .add(Projections.property("firstMoney"),"firstMoney")
                            .add(Projections.property("bankmoney"),"bankmoney")
                            .add(Projections.property("fitmentFirstPrice"),"fitmentFirstPrice")
                            .add(Projections.property("fitmentLastPrice"),"fitmentLastPrice")
                            .add(Projections.property("buyTime"),"buyTime")
                            .add(Projections.property("hourseInfo2.commisionPercent"),"commisionPercent")
                            .add(Projections.property("hourseInfo2.commisionMoney"),"commisionMoney")
                            .add(Projections.property("hourseInfo2.commisionpaid"),"commisionpaid")
                            .add(Projections.property("oprType"),"oprType")
                            .add(Projections.property("invalid"),"invalid")
            );
            cr.addOrder(Order.asc("buyId"));
            // 将多属性投影查询映射成一个Map对象
            list=cr.setResultTransformer(cr.ALIAS_TO_ENTITY_MAP).list();

            if(m!=null&&m.size()>0){
                Set<Map.Entry<String, String>> set=m.entrySet();
                for(Map.Entry<String, String> me:set){
                    System.out.println(me.getKey()+"————"+me.getValue());
                    cr.add(Expression.like(me.getKey(),"%"+me.getValue()+"%"));
                }
            }


            list=cr.list();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    //  buyhourse 获取数量
    public int findGetcount(DetachedCriteria dc,Map m){
        int count=0;
        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        //清空缓存
        session.clear();
        try{
            Criteria cr=dc.getExecutableCriteria(session);
            cr.createAlias("customerInfo","customerInfo");
            cr.createAlias("hourseInfo","hourseInfo");
            cr.createAlias("hourseInfo2","hourseInfo2");
            cr.createAlias("paidScheme","paidScheme");
            cr.createAlias("workProcess","workProcess");
            cr.setProjection(
                    Projections.projectionList()
                            .add(Projections.property("buyId"),"buyId")
                            .add(Projections.property("customerInfo.custname"),"custname")
                            .add(Projections.property("hourseInfo.hourseName"),"hoursename")
                            .add(Projections.property("userid"),"userid")
                            .add(Projections.property("paidScheme.psname"),"psname")
                            .add(Projections.property("unitPrice"),"unitPrice")
                            .add(Projections.property("fitmentunitprice"),"fitmentunitprice")
                            .add(Projections.property("signunitprice"),"signunitprice")
                            .add(Projections.property("signunitprice"),"signunitprice")
                            .add(Projections.property("totalMoney"),"totalMoney")
                            .add(Projections.property("fitmenttotalprice"),"fitmenttotalprice")
                            .add(Projections.property("signtotalprice"),"signtotalprice")
                            .add(Projections.property("bankid"),"bankid")
                            .add(Projections.property("firstMoney"),"firstMoney")
                            .add(Projections.property("bankmoney"),"bankmoney")
                            .add(Projections.property("fitmentFirstPrice"),"fitmentFirstPrice")
                            .add(Projections.property("fitmentLastPrice"),"fitmentLastPrice")
                            .add(Projections.property("buyTime"),"buyTime")
                            .add(Projections.property("hourseInfo2.commisionPercent"),"commisionPercent")
                            .add(Projections.property("hourseInfo2.commisionMoney"),"commisionMoney")
                            .add(Projections.property("hourseInfo2.commisionpaid"),"commisionpaid")
                            .add(Projections.property("oprType"),"oprType")
                            .add(Projections.property("invalid"),"invalid")
            );
            cr.addOrder(Order.asc("buyId"));
            // 将多属性投影查询映射成一个Map对象
            List list=cr.setResultTransformer(cr.ALIAS_TO_ENTITY_MAP).list();
            if(m!=null&&m.size()>0){
                Set<Map.Entry<String, String>> set=m.entrySet();
                for(Map.Entry<String, String> me:set){
                    System.out.println(me.getKey()+"————"+me.getValue());
                    cr.add(Expression.like(me.getKey(),"%"+me.getValue()+"%"));
                }
            }
            count=cr.list().size();
        }catch(Exception e){
            e.printStackTrace();
        }
        return count;
    }



    //诚意金明细
    public List findPageByDetach3(DetachedCriteria dc,PageObject p){
        List list=new ArrayList();
        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        //清空缓存
        session.clear();
        try{
            Criteria cr=dc.getExecutableCriteria(session);
            cr.setFirstResult(p.getStartRow());
            //设置每页显示记录数
            cr.setMaxResults(p.getPageRow());
            cr.createAlias("ticket","ticket");
            cr.createAlias("buyHourse","buyHourse");
            cr.createAlias("buyHourse.customerInfo","customerInfo");
            cr.createAlias("buyHourse.hourseInfo","hourseInfo");

            cr.setProjection(
                    Projections.projectionList()
                            .add(Projections.property("earnestId"),"earnestId")
                            .add(Projections.property("buyHourse.buyId"),"buyId")
                            .add(Projections.property("ticket.ticketNo"),"ticketNo")
                            .add(Projections.property("ticketFlow"),"ticketFlow")
                            .add(Projections.property("paidtypeid"),"paidtypeid")
                            .add(Projections.property("paidMoney"),"paidMoney")
                            .add(Projections.property("paidTime"),"paidTime")
                            .add(Projections.property("isTranslate"),"isTranslate")
                            .add(Projections.property("transMoney"),"transMoney")
                            .add(Projections.property("status"),"status")
                            .add(Projections.property("userid"),"userid")
                            .add(Projections.property("oprtime"),"oprtime")
                            .add(Projections.property("invalid"),"invalid")

                            .add(Projections.property("customerInfo.custname"),"custname")
                            .add(Projections.property("hourseInfo.hourseName"),"hourseName")

            );
            cr.addOrder(Order.asc("earnestId"));
            // 将多属性投影查询映射成一个Map对象
            cr.setResultTransformer(cr.ALIAS_TO_ENTITY_MAP);
            list=cr.list();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    //多条件模糊查询
    public List findByCondition(DetachedCriteria dc, Map m,PageObject pager){
        List list=null;
        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        //清空缓存
        session.clear();
        try{
            Criteria cr = dc.getExecutableCriteria(session);
            Set<Map.Entry<String, String>> set=m.entrySet();
            for(Map.Entry<String, String> me:set){
                System.out.println(me.getKey()+"————"+me.getValue());
                cr.add(Expression.like(me.getKey(),"%"+me.getValue()+"%"));
            }
            cr.setFirstResult(pager.getStartRow());
            //每页显示记录数
            cr.setMaxResults(pager.getPageRow());
            list = cr.list();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    //条件查询获取记录数
    public int findGetCount(DetachedCriteria dc, Map m){
        int count=0;
        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        //清空缓存
        session.clear();
        try{
            Criteria cr = dc.getExecutableCriteria(session);
            Set<Map.Entry<String, String>> set=m.entrySet();
            for(Map.Entry<String, String> me:set){
                System.out.println(me.getKey()+"————"+me.getValue());
                cr.add(Expression.like(me.getKey(),"%"+me.getValue()+"%"));
            }
            count=cr.list().size();
        }catch(Exception e){
            e.printStackTrace();
        }
        return count;
    }
    public int EarnestCount(DetachedCriteria dc){
        int count=0;
        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        //清空缓存
        session.clear();
        try{
            Criteria cr=dc.getExecutableCriteria(session);
            cr.createAlias("ticket","ticket");
            cr.createAlias("buyHourse","buyHourse");
            cr.setProjection(
                    Projections.projectionList()
                            .add(Projections.property("earnestId"),"earnestId")
                            .add(Projections.property("buyHourse.buyId"),"buyId")
                            .add(Projections.property("ticket.ticketNo"),"ticketNo")
                            .add(Projections.property("ticketFlow"),"ticketFlow")
                            .add(Projections.property("paidtypeid"),"paidtypeid")
                            .add(Projections.property("paidMoney"),"paidMoney")
                            .add(Projections.property("paidTime"),"paidTime")
                            .add(Projections.property("isTranslate"),"isTranslate")
                            .add(Projections.property("transMoney"),"transMoney")
                            .add(Projections.property("status"),"status")
                            .add(Projections.property("userid"),"userid")
                            .add(Projections.property("oprtime"),"oprtime")
                            .add(Projections.property("invalid"),"invalid")

            );
            cr.addOrder(Order.asc("buyId"));

            // 将多属性投影查询映射成一个Map对象
            cr.setResultTransformer(cr.ALIAS_TO_ENTITY_MAP);

            count=cr.list().size();
        }catch(Exception e){
            e.printStackTrace();
        }
        return count;
    }



}
