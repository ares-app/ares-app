package org.ares.app.common.repository.mybatis.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class CommonDaoImp<T extends Object> extends SqlSessionDaoSupport implements CommonDao<T> {

	public boolean add(String classMethod, T entity) {
		return this.getSqlSession().insert(classMethod, entity)>0;
	}

	public boolean edit(String classMethod, T entity) {
		return this.getSqlSession().update(classMethod, entity)>0;
	}

	public boolean remove(String classMethod, T entity) {
		return this.getSqlSession().delete(classMethod, entity)>0;
	}

	public T get(String classMethod, T entity) {
		return this.getSqlSession().selectOne(classMethod,entity);
	}

	public List<T> getAll(String classMethod) {
		return this.getSqlSession().selectList(classMethod);
	}
	
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@SuppressWarnings("unused")
	private SqlSessionFactory sqlSessionFactory;

}
