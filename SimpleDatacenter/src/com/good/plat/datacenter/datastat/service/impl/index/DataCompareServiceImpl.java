package com.good.plat.datacenter.datastat.service.impl.index;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.base.DateUtil;
import com.good.plat.datacenter.datastat.entity.index.DataCompare;
import com.good.plat.datacenter.datastat.mapper.index.DataCompareMapper;
import com.good.plat.datacenter.datastat.service.index.DataCompareService;
@Service(value="dataCompareService")
public class DataCompareServiceImpl implements DataCompareService {
	@Autowired
	private DataCompareMapper dataCompareMapper;
	@Override
	public List<DataCompare> selectAUList(BaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		searchData=searchData.searchDataFilter(searchData);
		Date endDate1=DateUtil.parse_yyyy_MM_dd(searchData.getStartdate());//查询日期
		Date endDate2=DateUtil.parse_yyyy_MM_dd(searchData.getEnddate());//对比日期
		int count=Integer.parseInt(searchData.getChecktype2());//天数
		
		List<DataCompare> list=new ArrayList<DataCompare>();
		List<DataCompare> currlist=new ArrayList<DataCompare>();
		List<DataCompare> comparelist=new ArrayList<DataCompare>();
		
		List<String> dateList1=new ArrayList<String>();
		List<String> dateList2=new ArrayList<String>();
		
		Date date1=null;
		Date date2=null;
		String startTime=null;//
		String startTime2=null;//
		int j=0;
		int k=0;
		//类型
		switch (Integer.parseInt(searchData.getChecktype1())) {
		case 1:
			//日
			//查询日期数据
			date1=DateUtil.add(endDate1, Calendar.DAY_OF_MONTH, -(count-1));
			startTime=DateUtil.format_yyyy_MM_dd(date1);
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd(endDate1));
			searchData.setStartdate(startTime);
			currlist=dataCompareMapper.selectAUList(searchData);
			//对比日期的数据
			date2=DateUtil.add(endDate2, Calendar.DAY_OF_MONTH, -(count-1));
			startTime2=DateUtil.format_yyyy_MM_dd(date2);
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd(endDate2));
			searchData.setStartdate(startTime2);
			comparelist=dataCompareMapper.selectAUList(searchData);
			while(date1.getTime()<=endDate1.getTime()){
				String date=DateUtil.format_yyyy_MM_dd(date1);
				dateList1.add(date);
				date1=DateUtil.add(date1, Calendar.DAY_OF_MONTH, 1);
			}
			
