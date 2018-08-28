package com.good.plat.datacenter.common.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class PropertiesUtil {
	public static String config_path = "config.properties";
	public static Properties props;
	static{
		Resource resource = new ClassPathResource(config_path);
		try {
			props = PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String getValue(String key){
		return props.getProperty(key);
	}
	
	public static void reLoad(){
		try {
			String path = PropertiesUtil.class.getClassLoader().getResource(config_path).getPath();  
			InputStream is = new FileInputStream(path);  
			props = new Properties();
			props.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
