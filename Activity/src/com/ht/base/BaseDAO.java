package com.ht.base;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/*
 * HibernateDaoSupport类里面已经实现了Hibernate所有的方法
 * 
 * */
@Repository
public class BaseDAO extends HibernateDaoSupport implements IBaseDAO {
	// 注入会话工厂对象
	@Resource
	public void setSF(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	//新增
	@Transactional
	public void save(Object obj){
		getHibernateTemplate().save(obj);
	}
	//修改
	@Transactional
	public void update(Object obj){
		getHibernateTemplate().update(obj);
	}
	//删除
	@Transactional
	public void delete(Object obj){
		getHibernateTemplate().delete(obj);
	}
	//新增或修改
	@Transactional
	public void saveOrUpdate(Object obj){
		getHibernateTemplate().saveOrUpdate(obj);
		
	}
	

	//查询一条记录
	@Transactional
	public Object findObjById(Class clz,Serializable oid){
		
		return getHibernateTemplate().get(clz, oid);
		
	}
	//使用QBC的离线查询，实现统一的统计记录数函数
	@Transactional
	public int getCount(DetachedCriteria dc){
		int count = 0;
		try{
			//清空session
			getHibernateTemplate().getSessionFactory().getCurrentSession().clear();
			Criteria cr = dc.getExecutableCriteria(getHibernateTemplate().getSessionFactory().getCurrentSession());
			count =  (Integer)cr.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
		
	}
	//使用QBC的离线查询，实现统一的列表查询函数
	@Transactional
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
	//使用QBC的离线查询，实现统一的分页列表查询函数
	@Transactional
	public List findPageByDetach(DetachedCriteria dc,PageObject pager){
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
}
