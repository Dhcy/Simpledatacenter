package com.good.plat.datacenter.login.auth;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.good.plat.datacenter.common.util.MD5;
import com.good.plat.datacenter.login.user.entity.SecondMenu;
import com.good.plat.datacenter.login.user.entity.User;
import com.good.plat.datacenter.login.user.service.impl.SecondMenuServiceImpl;
import com.good.plat.datacenter.login.user.service.impl.UserServiceImpl;

public class ShiroDataBaseRealm extends AuthorizingRealm {
	
	private static final Logger logger = LoggerFactory.getLogger(ShiroDataBaseRealm.class);

	@Autowired
	private SecondMenuServiceImpl secondMenuService;

	@Autowired
	private UserServiceImpl userService;

	public ShiroDataBaseRealm() {
		setName("ShiroDataBaseRealm");
		setCredentialsMatcher(new AllowAllCredentialsMatcher());
	}

	/**
	 * 当用户进行访问链接时的授权方法
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		try {
			if (principals == null) {
				throw new AuthorizationException("Principal对象不能为空");
			}

			Iterator<?> its = principals.fromRealm(getName()).iterator();

			if (its.hasNext() == false) {
				throw new AuthorizationException("找不到User啊，怎么没有登录？");
			}

			User user = (User) its.next();

			// 获取权限
			List<String> permissions = new ArrayList<String>();
			StringBuffer sb = new StringBuffer();
			if (user.getVocation().equals("super")) {// 超级管理员做一个特权校验
				List<SecondMenu> perms = secondMenuService
						.findSecondMenuList(new SecondMenu());
				for (SecondMenu permission : perms) {
					String p = permission.getPermission();
					permissions.add(p);
					sb.append(p + ";");
				}
			} else {
				List<SecondMenu> perms = secondMenuService
						.findSecondMenuListByUserID(user.getId());
				for (SecondMenu permission : perms) {
					String p = permission.getPermission();
					permissions.add(p);
					sb.append(p + ";");
				}
			}

			// 打印
			// System.out.println("######## doGetAuthorizationInfo username="+user.getUsername()+" #########");
			System.out.println("-------------------------------");
			System.out.println(sb);

			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

			info.addStringPermissions(permissions);

			return info;
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	/**
	 * 用户登录的认证方法
	 * 
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		
		String username = usernamePasswordToken.getUsername();
		
		if (username == null) {
			throw new AccountException("用户名不能为空");
		}
		//出现用户名不存在时,用下面这个查询看报什么错误
		/*try{
			userService.userList(new User());
		}catch(Exception e){
			e.printStackTrace();
		}*/
		User user = null;
		try {
			user = userService.findUser(new User(username));
		} catch (Exception e) {
			throw new UnknownAccountException("用户不存在");
		}
		
		if (user == null) {
			throw new UnknownAccountException("用户不存在");
		}
		
		String password = String.valueOf(usernamePasswordToken.getPassword());
		password = MD5.toMD5(password).toUpperCase();
		
		if (!user.getPassword().equals(password)) {
			throw new AccountException("用户密码错误");
		}
		
		//修改时间
		user.setLogindate(new Date());
		try {
			userService.updataUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
		
	}
	
}
