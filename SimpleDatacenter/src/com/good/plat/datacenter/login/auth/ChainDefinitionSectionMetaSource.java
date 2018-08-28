package com.good.plat.datacenter.login.auth;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.good.plat.datacenter.login.user.entity.SecondMenu;
import com.good.plat.datacenter.login.user.service.impl.SecondMenuServiceImpl;

@SuppressWarnings("rawtypes")
public class ChainDefinitionSectionMetaSource implements FactoryBean {

	private String filterChainDefinitions;

	@Autowired
	//@Qualifier(value = "SecondMenuServiceImpl")
	private SecondMenuServiceImpl secondMenuService;

	/**
	 * 默认premission字符串
	 */
	public static final String PREMISSION_STRING = "perms[\"{0}\"]";

	@Override
	public Section getObject() throws BeansException {
    	try {
    		 //获取所有Resource
            List<SecondMenu> list = secondMenuService.findSecondMenuList(new SecondMenu());

            Ini ini = new Ini();
            //加载默认的url
            ini.load(filterChainDefinitions);
            Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
            //循环Permission的url,逐个添加到section中。section就是filterChainDefinitionMap,
            //里面的键就是链接URL,值就是存在什么条件才能访问该链接
            
            for (Iterator<SecondMenu> it = list.iterator(); it.hasNext();) {

            	SecondMenu permission = it.next();
                //如果不为空值添加到section中
                if(StringUtils.isNotEmpty(permission.getUrl()) && StringUtils.isNotEmpty(permission.getPermission())) {
                    section.put(permission.getUrl(),  MessageFormat.format(PREMISSION_STRING, permission.getPermission()));
                }
                
            }
            
            return section;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
       
    }

	/**
	 * 通过filterChainDefinitions对默认的url过滤定义
	 * 
	 * @param filterChainDefinitions
	 *            默认的url过滤定义
	 */
	public void setFilterChainDefinitions(String filterChainDefinitions) {
		this.filterChainDefinitions = filterChainDefinitions;
	}

	@Override
	public Class<?> getObjectType() {
		return this.getClass();
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

}
