package com.ht.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

public interface IBaseDAO {
	public void save(Object obj);
	public void update(Object obj);
	public void delete(Object obj);
	public void saveOrUpdate(Object obj);
	public Object findObjById(Class clz, Serializable oid);
	public Number getCount(DetachedCriteria dc);
	public Object findclass(DetachedCriteria dc);
    /*public int getCount1(DetachedCriteria dc);*/
	public List findByDetach(DetachedCriteria dc);
	public List findPageByDetach(DetachedCriteria dc, PageObject pager);
    public List findPageByDetached(DetachedCriteria paramDetachedCriteria, int paramInt1, int paramInt2);
    public List findByDetached(DetachedCriteria dc1);
    public Object findObjByID(Class clazz, Serializable id);
    public List findByConditionfz(DetachedCriteria dc, Map m, PageObject pager);
    public int TerminFocount(DetachedCriteria dc,Map m);
    public  List TerminFolist(DetachedCriteria dc, PageObject p,Map m);
    public int EarnestCount(DetachedCriteria dc);
    /*public int getCount2(DetachedCriteria dc);*/
}
