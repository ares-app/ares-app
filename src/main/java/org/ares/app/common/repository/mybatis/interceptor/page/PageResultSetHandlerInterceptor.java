package org.ares.app.common.repository.mybatis.interceptor.page;

import static org.ares.app.common.util.BeanCopy.getProperty;
import static org.ares.app.common.util.BeanCopy.setProperty;

import java.sql.Statement;
import java.util.Properties;

import org.apache.ibatis.executor.resultset.DefaultResultSetHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.RowBounds;

@Intercepts({ @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = { Statement.class }) })
public class PageResultSetHandlerInterceptor implements Interceptor {

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		DefaultResultSetHandler resultSet = (DefaultResultSetHandler) invocation.getTarget();
		RowBounds rowBounds = (RowBounds) getProperty(resultSet,"rowBounds");
		if (rowBounds.getLimit() > 0 && rowBounds.getLimit() < RowBounds.NO_ROW_LIMIT) {
			setProperty(resultSet, "rowBounds", new RowBounds());
		}
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties props) {

	}

}
