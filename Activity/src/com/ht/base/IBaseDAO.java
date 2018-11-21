package com.ht.base;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface IBaseDAO {
	public void save(Object obj);
	public void update(Object obj);
	public void delete(Object obj);
	public void saveOrUpdate(Object obj);
	public Object findObjById(Class clz,Serializable oid);
	public int getCount(DetachedCriteria dc);
	public List findByDetach(DetachedCriteria dc);
	public List findPageByDetach(DetachedCriteria dc,PageObject pager);
}
