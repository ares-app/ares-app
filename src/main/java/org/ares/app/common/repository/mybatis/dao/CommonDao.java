package org.ares.app.common.repository.mybatis.dao;

import java.util.List;

public interface CommonDao<T> {

	public boolean add(String classMethod, T entity);

	public boolean edit(String classMethod, T entity);

	public boolean remove(String classMethod, T entity);

	public T get(String classMethod, T entity);

	public List<T> getAll(String classMethod);

}