			while(date2.getTime()<=endDate2.getTime()){
				String date=DateUtil.format_yyyy_MM_dd(date2);
				dateList2.add(date);
				date2=DateUtil.add(date2, Calendar.DAY_OF_MONTH, 1);
			}
			for(int i=0;i<dateList1.size();i++){
				
				DataCompare data=new DataCompare();
				String date=dateList1.get(i);
				if(j<currlist.size()&&date.equals(currlist.get(j).getStatdate())){
					data.setDate(date);
					data.setValue(currlist.get(j).getAccount().doubleValue());
					j=j+1;
				}else{
					data.setDate(date);
					data.setValue(0.0);
				}
				
				String new_date=dateList2.get(i);
				if(k<comparelist.size()&&new_date.equals(comparelist.get(k).getStatdate())){
					data.setNew_date(new_date);
					data.setNew_value(comparelist.get(k).getAccount().doubleValue());
					k=k+1;
				}else{
					data.setNew_date(new_date);
					data.setNew_value(0.0);
				}
				list.add(data);
			}
			break;
		case 2:
			//周
			//要查询数据
			date1=DateUtil.add(endDate1, Calendar.DAY_OF_MONTH, -((count*7)-1));
			startTime=DateUtil.format_yyyy_MM_dd(date1);
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd(endDate1));
			searchData.setStartdate(startTime);
			currlist=dataCompareMapper.selectWeekOrMonthAUList(searchData);
			
			//对比日期的数据
			date2=DateUtil.add(endDate2, Calendar.DAY_OF_MONTH, -((count*7)-1));
			startTime2=DateUtil.format_yyyy_MM_dd(date2);
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd(endDate2));
			searchData.setStartdate(startTime2);
			comparelist=dataCompareMapper.selectWeekOrMonthAUList(searchData);
			
			Date c_endDate1=endDate1;
			Date c_endDate2=endDate2;
			//currlist数组显示日期处理
			if(currlist!=null){
				for(DataCompare d:currlist){
					if(d!=null){
						Date startDate=DateUtil.add(c_endDate1, Calendar.DAY_OF_MONTH, -6);
						String str=DateUtil.format_yyyy_MM_dd(startDate)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate1);
						d.setStatdate(str);
						c_endDate1=DateUtil.add(startDate, Calendar.DAY_OF_MONTH, -1);
					}
				}
			}
			//comparelist数组显示日期处理
			if(comparelist!=null){
				for(DataCompare d:comparelist){
					if(d!=null){
						Date startDate=DateUtil.add(c_endDate2, Calendar.DAY_OF_MONTH, -6);
						String str=DateUtil.format_yyyy_MM_dd(startDate)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate2);
						d.setStatdate(str);
						c_endDate2=DateUtil.add(startDate, Calendar.DAY_OF_MONTH, -1);
					}
				}
			}
			c_endDate1=endDate1;
			c_endDate2=endDate2;
			//周数组
			for(int i=0;i<count;i++){
				//当前时间的周字符串数组
				Date startDate=DateUtil.add(c_endDate1, Calendar.DAY_OF_MONTH, -6);
				String str=DateUtil.format_yyyy_MM_dd(startDate)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate1);
				dateList1.add(str);
				c_endDate1=DateUtil.add(startDate, Calendar.DAY_OF_MONTH, -1);
				//对比时间的周字符串数组
				Date startDate2=DateUtil.add(c_endDate2, Calendar.DAY_OF_MONTH, -6);
				String str2=DateUtil.format_yyyy_MM_dd(startDate2)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate2);
				dateList2.add(str2);
				c_endDate2=DateUtil.add(startDate2, Calendar.DAY_OF_MONTH, -1);
			}
			//currlist数组与comparelist数组合并
			for(int i=0;i<dateList1.size();i++){
				DataCompare data=new DataCompare();
				String date=dateList1.get(i);
				if(j<currlist.size()&&date.equals(currlist.get(j).getStatdate())){
					data.setDate(date);
					data.setValue(currlist.get(j).getAccount().doubleValue());
					j=j+1;
				}else{
					data.setDate(date);
					data.setValue(0.0);
				}
				
				String new_date=dateList2.get(i);
				if(k<comparelist.size()&&new_date.equals(comparelist.get(k).getStatdate())){
					data.setNew_date(new_date);
					data.setNew_value(comparelist.get(k).getAccount().doubleValue());
					k=k+1;
				}else{
					data.setNew_date(new_date);
					data.setNew_value(0.0);
				}
				list.add(data);
			}
			break;
		case 3:
			//月
			date1=DateUtil.add(endDate1, Calendar.DAY_OF_MONTH, -((count*30)-1));
			startTime=DateUtil.format_yyyy_MM_dd(date1);
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd(endDate1));
			searchData.setStartdate(startTime);
			currlist=dataCompareMapper.selectWeekOrMonthAUList(searchData);
			//对比日期的数据
			date2=DateUtil.add(endDate2, Calendar.DAY_OF_MONTH, -((count*30)-1));
			startTime2=DateUtil.format_yyyy_MM_dd(date2);
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd(endDate2));
			searchData.setStartdate(startTime2);
			comparelist=dataCompareMapper.selectWeekOrMonthAUList(searchData);
			
			
			Date c_endDate3=endDate1;
			Date c_endDate4=endDate2;
			//currlist数组显示日期处理
			if(currlist!=null){
				for(DataCompare d:currlist){
					if(d!=null){
						Date startDate=DateUtil.add(c_endDate3, Calendar.DAY_OF_MONTH, -29);
						String str=DateUtil.format_yyyy_MM_dd(startDate)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate3);
						d.setStatdate(str);
						c_endDate3=DateUtil.add(startDate, Calendar.DAY_OF_MONTH, -1);
					}
				}
			}
			//comparelist数组显示日期处理
			if(comparelist!=null){
				for(DataCompare d:comparelist){
					if(d!=null){
						Date startDate=DateUtil.add(c_endDate4, Calendar.DAY_OF_MONTH, -29);
						String str=DateUtil.format_yyyy_MM_dd(startDate)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate4);
						d.setStatdate(str);
						c_endDate4=DateUtil.add(startDate, Calendar.DAY_OF_MONTH, -1);
					}
				}
			}
			c_endDate3=endDate1;
			c_endDate4=endDate2;
			//月数组
			for(int i=0;i<count;i++){
				//当前时间的月字符串数组
				Date startDate=DateUtil.add(c_endDate3, Calendar.DAY_OF_MONTH, -29);
				String str=DateUtil.format_yyyy_MM_dd(startDate)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate3);
				dateList1.add(str);
				c_endDate3=DateUtil.add(startDate, Calendar.DAY_OF_MONTH, -1);
				//对比时间的周字符串数组
				Date startDate2=DateUtil.add(c_endDate4, Calendar.DAY_OF_MONTH, -29);
				String str2=DateUtil.format_yyyy_MM_dd(startDate2)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate4);
				dateList2.add(str2);
				c_endDate4=DateUtil.add(startDate2, Calendar.DAY_OF_MONTH, -1);
			}
			//currlist数组与comparelist数组合并
			for(int i=0;i<dateList1.size();i++){
				DataCompare data=new DataCompare();
				String date=dateList1.get(i);
				if(j<currlist.size()&&date.equals(currlist.get(j).getStatdate())){
					data.setDate(date);
					data.setValue(currlist.get(j).getAccount().doubleValue());
					j=j+1;
				}else{
					data.setDate(date);
					data.setValue(0.0);
				}
				
				String new_date=dateList2.get(i);
				if(k<comparelist.size()&&new_date.equals(comparelist.get(k).getStatdate())){
					data.setNew_date(new_date);
					data.setNew_value(comparelist.get(k).getAccount().doubleValue());
					k=k+1;
				}else{
					data.setNew_date(new_date);
					data.setNew_value(0.0);
				}
				list.add(data);
			}
			break;
		}
		return list;
	}

	@Override
	public List<DataCompare> selectAcuAndPcuList(BaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		searchData=searchData.searchDataFilter(searchData);
		Date endDate1=DateUtil.parse_yyyy_MM_dd(searchData.getStartdate());//查询日期
		Date endDate2=DateUtil.parse_yyyy_MM_dd(searchData.getEnddate());//对比日期
		int count=Integer.parseInt(searchData.getChecktype2());//天数
		
		List<DataCompare> list=new ArrayList<DataCompare>();
		List<DataCompare> currlist=new ArrayList<DataCompare>();
		List<DataCompare> comparelist=new ArrayList<DataCompare>();
		
		List<String> dateList1=new ArrayList<String>();
		List<String> dateList2=new ArrayList<String>();
		
		Date date1=null;
		Date date2=null;
		String startTime=null;//
		String startTime2=null;//
		int j=0;
		int k=0;
		//类型
		switch (Integer.parseInt(searchData.getChecktype1())) {
		case 1:
			//日
			//查询日期数据
			date1=DateUtil.add(endDate1, Calendar.DAY_OF_MONTH, -(count-1));
			startTime=DateUtil.format_yyyy_MM_dd(date1);
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd(endDate1));
			searchData.setStartdate(startTime);
			currlist=dataCompareMapper.selectDailyAcuAndPcuList(searchData);
			//对比日期的数据
			date2=DateUtil.add(endDate2, Calendar.DAY_OF_MONTH, -(count-1));
			startTime2=DateUtil.format_yyyy_MM_dd(date2);
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd(endDate2));
			searchData.setStartdate(startTime2);
			comparelist=dataCompareMapper.selectDailyAcuAndPcuList(searchData);
			while(date1.getTime()<=endDate1.getTime()){
				String date=DateUtil.format_yyyy_MM_dd(date1);
				dateList1.add(date);
				date1=DateUtil.add(date1, Calendar.DAY_OF_MONTH, 1);
			}
			
			while(date2.getTime()<=endDate2.getTime()){
				String date=DateUtil.format_yyyy_MM_dd(date2);
				dateList2.add(date);
				date2=DateUtil.add(date2, Calendar.DAY_OF_MONTH, 1);
			}
			//两份数据合并
			for(int i=0;i<dateList1.size();i++){
				
				DataCompare data=new DataCompare();
				String date=dateList1.get(i);
				if(j<currlist.size()&&date.equals(currlist.get(j).getStatdate())){
					data.setDate(date);
					if(searchData.getChecktype3().equals("acu")){
						data.setValue(currlist.get(j).getAcu().doubleValue());
					}
					if(searchData.getChecktype3().equals("pcu")){
						data.setValue(currlist.get(j).getPcu().doubleValue());
					}
					j=j+1;
				}else{
					data.setDate(date);
					data.setValue(0.0);
				}
				
				String new_date=dateList2.get(i);
				if(k<comparelist.size()&&new_date.equals(comparelist.get(k).getStatdate())){
					data.setNew_date(new_date);
					if(searchData.getChecktype3().equals("acu")){
						data.setNew_value(comparelist.get(k).getAcu().doubleValue());
					}
					if(searchData.getChecktype3().equals("pcu")){
						data.setNew_value(comparelist.get(k).getPcu().doubleValue());
					}
					k=k+1;
				}else{
					data.setNew_date(new_date);
					data.setNew_value(0.0);
				}
				list.add(data);
			}
			break;
		case 2:
			//周
			//要查询数据
			date1=DateUtil.add(endDate1, Calendar.DAY_OF_MONTH, -((count*7)-1));
			startTime=DateUtil.format_yyyy_MM_dd(date1);
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd(endDate1));
			searchData.setStartdate(startTime);
			currlist=dataCompareMapper.selectWeekAndMonthAcuAndPcuList(searchData);
			
			//对比日期的数据
			date2=DateUtil.add(endDate2, Calendar.DAY_OF_MONTH, -((count*7)-1));
			startTime2=DateUtil.format_yyyy_MM_dd(date2);
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd(endDate2));
			searchData.setStartdate(startTime2);
			comparelist=dataCompareMapper.selectWeekAndMonthAcuAndPcuList(searchData);
			
			Date c_endDate1=endDate1;
			Date c_endDate2=endDate2;
			//currlist数组显示日期处理
			if(currlist!=null){
				for(DataCompare d:currlist){
					if(d!=null){
						Date startDate=DateUtil.add(c_endDate1, Calendar.DAY_OF_MONTH, -6);
						String str=DateUtil.format_yyyy_MM_dd(startDate)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate1);
						d.setStatdate(str);
						c_endDate1=DateUtil.add(startDate, Calendar.DAY_OF_MONTH, -1);
					}
				}
			}
			//comparelist数组显示日期处理
			if(comparelist!=null){
				for(DataCompare d:comparelist){
					if(d!=null){
						Date startDate=DateUtil.add(c_endDate2, Calendar.DAY_OF_MONTH, -6);
						String str=DateUtil.format_yyyy_MM_dd(startDate)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate2);
						d.setStatdate(str);
						c_endDate2=DateUtil.add(startDate, Calendar.DAY_OF_MONTH, -1);
					}
				}
			}
			c_endDate1=endDate1;
			c_endDate2=endDate2;
			//周数组
			for(int i=0;i<count;i++){
				//当前时间的周字符串数组
				Date startDate=DateUtil.add(c_endDate1, Calendar.DAY_OF_MONTH, -6);
				String str=DateUtil.format_yyyy_MM_dd(startDate)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate1);
				dateList1.add(str);
				c_endDate1=DateUtil.add(startDate, Calendar.DAY_OF_MONTH, -1);
				//对比时间的周字符串数组
				Date startDate2=DateUtil.add(c_endDate2, Calendar.DAY_OF_MONTH, -6);
				String str2=DateUtil.format_yyyy_MM_dd(startDate2)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate2);
				dateList2.add(str2);
				c_endDate2=DateUtil.add(startDate2, Calendar.DAY_OF_MONTH, -1);
			}
			//currlist数组与comparelist数组合并
			for(int i=0;i<dateList1.size();i++){
				DataCompare data=new DataCompare();
				String date=dateList1.get(i);
				if(j<currlist.size()&&date.equals(currlist.get(j).getStatdate())){
					data.setDate(date);
					if(searchData.getChecktype3().equals("acu")){
						data.setValue(currlist.get(j).getAcu().doubleValue());
					}
					if(searchData.getChecktype3().equals("pcu")){
						data.setValue(currlist.get(j).getPcu().doubleValue());
					}
					j=j+1;
				}else{
					data.setDate(date);
					data.setValue(0.0);
				}
				
				String new_date=dateList2.get(i);
				if(k<comparelist.size()&&new_date.equals(comparelist.get(k).getStatdate())){
					data.setNew_date(new_date);
					if(searchData.getChecktype3().equals("acu")){
						data.setNew_value(comparelist.get(k).getAcu().doubleValue());
					}
					if(searchData.getChecktype3().equals("pcu")){
						data.setNew_value(comparelist.get(k).getPcu().doubleValue());
					}
					k=k+1;
				}else{
					data.setNew_date(new_date);
					data.setNew_value(0.0);
				}
				list.add(data);
			}
			break;
		case 3:
			//月
			date1=DateUtil.add(endDate1, Calendar.DAY_OF_MONTH, -((count*30)-1));
			startTime=DateUtil.format_yyyy_MM_dd(date1);
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd(endDate1));
			searchData.setStartdate(startTime);
			currlist=dataCompareMapper.selectWeekAndMonthAcuAndPcuList(searchData);
			//对比日期的数据
			date2=DateUtil.add(endDate2, Calendar.DAY_OF_MONTH, -((count*30)-1));
			startTime2=DateUtil.format_yyyy_MM_dd(date2);
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd(endDate2));
			searchData.setStartdate(startTime2);
			comparelist=dataCompareMapper.selectWeekAndMonthAcuAndPcuList(searchData);
			
			
			Date c_endDate3=endDate1;
			Date c_endDate4=endDate2;
			//currlist数组显示日期处理
			if(currlist!=null){
				for(DataCompare d:currlist){
					if(d!=null){
						Date startDate=DateUtil.add(c_endDate3, Calendar.DAY_OF_MONTH, -29);
						String str=DateUtil.format_yyyy_MM_dd(startDate)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate3);
						d.setStatdate(str);
						c_endDate3=DateUtil.add(startDate, Calendar.DAY_OF_MONTH, -1);
					}
				}
			}
			//comparelist数组显示日期处理
			if(comparelist!=null){
				for(DataCompare d:comparelist){
					if(d!=null){
						Date startDate=DateUtil.add(c_endDate4, Calendar.DAY_OF_MONTH, -29);
						String str=DateUtil.format_yyyy_MM_dd(startDate)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate4);
						d.setStatdate(str);
						c_endDate4=DateUtil.add(startDate, Calendar.DAY_OF_MONTH, -1);
					}
				}
			}
			c_endDate3=endDate1;
			c_endDate4=endDate2;
			//月数组
			for(int i=0;i<count;i++){
				//当前时间的月字符串数组
				Date startDate=DateUtil.add(c_endDate3, Calendar.DAY_OF_MONTH, -29);
				String str=DateUtil.format_yyyy_MM_dd(startDate)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate3);
				dateList1.add(str);
				c_endDate3=DateUtil.add(startDate, Calendar.DAY_OF_MONTH, -1);
				//对比时间的周字符串数组
				Date startDate2=DateUtil.add(c_endDate4, Calendar.DAY_OF_MONTH, -29);
				String str2=DateUtil.format_yyyy_MM_dd(startDate2)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate4);
				dateList2.add(str2);
				c_endDate4=DateUtil.add(startDate2, Calendar.DAY_OF_MONTH, -1);
			}
			//currlist数组与comparelist数组合并
			for(int i=0;i<dateList1.size();i++){
				DataCompare data=new DataCompare();
				String date=dateList1.get(i);
				if(j<currlist.size()&&date.equals(currlist.get(j).getStatdate())){
					data.setDate(date);
					if(searchData.getChecktype3().equals("acu")){
						data.setValue(currlist.get(j).getAcu().doubleValue());
					}
					if(searchData.getChecktype3().equals("pcu")){
						data.setValue(currlist.get(j).getPcu().doubleValue());
					}
					
					j=j+1;
				}else{
					data.setDate(date);
					data.setValue(0.0);
				}
				
				String new_date=dateList2.get(i);
				if(k<comparelist.size()&&new_date.equals(comparelist.get(k).getStatdate())){
					data.setNew_date(new_date);
					if(searchData.getChecktype3().equals("acu")){
						data.setNew_value(comparelist.get(k).getAcu().doubleValue());
					}
					if(searchData.getChecktype3().equals("pcu")){
						data.setNew_value(comparelist.get(k).getPcu().doubleValue());
					}
					k=k+1;
				}else{
					data.setNew_date(new_date);
					data.setNew_value(0.0);
				}
				list.add(data);
			}
			break;
		}
		return list;
	}

	@Override
	public List<DataCompare> selectIncomeList(BaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		searchData=searchData.searchDataFilter(searchData);
		Date endDate1=DateUtil.parse_yyyy_MM_dd(searchData.getStartdate());//查询日期
		Date endDate2=DateUtil.parse_yyyy_MM_dd(searchData.getEnddate());//对比日期
		int count=Integer.parseInt(searchData.getChecktype2());//天数
		
		List<DataCompare> list=new ArrayList<DataCompare>();
		List<DataCompare> currlist=new ArrayList<DataCompare>();
		List<DataCompare> comparelist=new ArrayList<DataCompare>();
		
		List<String> dateList1=new ArrayList<String>();
		List<String> dateList2=new ArrayList<String>();
		
		Date date1=null;
		Date date2=null;
		String startTime=null;//
		String startTime2=null;//
		int j=0;
		int k=0;
		//类型
		switch (Integer.parseInt(searchData.getChecktype1())) {
		case 1:
			//日
			//查询日期数据
			date1=DateUtil.add(endDate1, Calendar.DAY_OF_MONTH, -(count-1));
			startTime=DateUtil.format_yyyy_MM_dd(date1);
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd(endDate1));
			searchData.setStartdate(startTime);
			currlist=dataCompareMapper.selectIncomeList(searchData);
			//对比日期的数据
			date2=DateUtil.add(endDate2, Calendar.DAY_OF_MONTH, -(count-1));
			startTime2=DateUtil.format_yyyy_MM_dd(date2);
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd(endDate2));
			searchData.setStartdate(startTime2);
			comparelist=dataCompareMapper.selectIncomeList(searchData);
			while(date1.getTime()<=endDate1.getTime()){
				String date=DateUtil.format_yyyy_MM_dd(date1);
				dateList1.add(date);
				date1=DateUtil.add(date1, Calendar.DAY_OF_MONTH, 1);
			}
			
			while(date2.getTime()<=endDate2.getTime()){
				String date=DateUtil.format_yyyy_MM_dd(date2);
				dateList2.add(date);
				date2=DateUtil.add(date2, Calendar.DAY_OF_MONTH, 1);
			}
			//两份数据合并
			for(int i=0;i<dateList1.size();i++){
				
				DataCompare data=new DataCompare();
				String date=dateList1.get(i);
				if(j<currlist.size()&&date.equals(currlist.get(j).getStatdate())){
					data.setDate(date);
					if(searchData.getChecktype3().equals("money")){
						data.setValue(currlist.get(j).getIncomes().doubleValue());
					}
					if(searchData.getChecktype3().equals("times")){
						data.setValue(currlist.get(j).getPayCounts().doubleValue());
					}
					j=j+1;
				}else{
					data.setDate(date);
					data.setValue(0.0);
				}
				
				String new_date=dateList2.get(i);
				if(k<comparelist.size()&&new_date.equals(comparelist.get(k).getStatdate())){
					data.setNew_date(new_date);
					if(searchData.getChecktype3().equals("money")){
						data.setNew_value(comparelist.get(k).getIncomes());
					}
					if(searchData.getChecktype3().equals("times")){
						data.setNew_value(comparelist.get(k).getPayCounts().doubleValue());
					}
					k=k+1;
				}else{
					data.setNew_date(new_date);
					data.setNew_value(0.0);
				}
				list.add(data);
			}
			break;
		case 2:
			//周
			//要查询数据
			date1=DateUtil.add(endDate1, Calendar.DAY_OF_MONTH, -((count*7)-1));
			startTime=DateUtil.format_yyyy_MM_dd(date1);
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd(endDate1));
			searchData.setStartdate(startTime);
			currlist=dataCompareMapper.selectWeekOrMonthIncomeList(searchData);
			
			//对比日期的数据
			date2=DateUtil.add(endDate2, Calendar.DAY_OF_MONTH, -((count*7)-1));
			startTime2=DateUtil.format_yyyy_MM_dd(date2);
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd(endDate2));
			searchData.setStartdate(startTime2);
			comparelist=dataCompareMapper.selectWeekOrMonthIncomeList(searchData);
			
			Date c_endDate1=endDate1;
			Date c_endDate2=endDate2;
			//currlist数组显示日期处理
			if(currlist!=null){
				for(DataCompare d:currlist){
					if(d!=null){
						Date startDate=DateUtil.add(c_endDate1, Calendar.DAY_OF_MONTH, -6);
						String str=DateUtil.format_yyyy_MM_dd(startDate)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate1);
						d.setStatdate(str);
						c_endDate1=DateUtil.add(startDate, Calendar.DAY_OF_MONTH, -1);
					}
				}
			}
			//comparelist数组显示日期处理
			if(comparelist!=null){
				for(DataCompare d:comparelist){
					if(d!=null){
						Date startDate=DateUtil.add(c_endDate2, Calendar.DAY_OF_MONTH, -6);
						String str=DateUtil.format_yyyy_MM_dd(startDate)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate2);
						d.setStatdate(str);
						c_endDate2=DateUtil.add(startDate, Calendar.DAY_OF_MONTH, -1);
					}
				}
			}
			c_endDate1=endDate1;
			c_endDate2=endDate2;
			//周数组
			for(int i=0;i<count;i++){
				//当前时间的周字符串数组
				Date startDate=DateUtil.add(c_endDate1, Calendar.DAY_OF_MONTH, -6);
				String str=DateUtil.format_yyyy_MM_dd(startDate)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate1);
				dateList1.add(str);
				c_endDate1=DateUtil.add(startDate, Calendar.DAY_OF_MONTH, -1);
				//对比时间的周字符串数组
				Date startDate2=DateUtil.add(c_endDate2, Calendar.DAY_OF_MONTH, -6);
				String str2=DateUtil.format_yyyy_MM_dd(startDate2)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate2);
				dateList2.add(str2);
				c_endDate2=DateUtil.add(startDate2, Calendar.DAY_OF_MONTH, -1);
			}
			//currlist数组与comparelist数组合并
			for(int i=0;i<dateList1.size();i++){
				DataCompare data=new DataCompare();
				String date=dateList1.get(i);
				if(j<currlist.size()&&date.equals(currlist.get(j).getStatdate())){
					data.setDate(date);
					if(searchData.getChecktype3().equals("money")){
						data.setValue(currlist.get(j).getIncomes());
					}
					if(searchData.getChecktype3().equals("times")){
						data.setValue(currlist.get(j).getPayCounts().doubleValue());
					}
					j=j+1;
				}else{
					data.setDate(date);
					data.setValue(0.0);
				}
				
				String new_date=dateList2.get(i);
				if(k<comparelist.size()&&new_date.equals(comparelist.get(k).getStatdate())){
					data.setNew_date(new_date);
					if(searchData.getChecktype3().equals("money")){
						data.setNew_value(comparelist.get(k).getIncomes());
					}
					if(searchData.getChecktype3().equals("times")){
						data.setNew_value(comparelist.get(k).getPayCounts().doubleValue());
					}
					k=k+1;
				}else{
					data.setNew_date(new_date);
					data.setNew_value(0.0);
				}
				list.add(data);
			}
			break;
		case 3:
			//月
			date1=DateUtil.add(endDate1, Calendar.DAY_OF_MONTH, -((count*30)-1));
			startTime=DateUtil.format_yyyy_MM_dd(date1);
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd(endDate1));
			searchData.setStartdate(startTime);
			currlist=dataCompareMapper.selectWeekOrMonthIncomeList(searchData);
			//对比日期的数据
			date2=DateUtil.add(endDate2, Calendar.DAY_OF_MONTH, -((count*30)-1));
			startTime2=DateUtil.format_yyyy_MM_dd(date2);
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd(endDate2));
			searchData.setStartdate(startTime2);
			comparelist=dataCompareMapper.selectWeekOrMonthIncomeList(searchData);
			
			
			Date c_endDate3=endDate1;
			Date c_endDate4=endDate2;
			//currlist数组显示日期处理
			if(currlist!=null){
				for(DataCompare d:currlist){
					if(d!=null){
						Date startDate=DateUtil.add(c_endDate3, Calendar.DAY_OF_MONTH, -29);
						String str=DateUtil.format_yyyy_MM_dd(startDate)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate3);
						d.setStatdate(str);
						c_endDate3=DateUtil.add(startDate, Calendar.DAY_OF_MONTH, -1);
					}
				}
			}
			//comparelist数组显示日期处理
			if(comparelist!=null){
				for(DataCompare d:comparelist){
					if(d!=null){
						Date startDate=DateUtil.add(c_endDate4, Calendar.DAY_OF_MONTH, -29);
						String str=DateUtil.format_yyyy_MM_dd(startDate)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate4);
						d.setStatdate(str);
						c_endDate4=DateUtil.add(startDate, Calendar.DAY_OF_MONTH, -1);
					}
				}
			}
			c_endDate3=endDate1;
			c_endDate4=endDate2;
			//月数组
			for(int i=0;i<count;i++){
				//当前时间的月字符串数组
				Date startDate=DateUtil.add(c_endDate3, Calendar.DAY_OF_MONTH, -29);
				String str=DateUtil.format_yyyy_MM_dd(startDate)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate3);
				dateList1.add(str);
				c_endDate3=DateUtil.add(startDate, Calendar.DAY_OF_MONTH, -1);
				//对比时间的周字符串数组
				Date startDate2=DateUtil.add(c_endDate4, Calendar.DAY_OF_MONTH, -29);
				String str2=DateUtil.format_yyyy_MM_dd(startDate2)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate4);
				dateList2.add(str2);
				c_endDate4=DateUtil.add(startDate2, Calendar.DAY_OF_MONTH, -1);
			}
			//currlist数组与comparelist数组合并
			for(int i=0;i<dateList1.size();i++){
				DataCompare data=new DataCompare();
				String date=dateList1.get(i);
				if(j<currlist.size()&&date.equals(currlist.get(j).getStatdate())){
					data.setDate(date);
					if(searchData.getChecktype3().equals("money")){
						data.setValue(currlist.get(j).getIncomes());
					}
					if(searchData.getChecktype3().equals("times")){
						data.setValue(currlist.get(j).getPayCounts().doubleValue());
					}
					
					j=j+1;
				}else{
					data.setDate(date);
					data.setValue(0.0);
				}
				
				String new_date=dateList2.get(i);
				if(k<comparelist.size()&&new_date.equals(comparelist.get(k).getStatdate())){
					data.setNew_date(new_date);
					if(searchData.getChecktype3().equals("money")){
						data.setNew_value(comparelist.get(k).getIncomes());
					}
					if(searchData.getChecktype3().equals("times")){
						data.setNew_value(comparelist.get(k).getPayCounts().doubleValue());
					}
					k=k+1;
				}else{
					data.setNew_date(new_date);
					data.setNew_value(0.0);
				}
				list.add(data);
			}
			break;
		}
		return list;
	}

	@Override
	public List<DataCompare> selectNewPlayerList(BaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		Date endDate1=DateUtil.parse_yyyy_MM_dd(searchData.getStartdate());//查询日期
		Date endDate2=DateUtil.parse_yyyy_MM_dd(searchData.getEnddate());//对比日期
		int count=Integer.parseInt(searchData.getChecktype2());//天数
		
		List<DataCompare> list=new ArrayList<DataCompare>();
		List<DataCompare> currlist=new ArrayList<DataCompare>();
		List<DataCompare> comparelist=new ArrayList<DataCompare>();
		
		List<String> dateList1=new ArrayList<String>();
		List<String> dateList2=new ArrayList<String>();
		
		Date date1=null;
		Date date2=null;
		String startTime=null;//
		String startTime2=null;//
		int j=0;
		int k=0;
		//类型
		switch (Integer.parseInt(searchData.getChecktype1())) {
		case 1:
			//日
			//查询日期数据
			date1=DateUtil.add(endDate1, Calendar.DAY_OF_MONTH, -(count-1));
			startTime=DateUtil.format_yyyy_MM_dd(date1);
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd(endDate1));
			searchData.setStartdate(startTime);
			currlist=dataCompareMapper.selectDailyNewPlayerList(searchData);
			//对比日期的数据
			date2=DateUtil.add(endDate2, Calendar.DAY_OF_MONTH, -(count-1));
			startTime2=DateUtil.format_yyyy_MM_dd(date2);
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd(endDate2));
			searchData.setStartdate(startTime2);
			comparelist=dataCompareMapper.selectDailyNewPlayerList(searchData);
			while(date1.getTime()<=endDate1.getTime()){
				String date=DateUtil.format_yyyy_MM_dd(date1);
				dateList1.add(date);
				date1=DateUtil.add(date1, Calendar.DAY_OF_MONTH, 1);
			}
			
			while(date2.getTime()<=endDate2.getTime()){
				String date=DateUtil.format_yyyy_MM_dd(date2);
				dateList2.add(date);
				date2=DateUtil.add(date2, Calendar.DAY_OF_MONTH, 1);
			}
			for(int i=0;i<dateList1.size();i++){
				
				DataCompare data=new DataCompare();
				String date=dateList1.get(i);
				if(j<currlist.size()&&date.equals(currlist.get(j).getStatdate())){
					data.setDate(date);
					data.setValue(currlist.get(j).getDevices().doubleValue());
					j=j+1;
				}else{
					data.setDate(date);
					data.setValue(0.0);
				}
				
				String new_date=dateList2.get(i);
				if(k<comparelist.size()&&new_date.equals(comparelist.get(k).getStatdate())){
					data.setNew_date(new_date);
					data.setNew_value(comparelist.get(k).getDevices().doubleValue());
					k=k+1;
				}else{
					data.setNew_date(new_date);
					data.setNew_value(0.0);
				}
				list.add(data);
			}
			break;
		case 2:
			//周
			//要查询数据
			date1=DateUtil.add(endDate1, Calendar.DAY_OF_MONTH, -((count*7)-1));
			startTime=DateUtil.format_yyyy_MM_dd(date1);
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd(endDate1));
			searchData.setStartdate(startTime);
			currlist=dataCompareMapper.selectWeekOrMonthNewPlayerList(searchData);
			
			//对比日期的数据
			date2=DateUtil.add(endDate2, Calendar.DAY_OF_MONTH, -((count*7)-1));
			startTime2=DateUtil.format_yyyy_MM_dd(date2);
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd(endDate2));
			searchData.setStartdate(startTime2);
			comparelist=dataCompareMapper.selectWeekOrMonthNewPlayerList(searchData);
			
			Date c_endDate1=endDate1;
			Date c_endDate2=endDate2;
			//currlist数组显示日期处理
			if(currlist!=null){
				for(DataCompare d:currlist){
					if(d!=null){
						Date startDate=DateUtil.add(c_endDate1, Calendar.DAY_OF_MONTH, -6);
						String str=DateUtil.format_yyyy_MM_dd(startDate)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate1);
						d.setStatdate(str);
						c_endDate1=DateUtil.add(startDate, Calendar.DAY_OF_MONTH, -1);
					}
				}
			}
			//comparelist数组显示日期处理
			if(comparelist!=null){
				for(DataCompare d:comparelist){
					if(d!=null){
						Date startDate=DateUtil.add(c_endDate2, Calendar.DAY_OF_MONTH, -6);
						String str=DateUtil.format_yyyy_MM_dd(startDate)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate2);
						d.setStatdate(str);
						c_endDate2=DateUtil.add(startDate, Calendar.DAY_OF_MONTH, -1);
					}
				}
			}
			c_endDate1=endDate1;
			c_endDate2=endDate2;
			//周数组
			for(int i=0;i<count;i++){
				//当前时间的周字符串数组
				Date startDate=DateUtil.add(c_endDate1, Calendar.DAY_OF_MONTH, -6);
				String str=DateUtil.format_yyyy_MM_dd(startDate)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate1);
				dateList1.add(str);
				c_endDate1=DateUtil.add(startDate, Calendar.DAY_OF_MONTH, -1);
				//对比时间的周字符串数组
				Date startDate2=DateUtil.add(c_endDate2, Calendar.DAY_OF_MONTH, -6);
				String str2=DateUtil.format_yyyy_MM_dd(startDate2)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate2);
				dateList2.add(str2);
				c_endDate2=DateUtil.add(startDate2, Calendar.DAY_OF_MONTH, -1);
			}
			//currlist数组与comparelist数组合并
			for(int i=0;i<dateList1.size();i++){
				DataCompare data=new DataCompare();
				String date=dateList1.get(i);
				if(j<currlist.size()&&date.equals(currlist.get(j).getStatdate())){
					data.setDate(date);
					data.setValue(currlist.get(j).getDevices().doubleValue());
					j=j+1;
				}else{
					data.setDate(date);
					data.setValue(0.0);
				}
				
				String new_date=dateList2.get(i);
				if(k<comparelist.size()&&new_date.equals(comparelist.get(k).getStatdate())){
					data.setNew_date(new_date);
					data.setNew_value(comparelist.get(k).getDevices().doubleValue());
					k=k+1;
				}else{
					data.setNew_date(new_date);
					data.setNew_value(0.0);
				}
				list.add(data);
			}
			break;
		case 3:
			//月
			date1=DateUtil.add(endDate1, Calendar.DAY_OF_MONTH, -((count*30)-1));
			startTime=DateUtil.format_yyyy_MM_dd(date1);
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd(endDate1));
			searchData.setStartdate(startTime);
			currlist=dataCompareMapper.selectWeekOrMonthNewPlayerList(searchData);
			//对比日期的数据
			date2=DateUtil.add(endDate2, Calendar.DAY_OF_MONTH, -((count*30)-1));
			startTime2=DateUtil.format_yyyy_MM_dd(date2);
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd(endDate2));
			searchData.setStartdate(startTime2);
			comparelist=dataCompareMapper.selectWeekOrMonthNewPlayerList(searchData);
			
			
			Date c_endDate3=endDate1;
			Date c_endDate4=endDate2;
			//currlist数组显示日期处理
			if(currlist!=null){
				for(DataCompare d:currlist){
					if(d!=null){
						Date startDate=DateUtil.add(c_endDate3, Calendar.DAY_OF_MONTH, -29);
						String str=DateUtil.format_yyyy_MM_dd(startDate)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate3);
						d.setStatdate(str);
						c_endDate3=DateUtil.add(startDate, Calendar.DAY_OF_MONTH, -1);
					}
				}
			}
			//comparelist数组显示日期处理
			if(comparelist!=null){
				for(DataCompare d:comparelist){
					if(d!=null){
						Date startDate=DateUtil.add(c_endDate4, Calendar.DAY_OF_MONTH, -29);
						String str=DateUtil.format_yyyy_MM_dd(startDate)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate4);
						d.setStatdate(str);
						c_endDate4=DateUtil.add(startDate, Calendar.DAY_OF_MONTH, -1);
					}
				}
			}
			c_endDate3=endDate1;
			c_endDate4=endDate2;
			//月数组
			for(int i=0;i<count;i++){
				//当前时间的月字符串数组
				Date startDate=DateUtil.add(c_endDate3, Calendar.DAY_OF_MONTH, -29);
				String str=DateUtil.format_yyyy_MM_dd(startDate)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate3);
				dateList1.add(str);
				c_endDate3=DateUtil.add(startDate, Calendar.DAY_OF_MONTH, -1);
				//对比时间的周字符串数组
				Date startDate2=DateUtil.add(c_endDate4, Calendar.DAY_OF_MONTH, -29);
				String str2=DateUtil.format_yyyy_MM_dd(startDate2)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate4);
				dateList2.add(str2);
				c_endDate4=DateUtil.add(startDate2, Calendar.DAY_OF_MONTH, -1);
			}
			//currlist数组与comparelist数组合并
			for(int i=0;i<dateList1.size();i++){
				DataCompare data=new DataCompare();
				String date=dateList1.get(i);
				if(j<currlist.size()&&date.equals(currlist.get(j).getStatdate())){
					data.setDate(date);
					data.setValue(currlist.get(j).getDevices().doubleValue());
					j=j+1;
				}else{
					data.setDate(date);
					data.setValue(0.0);
				}
				
				String new_date=dateList2.get(i);
				if(k<comparelist.size()&&new_date.equals(comparelist.get(k).getStatdate())){
					data.setNew_date(new_date);
					data.setNew_value(comparelist.get(k).getDevices().doubleValue());
					k=k+1;
				}else{
					data.setNew_date(new_date);
					data.setNew_value(0.0);
				}
				list.add(data);
			}
			break;
		}
		return list;
	}

	@Override
	public List<DataCompare> selectPayPlayerList(BaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		Date endDate1=DateUtil.parse_yyyy_MM_dd(searchData.getStartdate());//查询日期
		Date endDate2=DateUtil.parse_yyyy_MM_dd(searchData.getEnddate());//对比日期
		int count=Integer.parseInt(searchData.getChecktype2());//天数
		
		List<DataCompare> list=new ArrayList<DataCompare>();
		List<DataCompare> currlist=new ArrayList<DataCompare>();
		List<DataCompare> comparelist=new ArrayList<DataCompare>();
		
		List<String> dateList1=new ArrayList<String>();
		List<String> dateList2=new ArrayList<String>();
		
		Date date1=null;
		Date date2=null;
		String startTime=null;//
		String startTime2=null;//
		int j=0;
		int k=0;
		//类型
		switch (Integer.parseInt(searchData.getChecktype1())) {
		case 1:
			//日
			//查询日期数据
			date1=DateUtil.add(endDate1, Calendar.DAY_OF_MONTH, -(count-1));
			startTime=DateUtil.format_yyyy_MM_dd(date1);
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd(endDate1));
			searchData.setStartdate(startTime);
			currlist=dataCompareMapper.selectDailyPayPlayerList(searchData);
			//对比日期的数据
			date2=DateUtil.add(endDate2, Calendar.DAY_OF_MONTH, -(count-1));
			startTime2=DateUtil.format_yyyy_MM_dd(date2);
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd(endDate2));
			searchData.setStartdate(startTime2);
			comparelist=dataCompareMapper.selectDailyPayPlayerList(searchData);
			while(date1.getTime()<=endDate1.getTime()){
				String date=DateUtil.format_yyyy_MM_dd(date1);
				dateList1.add(date);
				date1=DateUtil.add(date1, Calendar.DAY_OF_MONTH, 1);
			}
			
			while(date2.getTime()<=endDate2.getTime()){
				String date=DateUtil.format_yyyy_MM_dd(date2);
				dateList2.add(date);
				date2=DateUtil.add(date2, Calendar.DAY_OF_MONTH, 1);
			}
			for(int i=0;i<dateList1.size();i++){
				
				DataCompare data=new DataCompare();
				String date=dateList1.get(i);
				if(j<currlist.size()&&date.equals(currlist.get(j).getStatdate())){
					data.setDate(date);
					if(searchData.getChecktype3().equals("newPayUser")){
						data.setValue(currlist.get(j).getNewPayUser().doubleValue());
					}
					if(searchData.getChecktype3().equals("totalPayUser")){
						data.setValue(currlist.get(j).getTotalPayUser().doubleValue());
					}
					j=j+1;
				}else{
					data.setDate(date);
					data.setValue(0.0);
				}
				
				String new_date=dateList2.get(i);
				if(k<comparelist.size()&&new_date.equals(comparelist.get(k).getStatdate())){
					data.setNew_date(new_date);
					if(searchData.getChecktype3().equals("newPayUser")){
						data.setNew_value(comparelist.get(k).getNewPayUser().doubleValue());
					}
					if(searchData.getChecktype3().equals("totalPayUser")){
						data.setNew_value(comparelist.get(k).getTotalPayUser().doubleValue());
					}
					k=k+1;
				}else{
					data.setNew_date(new_date);
					data.setNew_value(0.0);
				}
				list.add(data);
			}
			break;
		case 2:
			//周
			//要查询数据
			date1=DateUtil.add(endDate1, Calendar.DAY_OF_MONTH, -((count*7)-1));
			startTime=DateUtil.format_yyyy_MM_dd(date1);
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd(endDate1));
			searchData.setStartdate(startTime);
			currlist=dataCompareMapper.selectWeekAndMonthPayPlayerList(searchData);
			
			//对比日期的数据
			date2=DateUtil.add(endDate2, Calendar.DAY_OF_MONTH, -((count*7)-1));
			startTime2=DateUtil.format_yyyy_MM_dd(date2);
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd(endDate2));
			searchData.setStartdate(startTime2);
			comparelist=dataCompareMapper.selectWeekAndMonthPayPlayerList(searchData);
			
			Date c_endDate1=endDate1;
			Date c_endDate2=endDate2;
			//currlist数组显示日期处理
			if(currlist!=null){
				for(DataCompare d:currlist){
					if(d!=null){
						Date startDate=DateUtil.add(c_endDate1, Calendar.DAY_OF_MONTH, -6);
						String str=DateUtil.format_yyyy_MM_dd(startDate)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate1);
						d.setStatdate(str);
						c_endDate1=DateUtil.add(startDate, Calendar.DAY_OF_MONTH, -1);
					}
				}
			}
			//comparelist数组显示日期处理
			if(comparelist!=null){
				for(DataCompare d:comparelist){
					if(d!=null){
						Date startDate=DateUtil.add(c_endDate2, Calendar.DAY_OF_MONTH, -6);
						String str=DateUtil.format_yyyy_MM_dd(startDate)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate2);
						d.setStatdate(str);
						c_endDate2=DateUtil.add(startDate, Calendar.DAY_OF_MONTH, -1);
					}
				}
			}
			c_endDate1=endDate1;
			c_endDate2=endDate2;
			//周数组
			for(int i=0;i<count;i++){
				//当前时间的周字符串数组
				Date startDate=DateUtil.add(c_endDate1, Calendar.DAY_OF_MONTH, -6);
				String str=DateUtil.format_yyyy_MM_dd(startDate)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate1);
				dateList1.add(str);
				c_endDate1=DateUtil.add(startDate, Calendar.DAY_OF_MONTH, -1);
				//对比时间的周字符串数组
				Date startDate2=DateUtil.add(c_endDate2, Calendar.DAY_OF_MONTH, -6);
				String str2=DateUtil.format_yyyy_MM_dd(startDate2)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate2);
				dateList2.add(str2);
				c_endDate2=DateUtil.add(startDate2, Calendar.DAY_OF_MONTH, -1);
			}
			//currlist数组与comparelist数组合并
			for(int i=0;i<dateList1.size();i++){
				DataCompare data=new DataCompare();
				String date=dateList1.get(i);
				if(j<currlist.size()&&date.equals(currlist.get(j).getStatdate())){
					data.setDate(date);
					if(searchData.getChecktype3().equals("newPayUser")){
						data.setValue(currlist.get(j).getNewPayUser().doubleValue());
					}
					if(searchData.getChecktype3().equals("totalPayUser")){
						data.setValue(currlist.get(j).getTotalPayUser().doubleValue());
					}
					j=j+1;
				}else{
					data.setDate(date);
					data.setValue(0.0);
				}
				
				String new_date=dateList2.get(i);
				if(k<comparelist.size()&&new_date.equals(comparelist.get(k).getStatdate())){
					data.setNew_date(new_date);
					if(searchData.getChecktype3().equals("newPayUser")){
						data.setNew_value(comparelist.get(k).getNewPayUser().doubleValue());
					}
					if(searchData.getChecktype3().equals("totalPayUser")){
						data.setNew_value(comparelist.get(k).getTotalPayUser().doubleValue());
					}
					k=k+1;
				}else{
					data.setNew_date(new_date);
					data.setNew_value(0.0);
				}
				list.add(data);
			}
			break;
		case 3:
			//月
			date1=DateUtil.add(endDate1, Calendar.DAY_OF_MONTH, -((count*30)-1));
			startTime=DateUtil.format_yyyy_MM_dd(date1);
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd(endDate1));
			searchData.setStartdate(startTime);
			currlist=dataCompareMapper.selectWeekAndMonthPayPlayerList(searchData);
			//对比日期的数据
			date2=DateUtil.add(endDate2, Calendar.DAY_OF_MONTH, -((count*30)-1));
			startTime2=DateUtil.format_yyyy_MM_dd(date2);
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd(endDate2));
			searchData.setStartdate(startTime2);
			comparelist=dataCompareMapper.selectWeekAndMonthPayPlayerList(searchData);
			
			
			Date c_endDate3=endDate1;
			Date c_endDate4=endDate2;
			//currlist数组显示日期处理
			if(currlist!=null){
				for(DataCompare d:currlist){
					if(d!=null){
						Date startDate=DateUtil.add(c_endDate3, Calendar.DAY_OF_MONTH, -29);
						String str=DateUtil.format_yyyy_MM_dd(startDate)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate3);
						d.setStatdate(str);
						c_endDate3=DateUtil.add(startDate, Calendar.DAY_OF_MONTH, -1);
					}
				}
			}
			//comparelist数组显示日期处理
			if(comparelist!=null){
				for(DataCompare d:comparelist){
					if(d!=null){
						Date startDate=DateUtil.add(c_endDate4, Calendar.DAY_OF_MONTH, -29);
						String str=DateUtil.format_yyyy_MM_dd(startDate)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate4);
						d.setStatdate(str);
						c_endDate4=DateUtil.add(startDate, Calendar.DAY_OF_MONTH, -1);
					}
				}
			}
			c_endDate3=endDate1;
			c_endDate4=endDate2;
			//月数组
			for(int i=0;i<count;i++){
				//当前时间的月字符串数组
				Date startDate=DateUtil.add(c_endDate3, Calendar.DAY_OF_MONTH, -29);
				String str=DateUtil.format_yyyy_MM_dd(startDate)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate3);
				dateList1.add(str);
				c_endDate3=DateUtil.add(startDate, Calendar.DAY_OF_MONTH, -1);
				//对比时间的周字符串数组
				Date startDate2=DateUtil.add(c_endDate4, Calendar.DAY_OF_MONTH, -29);
				String str2=DateUtil.format_yyyy_MM_dd(startDate2)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate4);
				dateList2.add(str2);
				c_endDate4=DateUtil.add(startDate2, Calendar.DAY_OF_MONTH, -1);
			}
			//currlist数组与comparelist数组合并
			for(int i=0;i<dateList1.size();i++){
				DataCompare data=new DataCompare();
				String date=dateList1.get(i);
				if(j<currlist.size()&&date.equals(currlist.get(j).getStatdate())){
					data.setDate(date);
					if(searchData.getChecktype3().equals("newPayUser")){
						data.setValue(currlist.get(j).getNewPayUser().doubleValue());
					}
					if(searchData.getChecktype3().equals("totalPayUser")){
						data.setValue(currlist.get(j).getTotalPayUser().doubleValue());
					}
					j=j+1;
				}else{
					data.setDate(date);
					data.setValue(0.0);
				}
				
				String new_date=dateList2.get(i);
				if(k<comparelist.size()&&new_date.equals(comparelist.get(k).getStatdate())){
					data.setNew_date(new_date);
					if(searchData.getChecktype3().equals("newPayUser")){
						data.setNew_value(comparelist.get(k).getNewPayUser().doubleValue());
					}
					if(searchData.getChecktype3().equals("totalPayUser")){
						data.setNew_value(comparelist.get(k).getTotalPayUser().doubleValue());
					}
					k=k+1;
				}else{
					data.setNew_date(new_date);
					data.setNew_value(0.0);
				}
				list.add(data);
			}
			break;
		}
		return list;
	}

	@Override
	public List<DataCompare> selectPayRateList(BaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		Date endDate1=DateUtil.parse_yyyy_MM_dd(searchData.getStartdate());//查询日期
		Date endDate2=DateUtil.parse_yyyy_MM_dd(searchData.getEnddate());//对比日期
		int count=Integer.parseInt(searchData.getChecktype2());//天数
		
		List<DataCompare> list=new ArrayList<DataCompare>();
		List<DataCompare> currlist=new ArrayList<DataCompare>();
		List<DataCompare> comparelist=new ArrayList<DataCompare>();
		
		List<String> dateList1=new ArrayList<String>();
		List<String> dateList2=new ArrayList<String>();
		
		Date date1=null;
		Date date2=null;
		String startTime=null;//
		String startTime2=null;//
		int j=0;
		int k=0;
		//类型
		switch (Integer.parseInt(searchData.getChecktype1())) {
		case 1:
			//日
			//查询日期数据
			date1=DateUtil.add(endDate1, Calendar.DAY_OF_MONTH, -(count-1));
			startTime=DateUtil.format_yyyy_MM_dd(date1);
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd(endDate1));
			searchData.setStartdate(startTime);
			currlist=dataCompareMapper.selectDailyPayRateList(searchData);
			//对比日期的数据
			date2=DateUtil.add(endDate2, Calendar.DAY_OF_MONTH, -(count-1));
			startTime2=DateUtil.format_yyyy_MM_dd(date2);
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd(endDate2));
			searchData.setStartdate(startTime2);
			comparelist=dataCompareMapper.selectDailyPayRateList(searchData);
			while(date1.getTime()<=endDate1.getTime()){
				String date=DateUtil.format_yyyy_MM_dd(date1);
				dateList1.add(date);
				date1=DateUtil.add(date1, Calendar.DAY_OF_MONTH, 1);
			}
			
			while(date2.getTime()<=endDate2.getTime()){
				String date=DateUtil.format_yyyy_MM_dd(date2);
				dateList2.add(date);
				date2=DateUtil.add(date2, Calendar.DAY_OF_MONTH, 1);
			}
			for(int i=0;i<dateList1.size();i++){
				
				DataCompare data=new DataCompare();
				String date=dateList1.get(i);
				if(j<currlist.size()&&date.equals(currlist.get(j).getStatdate())){
					data.setDate(date);
					data.setValue(currlist.get(j).getPayRate());
					j=j+1;
				}else{
					data.setDate(date);
					data.setValue(0.0);
				}
				
				String new_date=dateList2.get(i);
				if(k<comparelist.size()&&new_date.equals(comparelist.get(k).getStatdate())){
					data.setNew_date(new_date);
					data.setNew_value(comparelist.get(k).getPayRate());
					k=k+1;
				}else{
					data.setNew_date(new_date);
					data.setNew_value(0.0);
				}
				list.add(data);
			}
			break;
		case 2:
			//周
			//要查询数据
			date1=DateUtil.add(endDate1, Calendar.DAY_OF_MONTH, -((count*7)-1));
			startTime=DateUtil.format_yyyy_MM_dd(date1);
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd(endDate1));
			searchData.setStartdate(startTime);
			currlist=dataCompareMapper.selectWeekAndMonthPayRateList(searchData);
			
			//对比日期的数据
			date2=DateUtil.add(endDate2, Calendar.DAY_OF_MONTH, -((count*7)-1));
			startTime2=DateUtil.format_yyyy_MM_dd(date2);
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd(endDate2));
			searchData.setStartdate(startTime2);
			comparelist=dataCompareMapper.selectWeekAndMonthPayRateList(searchData);
			
			Date c_endDate1=endDate1;
			Date c_endDate2=endDate2;
			//currlist数组显示日期处理
			if(currlist!=null){
				for(DataCompare d:currlist){
					if(d!=null){
						Date startDate=DateUtil.add(c_endDate1, Calendar.DAY_OF_MONTH, -6);
						String str=DateUtil.format_yyyy_MM_dd(startDate)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate1);
						d.setStatdate(str);
						c_endDate1=DateUtil.add(startDate, Calendar.DAY_OF_MONTH, -1);
					}
				}
			}
			//comparelist数组显示日期处理
			if(comparelist!=null){
				for(DataCompare d:comparelist){
					if(d!=null){
						Date startDate=DateUtil.add(c_endDate2, Calendar.DAY_OF_MONTH, -6);
						String str=DateUtil.format_yyyy_MM_dd(startDate)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate2);
						d.setStatdate(str);
						c_endDate2=DateUtil.add(startDate, Calendar.DAY_OF_MONTH, -1);
					}
				}
			}
			c_endDate1=endDate1;
			c_endDate2=endDate2;
			//周数组
			for(int i=0;i<count;i++){
				//当前时间的周字符串数组
				Date startDate=DateUtil.add(c_endDate1, Calendar.DAY_OF_MONTH, -6);
				String str=DateUtil.format_yyyy_MM_dd(startDate)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate1);
				dateList1.add(str);
				c_endDate1=DateUtil.add(startDate, Calendar.DAY_OF_MONTH, -1);
				//对比时间的周字符串数组
				Date startDate2=DateUtil.add(c_endDate2, Calendar.DAY_OF_MONTH, -6);
				String str2=DateUtil.format_yyyy_MM_dd(startDate2)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate2);
				dateList2.add(str2);
				c_endDate2=DateUtil.add(startDate2, Calendar.DAY_OF_MONTH, -1);
			}
			//currlist数组与comparelist数组合并
			for(int i=0;i<dateList1.size();i++){
				DataCompare data=new DataCompare();
				String date=dateList1.get(i);
				if(j<currlist.size()&&date.equals(currlist.get(j).getStatdate())){
					data.setDate(date);
					data.setValue(currlist.get(j).getPayRate());
					j=j+1;
				}else{
					data.setDate(date);
					data.setValue(0.0);
				}
				
				String new_date=dateList2.get(i);
				if(k<comparelist.size()&&new_date.equals(comparelist.get(k).getStatdate())){
					data.setNew_date(new_date);
					data.setNew_value(comparelist.get(k).getPayRate());
					k=k+1;
				}else{
					data.setNew_date(new_date);
					data.setNew_value(0.0);
				}
				list.add(data);
			}
			break;
		case 3:
			//月
			date1=DateUtil.add(endDate1, Calendar.DAY_OF_MONTH, -((count*30)-1));
			startTime=DateUtil.format_yyyy_MM_dd(date1);
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd(endDate1));
			searchData.setStartdate(startTime);
			currlist=dataCompareMapper.selectWeekAndMonthPayRateList(searchData);
			//对比日期的数据
			date2=DateUtil.add(endDate2, Calendar.DAY_OF_MONTH, -((count*30)-1));
			startTime2=DateUtil.format_yyyy_MM_dd(date2);
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd(endDate2));
			searchData.setStartdate(startTime2);
			comparelist=dataCompareMapper.selectWeekAndMonthPayRateList(searchData);
			
			
			Date c_endDate3=endDate1;
			Date c_endDate4=endDate2;
			//currlist数组显示日期处理
			if(currlist!=null){
				for(DataCompare d:currlist){
					if(d!=null){
						Date startDate=DateUtil.add(c_endDate3, Calendar.DAY_OF_MONTH, -29);
						String str=DateUtil.format_yyyy_MM_dd(startDate)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate3);
						d.setStatdate(str);
						c_endDate3=DateUtil.add(startDate, Calendar.DAY_OF_MONTH, -1);
					}
				}
			}
			//comparelist数组显示日期处理
			if(comparelist!=null){
				for(DataCompare d:comparelist){
					if(d!=null){
						Date startDate=DateUtil.add(c_endDate4, Calendar.DAY_OF_MONTH, -29);
						String str=DateUtil.format_yyyy_MM_dd(startDate)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate4);
						d.setStatdate(str);
						c_endDate4=DateUtil.add(startDate, Calendar.DAY_OF_MONTH, -1);
					}
				}
			}
			c_endDate3=endDate1;
			c_endDate4=endDate2;
			//月数组
			for(int i=0;i<count;i++){
				//当前时间的月字符串数组
				Date startDate=DateUtil.add(c_endDate3, Calendar.DAY_OF_MONTH, -29);
				String str=DateUtil.format_yyyy_MM_dd(startDate)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate3);
				dateList1.add(str);
				c_endDate3=DateUtil.add(startDate, Calendar.DAY_OF_MONTH, -1);
				//对比时间的周字符串数组
				Date startDate2=DateUtil.add(c_endDate4, Calendar.DAY_OF_MONTH, -29);
				String str2=DateUtil.format_yyyy_MM_dd(startDate2)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate4);
				dateList2.add(str2);
				c_endDate4=DateUtil.add(startDate2, Calendar.DAY_OF_MONTH, -1);
			}
			//currlist数组与comparelist数组合并
			for(int i=0;i<dateList1.size();i++){
				DataCompare data=new DataCompare();
				String date=dateList1.get(i);
				if(j<currlist.size()&&date.equals(currlist.get(j).getStatdate())){
					data.setDate(date);
					data.setValue(currlist.get(j).getPayRate());
					j=j+1;
				}else{
					data.setDate(date);
					data.setValue(0.0);
				}
				
				String new_date=dateList2.get(i);
				if(k<comparelist.size()&&new_date.equals(comparelist.get(k).getStatdate())){
					data.setNew_date(new_date);
					data.setNew_value(comparelist.get(k).getPayRate());
					k=k+1;
				}else{
					data.setNew_date(new_date);
					data.setNew_value(0.0);
				}
				list.add(data);
			}
			break;
		}
		return list;
	}

	@Override
	public List<DataCompare> selectDay1RetainRateList(BaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		Date endDate1=DateUtil.parse_yyyy_MM_dd(searchData.getStartdate());//查询日期
		Date endDate2=DateUtil.parse_yyyy_MM_dd(searchData.getEnddate());//对比日期
		int count=Integer.parseInt(searchData.getChecktype2());//天数
		
		List<DataCompare> list=new ArrayList<DataCompare>();
		List<DataCompare> currlist=new ArrayList<DataCompare>();
		List<DataCompare> comparelist=new ArrayList<DataCompare>();
		
		List<String> dateList1=new ArrayList<String>();
		List<String> dateList2=new ArrayList<String>();
		
		Date date1=null;
		Date date2=null;
		String startTime=null;//
		String startTime2=null;//
		int j=0;
		int k=0;
		//类型
		switch (Integer.parseInt(searchData.getChecktype1())) {
		case 1:
			//日
			//查询日期数据
			date1=DateUtil.add(endDate1, Calendar.DAY_OF_MONTH, -(count-1));
			startTime=DateUtil.format_yyyy_MM_dd(date1);
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd(endDate1));
			searchData.setStartdate(startTime);
			currlist=dataCompareMapper.selectDailyDay1RetRateList(searchData);
			//对比日期的数据
			date2=DateUtil.add(endDate2, Calendar.DAY_OF_MONTH, -(count-1));
			startTime2=DateUtil.format_yyyy_MM_dd(date2);
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd(endDate2));
			searchData.setStartdate(startTime2);
			comparelist=dataCompareMapper.selectDailyDay1RetRateList(searchData);
			while(date1.getTime()<=endDate1.getTime()){
				String date=DateUtil.format_yyyy_MM_dd(date1);
				dateList1.add(date);
				date1=DateUtil.add(date1, Calendar.DAY_OF_MONTH, 1);
			}
			
			while(date2.getTime()<=endDate2.getTime()){
				String date=DateUtil.format_yyyy_MM_dd(date2);
				dateList2.add(date);
				date2=DateUtil.add(date2, Calendar.DAY_OF_MONTH, 1);
			}
			for(int i=0;i<dateList1.size();i++){
				
				DataCompare data=new DataCompare();
				String date=dateList1.get(i);
				if(j<currlist.size()&&date.equals(currlist.get(j).getStatdate())){
					data.setDate(date);
					data.setValue(currlist.get(j).getDay1Rate());
					j=j+1;
				}else{
					data.setDate(date);
					data.setValue(0.0);
				}
				
				String new_date=dateList2.get(i);
				if(k<comparelist.size()&&new_date.equals(comparelist.get(k).getStatdate())){
					data.setNew_date(new_date);
					data.setNew_value(comparelist.get(k).getDay1Rate());
					k=k+1;
				}else{
					data.setNew_date(new_date);
					data.setNew_value(0.0);
				}
				list.add(data);
			}
			break;
		case 2:
			//周
			//要查询数据
			date1=DateUtil.add(endDate1, Calendar.DAY_OF_MONTH, -((count*7)-1));
			startTime=DateUtil.format_yyyy_MM_dd(date1);
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd(endDate1));
			searchData.setStartdate(startTime);
			currlist=dataCompareMapper.selectWeekAndMonthDay1RetRateList(searchData);
			
			//对比日期的数据
			date2=DateUtil.add(endDate2, Calendar.DAY_OF_MONTH, -((count*7)-1));
			startTime2=DateUtil.format_yyyy_MM_dd(date2);
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd(endDate2));
			searchData.setStartdate(startTime2);
			comparelist=dataCompareMapper.selectWeekAndMonthDay1RetRateList(searchData);
			
			Date c_endDate1=endDate1;
			Date c_endDate2=endDate2;
			//currlist数组显示日期处理
			if(currlist!=null){
				for(DataCompare d:currlist){
					if(d!=null){
						Date startDate=DateUtil.add(c_endDate1, Calendar.DAY_OF_MONTH, -6);
						String str=DateUtil.format_yyyy_MM_dd(startDate)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate1);
						d.setStatdate(str);
						c_endDate1=DateUtil.add(startDate, Calendar.DAY_OF_MONTH, -1);
					}
				}
			}
			//comparelist数组显示日期处理
			if(comparelist!=null){
				for(DataCompare d:comparelist){
					if(d!=null){
						Date startDate=DateUtil.add(c_endDate2, Calendar.DAY_OF_MONTH, -6);
						String str=DateUtil.format_yyyy_MM_dd(startDate)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate2);
						d.setStatdate(str);
						c_endDate2=DateUtil.add(startDate, Calendar.DAY_OF_MONTH, -1);
					}
				}
			}
			c_endDate1=endDate1;
			c_endDate2=endDate2;
			//周数组
			for(int i=0;i<count;i++){
				//当前时间的周字符串数组
				Date startDate=DateUtil.add(c_endDate1, Calendar.DAY_OF_MONTH, -6);
				String str=DateUtil.format_yyyy_MM_dd(startDate)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate1);
				dateList1.add(str);
				c_endDate1=DateUtil.add(startDate, Calendar.DAY_OF_MONTH, -1);
				//对比时间的周字符串数组
				Date startDate2=DateUtil.add(c_endDate2, Calendar.DAY_OF_MONTH, -6);
				String str2=DateUtil.format_yyyy_MM_dd(startDate2)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate2);
				dateList2.add(str2);
				c_endDate2=DateUtil.add(startDate2, Calendar.DAY_OF_MONTH, -1);
			}
			//currlist数组与comparelist数组合并
			for(int i=0;i<dateList1.size();i++){
				DataCompare data=new DataCompare();
				String date=dateList1.get(i);
				if(j<currlist.size()&&date.equals(currlist.get(j).getStatdate())){
					data.setDate(date);
					data.setValue(currlist.get(j).getDay1Rate());
					j=j+1;
				}else{
					data.setDate(date);
					data.setValue(0.0);
				}
				
				String new_date=dateList2.get(i);
				if(k<comparelist.size()&&new_date.equals(comparelist.get(k).getStatdate())){
					data.setNew_date(new_date);
					data.setNew_value(comparelist.get(k).getDay1Rate());
					k=k+1;
				}else{
					data.setNew_date(new_date);
					data.setNew_value(0.0);
				}
				list.add(data);
			}
			break;
		case 3:
			//月
			date1=DateUtil.add(endDate1, Calendar.DAY_OF_MONTH, -((count*30)-1));
			startTime=DateUtil.format_yyyy_MM_dd(date1);
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd(endDate1));
			searchData.setStartdate(startTime);
			currlist=dataCompareMapper.selectWeekAndMonthDay1RetRateList(searchData);
			//对比日期的数据
			date2=DateUtil.add(endDate2, Calendar.DAY_OF_MONTH, -((count*30)-1));
			startTime2=DateUtil.format_yyyy_MM_dd(date2);
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd(endDate2));
			searchData.setStartdate(startTime2);
			comparelist=dataCompareMapper.selectWeekAndMonthDay1RetRateList(searchData);
			
			
			Date c_endDate3=endDate1;
			Date c_endDate4=endDate2;
			//currlist数组显示日期处理
			if(currlist!=null){
				for(DataCompare d:currlist){
					if(d!=null){
						Date startDate=DateUtil.add(c_endDate3, Calendar.DAY_OF_MONTH, -29);
						String str=DateUtil.format_yyyy_MM_dd(startDate)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate3);
						d.setStatdate(str);
						c_endDate3=DateUtil.add(startDate, Calendar.DAY_OF_MONTH, -1);
					}
				}
			}
			//comparelist数组显示日期处理
			if(comparelist!=null){
				for(DataCompare d:comparelist){
					if(d!=null){
						Date startDate=DateUtil.add(c_endDate4, Calendar.DAY_OF_MONTH, -29);
						String str=DateUtil.format_yyyy_MM_dd(startDate)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate4);
						d.setStatdate(str);
						c_endDate4=DateUtil.add(startDate, Calendar.DAY_OF_MONTH, -1);
					}
				}
			}
			c_endDate3=endDate1;
			c_endDate4=endDate2;
			//月数组
			for(int i=0;i<count;i++){
				//当前时间的月字符串数组
				Date startDate=DateUtil.add(c_endDate3, Calendar.DAY_OF_MONTH, -29);
				String str=DateUtil.format_yyyy_MM_dd(startDate)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate3);
				dateList1.add(str);
				c_endDate3=DateUtil.add(startDate, Calendar.DAY_OF_MONTH, -1);
				//对比时间的周字符串数组
				Date startDate2=DateUtil.add(c_endDate4, Calendar.DAY_OF_MONTH, -29);
				String str2=DateUtil.format_yyyy_MM_dd(startDate2)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate4);
				dateList2.add(str2);
				c_endDate4=DateUtil.add(startDate2, Calendar.DAY_OF_MONTH, -1);
			}
			//currlist数组与comparelist数组合并
			for(int i=0;i<dateList1.size();i++){
				DataCompare data=new DataCompare();
				String date=dateList1.get(i);
				if(j<currlist.size()&&date.equals(currlist.get(j).getStatdate())){
					data.setDate(date);
					data.setValue(currlist.get(j).getDay1Rate());
					j=j+1;
				}else{
					data.setDate(date);
					data.setValue(0.0);
				}
				
				String new_date=dateList2.get(i);
				if(k<comparelist.size()&&new_date.equals(comparelist.get(k).getStatdate())){
					data.setNew_date(new_date);
					data.setNew_value(comparelist.get(k).getDay1Rate());
					k=k+1;
				}else{
					data.setNew_date(new_date);
					data.setNew_value(0.0);
				}
				list.add(data);
			}
			break;
		}
		return list;
	}

	@Override
	public List<DataCompare> selectAcuDivPcuList(BaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		Date endDate1=DateUtil.parse_yyyy_MM_dd(searchData.getStartdate());//查询日期
		Date endDate2=DateUtil.parse_yyyy_MM_dd(searchData.getEnddate());//对比日期
		int count=Integer.parseInt(searchData.getChecktype2());//天数
		
		List<DataCompare> list=new ArrayList<DataCompare>();
		List<DataCompare> currlist=new ArrayList<DataCompare>();
		List<DataCompare> comparelist=new ArrayList<DataCompare>();
		
		List<String> dateList1=new ArrayList<String>();
		List<String> dateList2=new ArrayList<String>();
		
		Date date1=null;
		Date date2=null;
		String startTime=null;//
		String startTime2=null;//
		int j=0;
		int k=0;
		//类型
		switch (Integer.parseInt(searchData.getChecktype1())) {
		case 1:
			//日
			//查询日期数据
			date1=DateUtil.add(endDate1, Calendar.DAY_OF_MONTH, -(count-1));
			startTime=DateUtil.format_yyyy_MM_dd(date1);
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd(endDate1));
			searchData.setStartdate(startTime);
			currlist=dataCompareMapper.selectDailyAcuDivPcuList(searchData);
			//对比日期的数据
			date2=DateUtil.add(endDate2, Calendar.DAY_OF_MONTH, -(count-1));
			startTime2=DateUtil.format_yyyy_MM_dd(date2);
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd(endDate2));
			searchData.setStartdate(startTime2);
			comparelist=dataCompareMapper.selectDailyAcuDivPcuList(searchData);
			while(date1.getTime()<=endDate1.getTime()){
				String date=DateUtil.format_yyyy_MM_dd(date1);
				dateList1.add(date);
				date1=DateUtil.add(date1, Calendar.DAY_OF_MONTH, 1);
			}
			
			while(date2.getTime()<=endDate2.getTime()){
				String date=DateUtil.format_yyyy_MM_dd(date2);
				dateList2.add(date);
				date2=DateUtil.add(date2, Calendar.DAY_OF_MONTH, 1);
			}
			for(int i=0;i<dateList1.size();i++){
				
				DataCompare data=new DataCompare();
				String date=dateList1.get(i);
				if(j<currlist.size()&&date.equals(currlist.get(j).getStatdate())){
					data.setDate(date);
					data.setValue(currlist.get(j).getAcuRate());
					j=j+1;
				}else{
					data.setDate(date);
					data.setValue(0.0);
				}
				
				String new_date=dateList2.get(i);
				if(k<comparelist.size()&&new_date.equals(comparelist.get(k).getStatdate())){
					data.setNew_date(new_date);
					data.setNew_value(comparelist.get(k).getAcuRate());
					k=k+1;
				}else{
					data.setNew_date(new_date);
					data.setNew_value(0.0);
				}
				list.add(data);
			}
			break;
		case 2:
			//周
			//要查询数据
			date1=DateUtil.add(endDate1, Calendar.DAY_OF_MONTH, -((count*7)-1));
			startTime=DateUtil.format_yyyy_MM_dd(date1);
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd(endDate1));
			searchData.setStartdate(startTime);
			currlist=dataCompareMapper.selectWeekAndMonthAcuDivPcuList(searchData);
			
			//对比日期的数据
			date2=DateUtil.add(endDate2, Calendar.DAY_OF_MONTH, -((count*7)-1));
			startTime2=DateUtil.format_yyyy_MM_dd(date2);
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd(endDate2));
			searchData.setStartdate(startTime2);
			comparelist=dataCompareMapper.selectWeekAndMonthAcuDivPcuList(searchData);
			
			Date c_endDate1=endDate1;
			Date c_endDate2=endDate2;
			//currlist数组显示日期处理
			if(currlist!=null){
				for(DataCompare d:currlist){
					if(d!=null){
						Date startDate=DateUtil.add(c_endDate1, Calendar.DAY_OF_MONTH, -6);
						String str=DateUtil.format_yyyy_MM_dd(startDate)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate1);
						d.setStatdate(str);
						c_endDate1=DateUtil.add(startDate, Calendar.DAY_OF_MONTH, -1);
					}
				}
			}
			//comparelist数组显示日期处理
			if(comparelist!=null){
				for(DataCompare d:comparelist){
					if(d!=null){
						Date startDate=DateUtil.add(c_endDate2, Calendar.DAY_OF_MONTH, -6);
						String str=DateUtil.format_yyyy_MM_dd(startDate)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate2);
						d.setStatdate(str);
						c_endDate2=DateUtil.add(startDate, Calendar.DAY_OF_MONTH, -1);
					}
				}
			}
			c_endDate1=endDate1;
			c_endDate2=endDate2;
			//周数组
			for(int i=0;i<count;i++){
				//当前时间的周字符串数组
				Date startDate=DateUtil.add(c_endDate1, Calendar.DAY_OF_MONTH, -6);
				String str=DateUtil.format_yyyy_MM_dd(startDate)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate1);
				dateList1.add(str);
				c_endDate1=DateUtil.add(startDate, Calendar.DAY_OF_MONTH, -1);
				//对比时间的周字符串数组
				Date startDate2=DateUtil.add(c_endDate2, Calendar.DAY_OF_MONTH, -6);
				String str2=DateUtil.format_yyyy_MM_dd(startDate2)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate2);
				dateList2.add(str2);
				c_endDate2=DateUtil.add(startDate2, Calendar.DAY_OF_MONTH, -1);
			}
			//currlist数组与comparelist数组合并
			for(int i=0;i<dateList1.size();i++){
				DataCompare data=new DataCompare();
				String date=dateList1.get(i);
				if(j<currlist.size()&&date.equals(currlist.get(j).getStatdate())){
					data.setDate(date);
					data.setValue(currlist.get(j).getAcuRate());
					j=j+1;
				}else{
					data.setDate(date);
					data.setValue(0.0);
				}
				
				String new_date=dateList2.get(i);
				if(k<comparelist.size()&&new_date.equals(comparelist.get(k).getStatdate())){
					data.setNew_date(new_date);
					data.setNew_value(comparelist.get(k).getAcuRate());
					k=k+1;
				}else{
					data.setNew_date(new_date);
					data.setNew_value(0.0);
				}
				list.add(data);
			}
			break;
		case 3:
			//月
			date1=DateUtil.add(endDate1, Calendar.DAY_OF_MONTH, -((count*30)-1));
			startTime=DateUtil.format_yyyy_MM_dd(date1);
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd(endDate1));
			searchData.setStartdate(startTime);
			currlist=dataCompareMapper.selectWeekAndMonthAcuDivPcuList(searchData);
			//对比日期的数据
			date2=DateUtil.add(endDate2, Calendar.DAY_OF_MONTH, -((count*30)-1));
			startTime2=DateUtil.format_yyyy_MM_dd(date2);
			searchData.setEnddate(DateUtil.format_yyyy_MM_dd(endDate2));
			searchData.setStartdate(startTime2);
			comparelist=dataCompareMapper.selectWeekAndMonthAcuDivPcuList(searchData);
			
			
			Date c_endDate3=endDate1;
			Date c_endDate4=endDate2;
			//currlist数组显示日期处理
			if(currlist!=null){
				for(DataCompare d:currlist){
					if(d!=null){
						Date startDate=DateUtil.add(c_endDate3, Calendar.DAY_OF_MONTH, -29);
						String str=DateUtil.format_yyyy_MM_dd(startDate)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate3);
						d.setStatdate(str);
						c_endDate3=DateUtil.add(startDate, Calendar.DAY_OF_MONTH, -1);
					}
				}
			}
			//comparelist数组显示日期处理
			if(comparelist!=null){
				for(DataCompare d:comparelist){
					if(d!=null){
						Date startDate=DateUtil.add(c_endDate4, Calendar.DAY_OF_MONTH, -29);
						String str=DateUtil.format_yyyy_MM_dd(startDate)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate4);
						d.setStatdate(str);
						c_endDate4=DateUtil.add(startDate, Calendar.DAY_OF_MONTH, -1);
					}
				}
			}
			c_endDate3=endDate1;
			c_endDate4=endDate2;
			//月数组
			for(int i=0;i<count;i++){
				//当前时间的月字符串数组
				Date startDate=DateUtil.add(c_endDate3, Calendar.DAY_OF_MONTH, -29);
				String str=DateUtil.format_yyyy_MM_dd(startDate)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate3);
				dateList1.add(str);
				c_endDate3=DateUtil.add(startDate, Calendar.DAY_OF_MONTH, -1);
				//对比时间的周字符串数组
				Date startDate2=DateUtil.add(c_endDate4, Calendar.DAY_OF_MONTH, -29);
				String str2=DateUtil.format_yyyy_MM_dd(startDate2)+"~"+DateUtil.format_yyyy_MM_dd(c_endDate4);
				dateList2.add(str2);
				c_endDate4=DateUtil.add(startDate2, Calendar.DAY_OF_MONTH, -1);
			}
			//currlist数组与comparelist数组合并
			for(int i=0;i<dateList1.size();i++){
				DataCompare data=new DataCompare();
				String date=dateList1.get(i);
				if(j<currlist.size()&&date.equals(currlist.get(j).getStatdate())){
					data.setDate(date);
					data.setValue(currlist.get(j).getAcuRate());
					j=j+1;
				}else{
					data.setDate(date);
					data.setValue(0.0);
				}
				
				String new_date=dateList2.get(i);
				if(k<comparelist.size()&&new_date.equals(comparelist.get(k).getStatdate())){
					data.setNew_date(new_date);
					data.setNew_value(comparelist.get(k).getAcuRate());
					k=k+1;
				}else{
					data.setNew_date(new_date);
					data.setNew_value(0.0);
				}
				list.add(data);
			}
			break;
		}
		return list;
	}
	
	

	
	
}
