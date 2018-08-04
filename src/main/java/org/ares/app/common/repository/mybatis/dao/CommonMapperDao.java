package org.ares.app.common.repository.mybatis.dao;

import java.util.List;

import org.ares.app.common.repository.mybatis.mapper.CommonSqlMapper;


public interface CommonMapperDao<T> {

@SuppressWarnings("rawtypes")
public void setMapperClass(Class<? extends CommonSqlMapper> mapperClass);
    
    public boolean add(T entity);
    
    public boolean edit(T entity);
    
    public boolean remove(T entity);
    
    public T get(T entity);
    
    public List<T> getList();
    
}
