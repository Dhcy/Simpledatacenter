package com.good.plat.datacenter.common.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class SystemConfig {

	private static Properties logstatconfig = null;

	static {
		InputStream in = SystemConfig.class.getClassLoader().getResourceAsStream("logstat.properties");
		logstatconfig = new Properties();
		try {
			logstatconfig.load(in);
			in.close();
		} catch (IOException e) {
			System.out.println("找不到config.properties");
		}
	}

	// 根据key读取value
	public static String getValue(String key) {
		try {
			String value = new String(logstatconfig.getProperty(key).getBytes("UTF-8"), "UTF-8");
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ConfigInfoError" + e.toString());
			return null;
		}
	}
	
	/*
	 * // 读取properties的全部信息 public static void readAllProperties() { try {
	 * Enumeration en = config.propertyNames(); while (en.hasMoreElements()) {
	 * String key = (String) en.nextElement(); String Property =
	 * config.getProperty(key); System.out.println(key + Property); } } catch
	 * (Exception e) { e.printStackTrace(); System.err.println("ConfigInfoError"
	 * + e.toString()); } }
	 */

	/*public static void main(String[] args) {
		System.out.println(SystemConfig.getValue("nlpz1"));
	}*/
	
	public static String getProValue (String proName, String key) {
		Properties properties = new Properties();
		InputStream in = SystemConfig.class.getClassLoader().getResourceAsStream(proName);
		try {
			properties.load(in);
			in.close();
		} catch (IOException e) {
			System.out.println("找不到"+proName);
			e.printStackTrace();
		}
		
		try {
			String value = new String(properties.getProperty(key).getBytes("UTF-8"), "UTF-8");
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ConfigInfoError" + e.toString());
			return null;
		}
	}
	
}
