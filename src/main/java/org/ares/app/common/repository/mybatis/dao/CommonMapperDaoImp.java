package org.ares.app.common.repository.mybatis.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSessionFactory;
import org.ares.app.common.repository.mybatis.mapper.CommonSqlMapper;
import org.mybatis.spring.SqlSessionTemplate;

@SuppressWarnings({"rawtypes","unchecked"})
public class CommonMapperDaoImp<T> extends SqlSessionTemplate implements CommonMapperDao<T> {

	@Inject
    public CommonMapperDaoImp(SqlSessionFactory sqlSessionFactory) {
        super(sqlSessionFactory);
    }
	
	public void setMapperClass(Class<? extends CommonSqlMapper> mapperClass) {
		this.mapperClass=mapperClass;		
	}
	
	private CommonSqlMapper<T> getMapper() {
        return this.getMapper(mapperClass);
    }

	public boolean add(T entity) {
		return this.getMapper().add(entity);
	}

	public boolean edit(T entity) {
		return this.getMapper().edit(entity);
	}

	public boolean remove(T entity) {
		return this.getMapper().remvoe(entity);
	}

	public T get(T entity) {
		return this.getMapper().get(entity);
	}

	public List<T> getList() {
		return this.getMapper().getList(null);
	}
	
	private Class<? extends CommonSqlMapper> mapperClass;
	
}
