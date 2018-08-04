package org.ares.app.common.repository.mybatis.mapper;

import java.util.List;

public interface CommonSqlMapper<T> extends SqlMapper {
	
	public boolean add(T entity);
    
    public boolean edit(T entity);
    
    public boolean remvoe(T entity);
    
    public T get(T entity);
    
    public List<T> getList(T entity);
    
}
