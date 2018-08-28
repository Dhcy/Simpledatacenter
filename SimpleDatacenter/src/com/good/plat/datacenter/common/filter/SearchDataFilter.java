package com.good.plat.datacenter.common.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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

import com.alibaba.fastjson.JSONObject;
import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.game.entity.Channel;

public class SearchDataFilter implements Filter {

	private static final Logger logger = LoggerFactory
			.getLogger(SearchDataFilter.class);

	public SearchDataFilter() {
	}

	@Override
	public void destroy() {

	}

	@SuppressWarnings({"unchecked" })
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse rep = (HttpServletResponse) response;

		ServletRequest requestWrapper = null;
		if (request instanceof HttpServletRequest) {
			requestWrapper = new BodyReaderHttpServletRequestWrapper(
					(HttpServletRequest) request);
		}

		String message = "";
		List<Integer> channelids = new ArrayList<Integer>();
		if (req.getSession().getAttribute("RESOURCE_CHANNEL") == null) {
			message = "你貌似木有登录吧,别瞎搞了";

			rep.setStatus(500);
			rep.setContentType("application/json;charset=utf-8");
			PrintWriter writer = rep.getWriter();
			writer.write("{\"message\":\"" + message + "\"}");
			writer.flush();
		} else {
			List<Channel> userChannel = (List<Channel>) req.getSession()
					.getAttribute("RESOURCE_CHANNEL");
			for (Channel channel : userChannel) {
				channelids.add(channel.getId());
			}

			StringBuffer jb = new StringBuffer();
			String line = null;
			try {
				BufferedReader reader = requestWrapper.getReader();
				while ((line = reader.readLine()) != null)
					jb.append(line);
			} catch (Exception e) {
				logger.debug(e.getMessage());
			}
			
			JSONObject json = JSONObject.parseObject(jb.toString());
			BaseSearchData data = JSONObject.toJavaObject(json, BaseSearchData.class);
			if(data != null){
				List<Channel> channelList = data.getChannelList();
				if (channelList != null) {
					for (Channel channel : channelList) {
						if (!channelids.contains(channel.getId())) {
							message = "你没有这个渠道的权限啊";

							rep.setStatus(500);
							rep.setContentType("application/json;charset=utf-8");
							PrintWriter writer = rep.getWriter();
							writer.write("{\"message\":\"" + message + "\"}");
							writer.flush();
						}
					}
				}
			}
		}

		String strUrl = req.getServletPath().toString();

		logger.info("---访问链接---:" + strUrl);
		if (null == requestWrapper) {
			filterChain.doFilter(request, response);
		} else {
			filterChain.doFilter(requestWrapper, response);
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
