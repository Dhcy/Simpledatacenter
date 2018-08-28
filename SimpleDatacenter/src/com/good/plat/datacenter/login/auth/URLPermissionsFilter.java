package com.good.plat.datacenter.login.auth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;


public class URLPermissionsFilter extends PermissionsAuthorizationFilter{
	
	
	/**
	 *@param mappedValue 指的是在声明url时指定的权限字符串，如/User/create.do=perms[User:create].我们要动态产生这个权限字符串，所以这个配置对我们没用
	 */
	@Override
	public boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws IOException {
		 return super.isAccessAllowed(request, response, buildPermissions(request,mappedValue));
	}
	/**
	 * 根据请求URL产生权限字符串，这里只产生，而比对的事交给Realm
	 * @param request
	 * @return
	 */
	protected String[] buildPermissions(ServletRequest request,Object mappedValue) {
		
		List<String> permissions = new ArrayList<String>();
		
		//HttpServletRequest req = (HttpServletRequest) request;
		
		String[] mv = (String[])mappedValue;
		for(String s:mv){
			permissions.add(s);
		}
		
		
		String[] perms = new String[permissions.size()];
		for(int i=0;i<permissions.size();i++){
			perms[i]=permissions.get(i);
		}
		
		return perms;
	}
	
	
}
