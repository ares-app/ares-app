package org.ares.app.common.filter.cache;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class IgnoreBrowseCacheFilter implements Filter {

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response=(HttpServletResponse)res;
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, post-check=0, pre-check=0");// HTTP/1.1 + IE extensions
		response.setHeader("Pragma", "no-cache");// HTTP/1.0
		response.setHeader("Expires", "-1");// Last resort for those that ignore all of the above
		chain.doFilter(request, res);
	}

	@Override
	public void init(FilterConfig cfg) throws ServletException {

	}

}
