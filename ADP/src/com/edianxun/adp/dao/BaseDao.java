package com.edianxun.adp.dao;

import java.io.Serializable;
import java.util.List;

/**
 * CRUD通用接口
 * @author lhr
 *	@ date 2014-4-21
 * @param <T>
 */
public interface BaseDao<T> {
	//根据Id加载实体
	T get(Class<T> entityClass, Serializable id);
	//保存实体
	Serializable save(T entity);
	//更新实体
	void update(T entity);
	//删除实体
	void delete(T entity);
	//根据ID删除实体
	void delete(Class<T> entityClass, Serializable id);
	//获取所有实体
	List<T> findAll(Class<T> entityClass);
	//获取所有实体数量
	Long allTotal(Class<T> entityClass);
}
