package com.good.plat.datacenter.common.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.good.plat.datacenter.common.base.Validator;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;


public class LoggerUtil {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Properties props;
	private String resourceName;
	
	
	public String getResourceName() {
		return resourceName;
	}



	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}



	public Properties load(String path) throws IOException {
		Resource resource = new ClassPathResource(path);
		Properties props = PropertiesLoaderUtils.loadProperties(resource);
		logger.debug("Logger resource: {}", props );
		return props;
	}
	
	

	public LoggerUtil() {
		super();
	}



	public static void main(String args[]) throws IOException{
		FileInputStream fis = new FileInputStream("config/logOperationDesc.properties");
		Properties prop = new Properties();
		prop.load(fis);
		System.out.println(prop.get("com.good.plat.datacenter.datastat.controller.channels.ChannelDataController.selectPartnerAmountMap"));
		
		//
		Resource resource = new ClassPathResource("logOperationDesc.properties");
		Properties props = PropertiesLoaderUtils.loadProperties(resource);
		System.out.println(props.get("test"));
	}
	
	public static String formatMethod(Class cls,String metdhoName){
		return cls.getName() + "." + metdhoName;
	}
	
	public static Object getLoginUser(HttpSession session){
		return (Integer) session.getAttribute("USERID");
	}
	public String getValue(String key){
		if(props == null){
			try {
				props = load(resourceName);
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
		return props.getProperty(key);
	}
	public static String getValue(HttpServletRequest request,String key){
	
		ServletContext servletCtx = request.getServletContext();
		WebApplicationContext webappctx = WebApplicationContextUtils.getRequiredWebApplicationContext(servletCtx);
		
		return webappctx.getMessage(key, null,Locale.getDefault());
	}
	
	public static SysAccessLog initNewInstance(HttpServletRequest request,Class cls,String methodName,String op_desc,Date op_date,Date op_returnDate,Object ...parameterObjects){
		HttpSession session = request.getSession();
		SysAccessLog accessLog = new SysAccessLog();
		accessLog.setOp_time(op_date);
		if(Validator.isNullOrBlank(op_desc)){
			op_desc = LoggerUtil.formatMethod(cls, methodName);
		}
		accessLog.setOp_desc(op_desc);
		accessLog.setOperator_ip(HTTPUtil.getIpAddr(request));
		accessLog.setProcess_method(LoggerUtil.formatMethod(cls, methodName));
		accessLog.setProcess_method_params(JSONUtil.formatJavaObjects2JSONString(parameterObjects));
		accessLog.setReturn_time(op_returnDate);
		accessLog.setRequest_url(HTTPUtil.getRequestURL(request));
		Integer userkey = (Integer) LoggerUtil.getLoginUser(session);
		accessLog.setUser_id(userkey.toString());
		return accessLog;
	}
}
