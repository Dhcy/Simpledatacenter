package com.good.plat.datacenter.common.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.good.plat.datacenter.sys.entity.log.SysAccessLog;

/**
 * int, double, float, long, short, boolean, byte, char， void
 * Integer,Double,Float,Long,Short,Boolean,Byte,Char
 * 
 * @ClassName: JSONUtil
 * @Description: TODO
 * @author moxw
 * @date 2016年5月6日 上午9:54:35
 */
public class JSONUtil {
	
	public static void main(String[] args) {
		
		SysAccessLog a = new SysAccessLog();
		SysAccessLog b = new SysAccessLog();
		System.out.println(formatJavaObjects2JSONString(a,b));
		BigDecimal bd = new BigDecimal("1");
		System.out.println(formatJavaObjects2JSONString(bd,1));
	}
	
	public static String toJSONString(Object javaObject){
		return fromObjects(javaObject).toJSONString();
	}
	
	public static String formatJavaObjects2JSONString(Object... javaObjects){
		if(javaObjects == null){
			return "";
		}
		return fromObjects(javaObjects).toJSONString();
	}
	
/*	public static JSONObject fromObject(Object javaObject){
		return (JSONObject) JSONObject.toJSON(javaObject);
	}*/
	
	public static JSONArray fromObjects(Object... parameterJavaObject){
		List<Object> list = new LinkedList();
		for(Object o : parameterJavaObject){
			Map<Object,Object> m = new HashMap<Object, Object>();
			m.put(o.getClass().getName(), o);
			list.add(m);
		}
		return (JSONArray) JSONArray.toJSON(list);
	}
	
	/**
	 * 获取json参数(适用于contentType=application/x-www-form-urlencoded)
	 * @param request
	 * @return
	 */
	public static JSONObject Prarm2Json(HttpServletRequest request ){
		Map prarmMap=request.getParameterMap();
		if (prarmMap!=null&&prarmMap.size()>0){
			String prarm=(String) prarmMap.keySet().toArray()[0];
			JSONObject jsonObject =JSONObject.parseObject(prarm);
			return jsonObject;
		}else{
			return null;
		}
		 
	}
	/**
	 * 输出json
	 * @Title: renderJson
	 * @Description: TODO
	 * @param response
	 * @param obj
	 * void
	 * @author hwj
	 * @date 2017-6-2 上午11:23:56
	 */
	public static void renderJson(HttpServletResponse response,JSON obj){
		try {
		   response.setCharacterEncoding("utf-8");
		   response.getWriter().write(JSON.toJSONString(obj));
		} catch (IOException e) {
			 e.printStackTrace();
		}
    	
	}
}
