package com.good.plat.datacenter.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResponseHeaderFilter implements Filter{
	
	private static final Logger logger = LoggerFactory.getLogger(ResponseHeaderFilter.class);

	public ResponseHeaderFilter() {
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse rep = (HttpServletResponse) response;
		rep.setContentType("text/html;charset=UTF-8");
		//'*'表示允许所有域名访问，可以设置为指定域名访问，多个域名中间用','隔开
		rep.addHeader("Access-Control-Allow-Origin","*");
		if("IE".equals(request.getParameter("type"))){
			rep.addHeader("XDomainRequestAllowed","1");
	    }
		String strUrl = req.getServletPath().toString();
		
		logger.info("---访问链接---:"+strUrl);
		filterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
