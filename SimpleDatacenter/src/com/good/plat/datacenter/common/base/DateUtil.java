package com.good.plat.datacenter.common.base;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

public class DateUtil {
	public static String ALL_DAY_TIME = "23:59:59";
	public static int FIRST_DAY_IN_WEEK_VALUE = 1;
	public static int DAYS_OF_WEEK = 7;
	public static SimpleDateFormat sdf_day = new SimpleDateFormat("yyyy-MM-dd");
	public DateUtil() {
		// TODO Auto-generated constructor stub
	}
	public static Date beginingOfDay(Date day){
		Calendar cal = Calendar.getInstance();
		cal.setTime(day);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	public static Date endingOfDay(Date day){
		Calendar cal = Calendar.getInstance();
		cal.setTime(day);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}
	
	public static String format2YYYY_MM_DD(Date day){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
		return sdf.format(day);
	}
	public static Long daysCount(Date d1,Date d2){
		Long daysCount = 0L;
		if(d1 != null){
			daysCount = 1L;
			if(d2 != null){
				if(d1.after(d2)){
					Date d = d2;
					d2 = d1;
					d1 = d;
				}
				daysCount = (d2.getTime() - d1.getTime()) / (1000 * 3600 * 24) + 1;
			}
		}
		return daysCount;
	}
	
	public static Date parse_yyyy_MM_dd(String date){
		String format = "yyyy-MM-dd";
		return parse(format, date);
	}
	
	
	public static String format_yyyy_MM_dd(Date date){
		String format = "yyyy-MM-dd";
		return format(date, format);
	}
	
	public static String format_yyyy_MM(Date date){
		String format = "yyyy-MM";
		return format(date, format);
	}
	
	public static Date parse_yyyy_MM_dd_HH_mm_ss(String date){
		String format = "yyyy-MM-dd HH:mm:ss";
		return parse(format, date);
	}
	
	public static Date parse(String format,String date){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			//e.printStackTrace();
		} catch(NullPointerException ne){
			
		}
		return null;
	}
	
