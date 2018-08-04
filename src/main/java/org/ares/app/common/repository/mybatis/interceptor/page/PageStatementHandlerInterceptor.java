package org.ares.app.common.repository.mybatis.interceptor.page;

import static org.ares.app.common.util.BeanCopy.getProperty;
import static org.ares.app.common.util.BeanCopy.setProperty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.statement.PreparedStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.apache.log4j.Logger;
import org.ares.app.common.advice.datastore.WebDataStore;
import org.ares.app.common.page.PageModel;
import org.ares.app.common.page.dialect.Dialect;
import org.ares.app.common.util.BeanCopy;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Intercepts( {@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class})})
public class PageStatementHandlerInterceptor implements Interceptor {

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		
		if(dataStore.getCurrentPage()==null)//first read code,no request
			 return invocation.proceed();
		
		RoutingStatementHandler statement = (RoutingStatementHandler)invocation.getTarget();
        PreparedStatementHandler handler = (PreparedStatementHandler)BeanCopy.getProperty(statement,"delegate");
        RowBounds rowBounds = (RowBounds)getProperty(handler,"rowBounds");
        BoundSql boundSql = statement.getBoundSql();
        String sql = boundSql.getSql();
        log.debug("[source sql]--"+sql);
        PageModel page=dataStore.getCurrentPage();
        if(dialect.isSelectSql(sql)){
        	int total=getCount((Connection)invocation.getArgs()[0],statement,dialect.getCountSql(sql));
        	page.refresh(total); //total
        	if(page.isFirstQuery()){
        		page.first();
        		rowBounds=new RowBounds(0,page.getPageSize());
        	}
    		else{
    			page.gotoPage(Integer.parseInt(page.getPageNumber()));
    			rowBounds=new RowBounds(page.getStart(), page.getEnd());
    		}
        	setProperty(handler, "rowBounds", rowBounds);
        }
        
        if (rowBounds.getLimit() > 0 && rowBounds.getLimit() < RowBounds.NO_ROW_LIMIT){
            sql = boundSql.getSql();
            String pageSql = dialect.getLimitString(sql, rowBounds.getOffset(),rowBounds.getLimit());//page sql
            log.debug("[page sql]--"+pageSql);
            if(!sql.equals(pageSql))
            	setProperty(boundSql, "sql", pageSql);
        }
        return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		Object obj= Plugin.wrap(target, this);
		return obj;
	}
	
	private int getCount(Connection con,RoutingStatementHandler statementHandler, String countSql)throws Exception {
		int r = -1;
		PreparedStatementHandler delegate = (PreparedStatementHandler) BeanCopy.getProperty(statementHandler, "delegate");
		MappedStatement mappedStatement = (MappedStatement) BeanCopy.getProperty(delegate, "mappedStatement");
		BoundSql boundSql = delegate.getBoundSql();
		Object parameterObject = boundSql.getParameterObject();
		PreparedStatement countStmt = con.prepareStatement(countSql);
		BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(),
				countSql, boundSql.getParameterMappings(), parameterObject);
		setParameters(countStmt, mappedStatement, countBS, parameterObject);
		ResultSet rs = countStmt.executeQuery();
		if (rs.next()) {
			r = rs.getInt(1);
		}
		rs.close();
		countStmt.close();
		return r;
	}
	
	private void setParameters(PreparedStatement ps,MappedStatement mappedStatement, BoundSql boundSql,Object parameterObject) throws SQLException {
		ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		if (parameterMappings != null) {
			Configuration configuration = mappedStatement.getConfiguration();
			TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
			MetaObject metaObject = parameterObject == null ? null: configuration.newMetaObject(parameterObject);
			for (int i = 0; i < parameterMappings.size(); i++) {
				ParameterMapping parameterMapping = parameterMappings.get(i);
				if (parameterMapping.getMode() != ParameterMode.OUT) {
					Object value;
					String propertyName = parameterMapping.getProperty();
					PropertyTokenizer prop = new PropertyTokenizer(propertyName);
					if (parameterObject == null) {
						value = null;
					} else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
						value = parameterObject;
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						value = boundSql.getAdditionalParameter(propertyName);
					} else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX) && boundSql.hasAdditionalParameter(prop.getName())) {
						value = boundSql.getAdditionalParameter(prop.getName());
						if (value != null) {
							value = configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));
						}
					} else {
						value = metaObject == null ? null : metaObject.getValue(propertyName);
					}
					TypeHandler typeHandler = parameterMapping.getTypeHandler();
					if (typeHandler == null) {
						throw new ExecutorException("There was no TypeHandler found for parameter "+ propertyName + " of statement "+ mappedStatement.getId());
					}
					typeHandler.setParameter(ps, i + 1, value,parameterMapping.getJdbcType());
				}
			}
		}
	}
	
	public void setDialect(Dialect dialect) {
		this.dialect = dialect;
	}
	
	public void setDataStore(WebDataStore dataStore) {
		this.dataStore = dataStore;
	}

	@Override
	public void setProperties(Properties properties) {
		
	}
	
	private Dialect dialect;
	private WebDataStore dataStore;
	static Logger log=Logger.getLogger(PageStatementHandlerInterceptor.class);

}
