package com.good.plat.datacenter.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DateUtil {

	/**
	 * 获得某天所在周的第一天
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.SUNDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	
	/**
	 * 取得某天所在周的最后一天 
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.SUNDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	} 
	
	/**
	 * 取得某天相加(减)後的那一天
	 * @param date
	 * @param num(可正可负)
	 * @return
	 */
	public static Date getAnotherDate(Date date, int num) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_YEAR, num);
		return c.getTime();
	}
	
	/**
	 * 取得某天相加(减)後的那一天
	 * @param date
	 * @param num(可正可负)
	 * @return
	 */
	public static String getStrAnotherDate(Date date, int num) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_YEAR, num);
		return sdf.format(c.getTime());
	}
	

	/**
	 * 取得某天所在月的的第一天
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getFirstDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, 1);// 日，设为一号
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	/**
	 * 取得某天所在月的的最后一天
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getLastDayOfMonth(Date date, boolean isHead) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, 1);// 日，设为一号
		cal.add(Calendar.MONTH, 1);// 月份加一，得到下个月的一号
		cal.add(Calendar.DATE, -1);// 下一个月减一为本月最后一天
		if(isHead){
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
		}else{
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			cal.set(Calendar.MILLISECOND, 0);
		}
		return cal.getTime();
	}
	

	/**
	 * 取得某天是一年中的多少周
	 * @param date
	 * @return
	 */
	public static int getWeekOfYear(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.SUNDAY);
		c.setMinimalDaysInFirstWeek(7);
		c.setTime(date);
		return c.get(Calendar.WEEK_OF_YEAR);
	}
	
	/**  
	* @param date1 需要比较的时间 不能为空(null),需要正确的日期格式 ,如：2009-09-12 
	* @param date2 被比较的时间  为空(null)则为当前时间  
	* @param stype 返回值类型   0为多少天，1为多少个月，2为多少年  
	* @return  
	* 举例：
	* compareDate("2009-09-12", null, 0);//比较天
	* compareDate("2009-09-12", null, 1);//比较月
	* compareDate("2009-09-12", null, 2);//比较年
	*/ 
	public static int compareDate(String startDay, String endDay, int stype) {
		int n = 0;
		String[] u = { "天", "月", "年" };
		String formatStyle = stype == 1 ? "yyyy-MM" : "yyyy-MM-dd";

		endDay = endDay == null ? getCurrentDate("yyyy-MM-dd") : endDay;

		DateFormat df = new SimpleDateFormat(formatStyle);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		try {
			c1.setTime(df.parse(startDay));
			c2.setTime(df.parse(endDay));
		} catch (Exception e3) {
			System.out.println("wrong occured");
		}
		// List list = new ArrayList();
		while (!c1.after(c2)) { // 循环对比，直到相等，n 就是所要的结果
			// list.add(df.format(c1.getTime())); // 这里可以把间隔的日期存到数组中 打印出来
			n++;
			if (stype == 1) {
				c1.add(Calendar.MONTH, 1); // 比较月份，月份+1
			} else {
				c1.add(Calendar.DATE, 1); // 比较天数，日期+1
			}
		}
		n = n - 1;
		if (stype == 2) {
			n = n / 365;
		}
		System.out.println(startDay + " -- " + endDay + " 相差多少" + u[stype]
				+ ":" + n);
		return n;
	}
	
	
	public static String getCurrentDate(String format) {
		Calendar day = Calendar.getInstance();
		day.add(Calendar.DATE, 0);
		SimpleDateFormat sdf = new SimpleDateFormat(format);// "yyyy-MM-dd"
		String date = sdf.format(day.getTime());
		return date;
	} 
	
	
	public static List<Date> getDateInterval(Date startdate, Date enddate) {
		List<Date> dateList = new ArrayList<Date>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(startdate);
		try {
			startdate = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if (dateList.size()==0) {
			dateList.add(startdate);
		}
		
		Calendar cal = Calendar.getInstance();
		while (startdate.before(enddate)) {
			cal.setTime(startdate);
			cal.add(Calendar.DAY_OF_YEAR, 1);
			startdate = cal.getTime();
			dateList.add(startdate);
		}
		return dateList;
	}
	
	
	public static List<String> getDateInterval(String sdate, String edate) {
		List<String> dateList = new ArrayList<String>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//String str = sdf.format(startdate);
		Date startdate = null;
		Date enddate = null;
		
		try {
			startdate = sdf.parse(sdate);
			enddate = sdf.parse(edate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		sdate=sdf.format(startdate);
		if (dateList.size()==0) {
			dateList.add(sdate);
		}
		
		Calendar cal = Calendar.getInstance();
		while (startdate.before(enddate)) {
			cal.setTime(startdate);
			cal.add(Calendar.DAY_OF_YEAR, 1);
			startdate = cal.getTime();
			dateList.add(sdf.format(startdate));
		}
		return dateList;
	}
	
	public static String isTrueDate(Date startdate, Date enddate){
		long start = startdate.getTime();
		long end = enddate.getTime();
		long lo = end - start;
		if (lo < 0) {
			return "输入的结束日期不能小于开始日期";
		}else if(lo > 7*24*3600*1000){
			return "开始日期与结束日期时间间隔不能超过7天";
		}
		return null;
	}
	
	
	public static String isTrueDate(Date startdate, Date enddate, int in){
		long start = startdate.getTime();
		long end = enddate.getTime();
		long lo = end - start;
		if (lo < 0) {
			return "输入的结束日期不能小于开始日期";
		}else if(lo > (in*24*3600*1000l)){
			return "开始日期与结束日期时间间隔不能超过"+in+"天";
		}
		return null;
	}
	
	public static Date parseDate(String date,String pattern) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.parse(date);
	}

	public static Date parsetDate_yyyy_MM_dd_HH_mm_ss(String date) throws ParseException {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		return parseDate(date,pattern);
	}
	
}