	public static String format(Date date,String format){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);	
		}catch (Exception e) {
			
		}
		return null;
	}
	
	public static void main(String[] args) {
		Date d1 = parse_yyyy_MM_dd_HH_mm_ss("2016-08-8 23:11:11");
		System.out.println(toLastDayOfThisMonth(d1).toLocaleString());
		Calendar cal = Calendar.getInstance();
		System.out.println(cal.get(Calendar.DAY_OF_WEEK));
		Date d2 = parse_yyyy_MM_dd_HH_mm_ss("2016-9-7 21:11:11");
		System.out.println(toLastDayOfThisMonth(d2).toLocaleString());
		System.out.println("========================");
		System.out.println(d1);
		System.out.println(d1.toLocaleString());
		System.out.println(d2.toLocaleString());
		List<Date[]> dates = getContainingWeekList(d1,d2); // getContainingMonth(d1,d2); //
		if(dates != null){
			for(Date[] d : dates){
				System.out.println(d[0].toLocaleString() + "-" + d[1].toLocaleString());
			}
		}
		
		//
		Date d = new Date();
		System.out.println(d);
		System.out.println(add(d, Calendar.DAY_OF_YEAR, -1));
	}
	
	public static Date toLastDayOfThisMonth(Date d){
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DAY_OF_YEAR, -1);
		return cal.getTime();
	}
	
	public static List<Date[]> getContainingMonth(Date d1,Date d2){
		List<Date[]> list = new ArrayList<Date[]>();
		try{
			d1 = parse_yyyy_MM_dd(format_yyyy_MM_dd(d1));// yyyy-MM-dd 00:00:00
			Calendar cal = Calendar.getInstance(),cal2 = Calendar.getInstance();
			cal.setTime(d1);cal2.setTime(d2);
			//cal = this month's first day
			cal.set(Calendar.DAY_OF_MONTH, 1);// yyyy-MM-01 00:00:00
			while(!cal.after(cal2)){
				Date date[] = new Date[2];
				date[0] = cal.getTime();
				//last day of this month
				cal.add(Calendar.MONTH, 1);
				cal.set(Calendar.DAY_OF_MONTH, 1);
				cal.add(Calendar.DAY_OF_YEAR, -1);
				date[1] = cal.getTime();
				list.add(date);
				//next month's first day
				cal.add(Calendar.DAY_OF_YEAR, 1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 周一到周日为一周
	 * @Title: getContainingWeekList2
	 * @Description: TODO
	 * @param d1
	 * @param d2
	 * @return
	 * List<Date[]>
	 * @author moxw
	 * @date 2016年8月29日 下午5:56:08
	 */
	public static List<Date[]> getContainingWeekList(Date d1,Date d2){
		List<Date[]> datePairList = new ArrayList<Date[]>();
		if(d1 != null && d2 != null){
			//交换
			if(d1.after(d2)){
				Date d = d2;
				d2 = d1;
				d1 = d;
			}
			
			Calendar cal = toCalendar(d1),cal2 = toCalendar(d2);		//sun  mon
			
			while(cal.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY){	// 1    2
				cal.add(Calendar.DAY_OF_YEAR, -1);						// mon  ...  sun
			}															// 1          7
			while(cal2.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY){
				cal2.add(Calendar.DAY_OF_YEAR, 1);
			}
			List<Date> dlist = new LinkedList<Date>();
			while(cal.before(cal2)){
				Date pairDate[] = new Date[2];
				pairDate[0] = cal.getTime();
				cal.add(Calendar.DAY_OF_YEAR, DAYS_OF_WEEK-1);
				pairDate[1] = cal.getTime();
				datePairList.add(pairDate);
				cal.add(Calendar.DAY_OF_YEAR, 1);
			}
		}
		return datePairList;
	}
	
	/**
	 * 周日到周一为一周
	 * @Title: getContainingWeekList
	 * @Description: TODO
	 * @param d1
	 * @param d2
	 * @return
	 * List<Date[]>
	 * @author moxw
	 * @date 2016年8月29日 下午5:56:28
	 */
	public static List<Date[]> getContainingWeekList2(Date d1,Date d2){
		List<Date[]> datePairList = new ArrayList<Date[]>();
		try{
			if(d1 != null && d2 != null){
				if(d1.after(d2)){
					Date d = d2;
					d2 = d1;
					d1 = d;
				}
				Calendar cal = toCalendar(d1),cal2 = toCalendar(d2);
				cal.set(Calendar.DAY_OF_WEEK, FIRST_DAY_IN_WEEK_VALUE);
				cal2.set(Calendar.DAY_OF_WEEK, DAYS_OF_WEEK);
				List<Date> dlist = new LinkedList<Date>();
				while(cal.before(cal2)){
					Date pairDate[] = new Date[2];
					pairDate[0] = cal.getTime();
					cal.add(Calendar.DAY_OF_YEAR, DAYS_OF_WEEK-1);
					pairDate[1] = cal.getTime();
					datePairList.add(pairDate);
					cal.add(Calendar.DAY_OF_YEAR, 1);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return datePairList;
	}
	
	
	public static List<Date[]> getAbsoluteContainingWeekList(Date d1,Date d2){
		List<Date[]> datePairList = new ArrayList<Date[]>();
		try{
			if(d2.after(d1)){
				Calendar cal = toCalendar(d1),cal2 = toCalendar(d2);
				int prefix_days_to_ignore = cal.get(Calendar.DAY_OF_WEEK) == 1 ? 0 :  DAYS_OF_WEEK - cal.get(Calendar.DAY_OF_WEEK) + 1;//7 - 1
				int postfix_days_to_ignore = cal2.get(Calendar.DAY_OF_WEEK) == DAYS_OF_WEEK ? 0 :  cal2.get(Calendar.DAY_OF_WEEK);
				cal.add(Calendar.DAY_OF_YEAR, prefix_days_to_ignore);
				cal2.add(Calendar.DAY_OF_YEAR, -postfix_days_to_ignore);
				List<Date> dlist = new LinkedList<Date>();
				while(!cal.after(cal2)){
					Date pairDate[] = new Date[2];
					pairDate[0] = cal.getTime();
					cal.add(Calendar.DAY_OF_YEAR, DAYS_OF_WEEK-1);
					pairDate[1] = cal.getTime();
					datePairList.add(pairDate);
					cal.add(Calendar.DAY_OF_YEAR, 1);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return datePairList;
	}

	public static Calendar toCalendar(Date d){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		return calendar;
	}
	//日期加法
	public static Date add(Date day, int field, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(day);
		cal.add(field, n);
		return cal.getTime();
	}
	//日期加1
	public static String addDay(String date,int interval) throws ParseException{
			Calendar c = new GregorianCalendar();
			c.setTime(sdf_day.parse(date));
			c.add(Calendar.DATE, interval);
			return sdf_day.format(c.getTime());
		}
	//日期间隔(正数)
	public static int getDateInterval(String startDate,String endDate){
		int day=0;
		String format = "yyyy-MM-dd";
		Date startTime= parse(format, startDate);
		Date endTime=parse(format, endDate);
		day=Math.abs(new Long((endTime.getTime()-startTime.getTime())/(1000*60*60*24)).intValue());
		return day;
	}
	//日期间隔(正负数均可)
	public static int getTimeInterval(String startDate,String endDate){
		int day=0;
		String format = "yyyy-MM-dd";
		Date startTime= parse(format, startDate);
		Date endTime=parse(format, endDate);
		day=new Long((endTime.getTime()-startTime.getTime())/(1000*60*60*24)).intValue();
		return day;
	}
	
	/**
	 * 获取月的最后一天
	 * @Title: monthLastDay
	 * @Description: TODO
	 * @param str 日期
	 * @return
	 * @throws ParseException
	 * String
	 * @author hwj
	 * @date 2017-2-14 下午05:12:17
	 */
	public static String monthLastDay(String str) throws ParseException{
		String first = str.substring(0, 7)+"-01";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(first);
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.add(Calendar.MONTH, 1);
		c.add(Calendar.DAY_OF_YEAR, -1);
		return sdf.format(c.getTime());
	}
	
	public static String firstDayOfMonth(String date ) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date time=sdf.parse(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return sdf.format(cal.getTime());
		}
	
	/**
	 * 两个日期相隔月数
	 * @Title: getMonthSpace
	 * @Description: TODO
	 * @param date1
	 * @param date2
	 * @return
	 * @throws ParseException
	 * int
	 * @author hwj
	 * @date 2017-2-14 下午05:26:21
	 */
	 public static int getMonthSpace(String date1, String date2)throws ParseException {
		 int result = 0;
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 Calendar c1 = Calendar.getInstance();
		 Calendar c2 = Calendar.getInstance();
		 c1.setTime(sdf.parse(date1));
		 c2.setTime(sdf.parse(date2));
		 result = (c2.get(Calendar.YEAR)-c1.get(Calendar.YEAR))*12+(c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH));
		 return Math.abs(result);
		
		}
	 //格式日期字符串
	 public static String format_yyyy_MM_dd_HH_mm_ss(Date date){
		 String format = "yyyy-MM-dd HH:mm:ss";
			return format(date, format);
		}
	 //获取日期指定小时或者分钟，秒
	 public static int getHourOrMinOrMm(Date date,int filed){
		 Calendar c1 = Calendar.getInstance();
		 c1.setTime(date);
		 return c1.get(filed);
	 }
	 /**
	  * 设置小时,分钟,秒
	  * @Title: setDateTime
	  * @Description: TODO
	  * @param date
	  * @param hour
	  * @param minute
	  * @param second
	  * @return
	  * Date
	  * @author hwj
	  * @date 2017-7-27 下午05:36:30
	  */
	 public static  Date setHour_minute_second(Date date,int hour,int minute,int second){
			Calendar c1=Calendar.getInstance();
			c1.setTime(date);
			c1.set(Calendar.HOUR_OF_DAY, hour);
			c1.set(Calendar.MINUTE, minute);
			c1.set(Calendar.SECOND, second);
			return c1.getTime();
		}
	 /**
	  * 格式时间为HH:mm
	  * @Title: format_HH_mm
	  * @Description: TODO
	  * @param day
	  * @return
	  * String
	  * @author hwj
	  * @date 2017-7-27 下午05:37:01
	  */
		public static String format_HH_mm(Date day){
			Calendar calender1=Calendar.getInstance();
			calender1.setTime(day);
			int hour=calender1.get(Calendar.HOUR_OF_DAY);
			int minute=calender1.get(Calendar.MINUTE);
			return hour+":"+minute;
		}
		/**
		 *	秒转化为x小时x分钟x秒
		 * @Title: secondTransferToHMS
		 * @Description: TODO
		 * @param second
		 * @return
		 * String
		 * @author hwj
		 * @date 2017-7-28 上午10:26:06
		 */
		public static String secondTransferToHMS(int second){
//			int h = 0;  
	        int d = 0;  
	        int s = 0;  
	        int temp = second%3600;  
	         if(second>3600){  
	           int h= second/3600;  
	           if(temp!=0){  
	    	   if(temp>60){  
	    		   d = temp/60;  
	              if(temp%60!=0){  
	               s = temp%60;  
	              }  
		        }else{  
		           s = temp;  
		        }  
	       } 
	       return h+"时"+d+"分"+s+"秒"; 
	      }else{  
	          d = second/60;  
	       if(second%60!=0){  
	          s = second%60;  
	       }
	       return d+"分"+s+"秒";  
	      }  
		}
		/**
		 * 秒转化为x小时x分钟x秒
		 * @Title: toLocalTimeDesc
		 * @Description: TODO
		 * @param time
		 * @return
		 * String
		 * @author hwj
		 * @date 2017-12-29 上午11:36:09
		 */
		public static String toLocalTimeDesc(Long time){
			int hh = 0,mm = 0, ss = 0;
			//ss = e.getTimes() % 60;
			ss = BigInteger.valueOf(time).mod(BigInteger.valueOf(60L)).intValue();
			
			//mm = e.getTimes() % 3600 / 60;
			mm = BigInteger.valueOf(time).mod(BigInteger.valueOf(3600L)).intValue()/60;
			//hh = e.getTimes() / 3600;
			hh = BigInteger.valueOf(time).divide(BigInteger.valueOf(3600L)).intValue();
			return (hh + "h" + mm + "m" + ss+"s");
		}
}
