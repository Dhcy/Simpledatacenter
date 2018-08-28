package com.good.plat.datacenter.common.base;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Validator {

	public Validator() {
		// TODO Auto-generated constructor stub
	}
	public static boolean isNullOrBlank(String str){
		return (str == null ? true : "".equals(str.trim()) ) ;
	}
	public static boolean isNull(Object o) {
		return o == null ;
	}
	public static Integer null2zero(Integer n) {
		return n == null ? 0 : n;
	}
	public static Double null2zero(Double n) {
		return n == null ? 0 : n;
	}
	public static Integer toInteger(String v) {
		try{
			return Integer.valueOf(v);
		}catch(NumberFormatException e){
			
		}
		return null;
	}
	//校验字符串为空
	public static boolean str_isEmpty(String str){
		if(str==null||"".equals(str))
			return true;
		return false;
	}
	//校验字符串是否为指定格式日期
	 public static boolean isValidDate(String str,String formatStr) {
	        boolean convertSuccess=true;
	         SimpleDateFormat format = new SimpleDateFormat(formatStr);
	        try {
	            format.parse(str);
	         } catch (ParseException e) {
	           // e.printStackTrace();
	 // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
	            convertSuccess=false;
	        } 
	        return convertSuccess;
		 }
	//金额验证  
	 public static boolean isNumber(String str){   
	      Pattern pattern=Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式  
	      Matcher match=pattern.matcher(str);   
	      if(match.matches()==false){   
	         return false;   
	      }else{   
	         return true;   
	      }   
	  }  
}
