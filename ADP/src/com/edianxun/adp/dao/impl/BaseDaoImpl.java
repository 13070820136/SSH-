package com.edianxun.adp.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.edianxun.adp.dao.BaseDao;

/**
 * @author lhr
 * @ date 2014-4-21
 * @param <T>
 */
public class BaseDaoImpl<T>extends HibernateDaoSupport implements BaseDao<T> {
	//继承HibernateDaoSupport只是利用了它提供的setSessionFactory(),为spring注入所用
	@Override
	public T get(Class<T> entityClass, Serializable id) {
		return (T) getSessionFactory().getCurrentSession().get(entityClass, id);
	}

	@Override
	public Serializable save(T entity) {
		return getSessionFactory().getCurrentSession().save(entity);
	}

	@Override
	public void update(T entity) {
		getSessionFactory().getCurrentSession().saveOrUpdate(entity);
	}

	@Override
	public void delete(T entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	@Override
	public void delete(Class<T> entityClass, Serializable id) {
		delete(get(entityClass, id));
	}

	@Override
	public List<T> findAll(Class<T> entityClass) {
		return find("select en from " + entityClass.getSimpleName() + " en");
	}
	
	protected List<T> find(String hql){
		return getSessionFactory().getCurrentSession().createQuery(hql).list();
	}
	
	protected List<T> find(String hql, Object...params){
		//创建查询
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		//为包占位符的HQL语句设置参数
		for (int i = 0, len = params.length; i < len; i++) {
			query.setParameter(i + "", params[i]);//i+"" 将i转为String型
		}
		return query.list();
	}

	@Override
	public Long allTotal(Class<T> entityClass) {
		Long total= (Long) getSessionFactory().getCurrentSession().createCriteria(entityClass)
				.setProjection(Projections.rowCount()).uniqueResult();
		return total;
	}

}
