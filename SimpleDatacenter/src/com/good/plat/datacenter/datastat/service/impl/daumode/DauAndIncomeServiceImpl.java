package com.good.plat.datacenter.datastat.service.impl.daumode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.DateUtil;
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.datastat.entity.daumode.DauData;
import com.good.plat.datacenter.datastat.entity.daumode.DauMode;
import com.good.plat.datacenter.datastat.service.daumode.DauAndIncomeService;
@Service(value="dauAndIncomeService")
public class DauAndIncomeServiceImpl implements DauAndIncomeService {
	/**
	 * 计算最大最小留存率
	 * @Title: minAndMaxRetain
	 * @Description: TODO
	 * @param dauMode
	 * @return
	 * Map<String,List<Double>>
	 * @author hwj
	 * @date 2017-3-28 下午07:31:29
	 */
	public Map<String,List<Double>> minAndMaxRetain(DauMode dauMode){
		Map<String,List<Double>> map=new HashMap<String,List<Double>>();
		//预测最小留存率
		List<Double> min_rate_list=new ArrayList<Double>();
		min_rate_list.add(dauMode.getMinDay1Rate());
		min_rate_list.add(dauMode.getMinDay2Rate());
		min_rate_list.add(dauMode.getMinDay3Rate());
		min_rate_list.add(dauMode.getMinDay4Rate());
		min_rate_list.add(dauMode.getMinDay5Rate());
		min_rate_list.add(dauMode.getMinDay6Rate());
		min_rate_list.add(dauMode.getMinDay7Rate());
		min_rate_list.add(dauMode.getMinDay14Rate());
		min_rate_list.add(dauMode.getMinDay30Rate());
		for(int i=0;i<60;i++){
			//求r8到r13最小留存率
			if(i>=7&&i<13){
				Double r8to13=dauMode.getMinDay7Rate()-dauMode.getMinDay14Rate()/6;
				min_rate_list.add(i, r8to13);
			}
			//求r15到r29最小留存率
			if(i>=14&&i<29){
				Double r15to29=dauMode.getMinDay14Rate()-dauMode.getMinDay30Rate()/15;
				min_rate_list.add(i, r15to29);
			}
			//求r31到r59的最小留存率
			/*r31=r30-r衰减
			 * r32=r30-2r衰减
			 * */
			if(i>=30&&i<=58){
				Double r31to60=dauMode.getMinDay30Rate()-((i-30+1)*dauMode.getDecayValue());
				if(r31to60<0){
					r31to60=0.0;
				}
				min_rate_list.add(i, r31to60);
			}
		}
		//预测最大留存率
		List<Double> max_rate_list=new ArrayList<Double>();
		max_rate_list.add(dauMode.getMaxDay1Rate());
		max_rate_list.add(dauMode.getMaxDay2Rate());
		max_rate_list.add(dauMode.getMaxDay3Rate());
		max_rate_list.add(dauMode.getMaxDay4Rate());
		max_rate_list.add(dauMode.getMaxDay5Rate());
		max_rate_list.add(dauMode.getMaxDay6Rate());
		max_rate_list.add(dauMode.getMaxDay7Rate());
		max_rate_list.add(dauMode.getMaxDay14Rate());
		max_rate_list.add(dauMode.getMaxDay30Rate());
		for(int i=0;i<60;i++){
			//求r8到r13最大留存率
			if(i>=7&&i<13){
				Double r8to13=NumberUtil.ajustScale(dauMode.getMaxDay7Rate()-dauMode.getMaxDay14Rate()/6, 2);
				max_rate_list.add(i, r8to13);
			}
			//求r15到r29最大留存率
			if(i>=14&&i<29){
				Double r15to29=NumberUtil.ajustScale(dauMode.getMaxDay14Rate()-dauMode.getMaxDay30Rate()/15,2);
				max_rate_list.add(i, r15to29);
			}
			//求r31到r59的最大留存率
			/*r31=r30-r衰减
			 * r32=r30-2r衰减
			 * */
			if(i>=30&&i<=58){
				Double r31to60=NumberUtil.ajustScale(dauMode.getMaxDay30Rate()-((i-30+1)*dauMode.getDecayValue()),2);
				if(r31to60<0){
					r31to60=0.0;
				}
				max_rate_list.add(i, r31to60);
			}
		}
		map.put("minRemainRate", min_rate_list);
		map.put("maxRemainRate", max_rate_list);
		return map;
	}
	@Override
	public Map<String, Object> dauAndIncomeOfSpread(DauMode dauMode)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> dauAndIncomeMap=new HashMap<String,Object>();
		Date startTime=null;//起始日期
		Date endDate=null;
		List<String> dateList=new ArrayList<String>();//日期数组
		Map<String,List<Double>> map=minAndMaxRetain(dauMode);
//		List<Double> minRemainlist=map.get("minRemainRate");//最小留存率list
//		List<Double> maxRemainlist=map.get("maxRemainRate");//最大留存率list
		List<DauMode> dauList=new ArrayList<DauMode>();//dauList
		List<DauMode> incomeList=new ArrayList<DauMode>();//incomeList
		Date comparedDate=null;//被比较的日期
//		Integer userCnt=dauMode.getUserCnt();
		switch (dauMode.getFlag()) {
		case 1:
			dauMode.setStartDate(dauMode.getCurrDate());
			startTime=DateUtil.parse_yyyy_MM_dd(dauMode.getStartDate());//自然用户当前日期
			endDate=DateUtil.add(startTime, Calendar.DAY_OF_MONTH, 59);//60天后
			while(startTime.getTime()<=endDate.getTime()){
				dateList.add(DateUtil.format_yyyy_MM_dd(startTime));
				startTime=DateUtil.add(startTime, Calendar.DAY_OF_MONTH, 1);
			}
			//计算dau
			dauList=countNutureDau(dauMode,dateList,map);
			//计算收入
			incomeList=caculIncome(dateList, dauList, dauMode);
			//小数点四舍五入
			if(dauList!=null&&dauList.size()>0){
				for(DauMode dau:dauList){
					dau.setMax_dau(Math.rint(dau.getMax_dau()));
					dau.setMin_dau(Math.rint(dau.getMin_dau()));
				}
			}
			break;
		case 2:
			List<DauData> mutilParamList=dauMode.getMutilParamList();
			//比较多个推广周期起始日期
			startTime=DateUtil.parse_yyyy_MM_dd(mutilParamList.get(0).getStartDate());//最小起始日期
			if(mutilParamList!=null&&mutilParamList.size()>1){
				for(int i=1;i<mutilParamList.size();i++){
					comparedDate=DateUtil.parse_yyyy_MM_dd(mutilParamList.get(i).getStartDate());
					if(startTime.getTime()<comparedDate.getTime()){
						continue;
					}else if(startTime.getTime()>comparedDate.getTime()){
						startTime=comparedDate;
					}else{
						continue;
					}
				}
			}
		 endDate=DateUtil.add(startTime, Calendar.DAY_OF_MONTH, 59);//60天后
		//往后推60天
		while(startTime.getTime()<=endDate.getTime()){
			dateList.add(DateUtil.format_yyyy_MM_dd(startTime));
			startTime=DateUtil.add(startTime, Calendar.DAY_OF_MONTH, 1);
		}
		
		Map<String, DauMode> total=null;
		if(mutilParamList!=null&&mutilParamList.size()>0){
			//计算推广用户的每份周期的dau
			for(DauData e:mutilParamList){
				Map<String, DauMode> daus=countSpreadDau(e,map);
				if(total == null){
					total = daus;
					continue;
				}
				for(String date : daus.keySet()){
					DauMode dau = total.get(date);
					if(dau == null){
						total.put(date, daus.get(date));
					}else{
						dau.setMax_dau(dau.getMax_dau()+daus.get(date).getMax_dau());
						dau.setMin_dau(dau.getMin_dau()+daus.get(date).getMin_dau());
					}
				}
			}
		}
		//排序并转为list
			dauList=sortAndToList(total);
			if(dauList!=null&&dauList.size()>0){
				Iterator<DauMode> it=dauList.iterator();
				while(it.hasNext()){
					DauMode dau=it.next();
					Date passDate=DateUtil.parse_yyyy_MM_dd(dau.getCurrDate());
					dau.setMax_dau(Math.rint(dau.getMax_dau()));
					dau.setMin_dau(Math.rint(dau.getMin_dau()));
					if(passDate.getTime()>endDate.getTime()){
						it.remove();
					}
				}
			}
			
			incomeList=caculIncome(dateList, dauList, dauMode);
			break;
		default:
			break;
		}
		dauAndIncomeMap.put("dauList", dauList);
		dauAndIncomeMap.put("incomeList", incomeList);
		return dauAndIncomeMap;
	}
	//计算自然用户的dau
	public List<DauMode> countNutureDau(DauMode dauMode,List<String> dateList,Map<String,List<Double>> map){
		Integer natureUserCnt=dauMode.getUserCnt();
		List<Double> minRemainlist=map.get("minRemainRate");//最小留存率list
		List<Double> maxRemainlist=map.get("maxRemainRate");//最大留存率list
		List<DauMode> dauList=new ArrayList<DauMode>();//dauList
		//自然用户dau
			if(dateList!=null&&dateList.size()!=0){
				//预测dau
				for(int i=0; i<dateList.size();i++){
					DauMode mode=new DauMode();
					Double maxTotal=0.0;
					Double minTotal=0.0;
					mode.setCurrDate(dateList.get(i));//显示日期
					if(i==0){
						mode.setMax_dau(natureUserCnt.doubleValue());
						mode.setMin_dau(natureUserCnt.doubleValue());
						dauList.add(0, mode);
					}else{
							for(int j=i-0;j>=1;j--){
								maxTotal+=NumberUtil.div(natureUserCnt*maxRemainlist.get(j-1), 100.00);
								minTotal+=NumberUtil.div(natureUserCnt*minRemainlist.get(j-1), 100.00);
							}
							maxTotal=maxTotal+natureUserCnt;
							minTotal=minTotal+natureUserCnt;
							mode.setMax_dau(NumberUtil.ajustScale(maxTotal, 2));
							mode.setMin_dau(NumberUtil.ajustScale(minTotal, 2));
							dauList.add(mode);		
						}
					}
				}
		return dauList;
	} 
	
	/**
	 * 计算推广用户dau
	 * @Title: countSpreadDau
	 * @Description: TODO
	 * @param data 推广用户参数
	 * @param map
	 * @return
	 * List<DauMode>
	 * @author hwj
	 * @date 2017-3-28 下午06:11:01
	 */
	public Map<String, DauMode> countSpreadDau(DauData data,Map<String,List<Double>> map){
		Integer userCnt=data.getUserCnt();//导入数
		List<Double> minRemainlist=map.get("minRemainRate");//最小留存率list
		List<Double> maxRemainlist=map.get("maxRemainRate");//最大留存率list
		Map<String, DauMode> dauList=new HashMap<String,DauMode>();//dauList
		List<String> timeList=new ArrayList<String>();
		Date startTime=DateUtil.parse_yyyy_MM_dd(data.getStartDate());//周期内起始日期
		Date endDate=DateUtil.add(startTime, Calendar.DAY_OF_MONTH, 59);//60天后
		Date endTime=DateUtil.parse_yyyy_MM_dd(data.getEndDate());//周期内结束日期
		//往后推60天
		while(startTime.getTime()<=endDate.getTime()){
			timeList.add(DateUtil.format_yyyy_MM_dd(startTime));
			startTime=DateUtil.add(startTime, Calendar.DAY_OF_MONTH, 1);
		}
		//推广dau
			if(timeList!=null&&timeList.size()!=0){
				int k=0;//结束日期有导入数的下标 
				//预测dau
				for(int i=0; i<timeList.size();i++){
					DauMode mode=new DauMode();
					Double maxTotal=0.0;
					Double minTotal=0.0;
					mode.setCurrDate(timeList.get(i));//显示日期
					if(i==0){
						mode.setMax_dau(userCnt.doubleValue());
						mode.setMin_dau(userCnt.doubleValue());
						dauList.put(timeList.get(i), mode);
					}else{
						//推广日期内的dau计算
						if(DateUtil.parse_yyyy_MM_dd(timeList.get(i)).getTime()<=endTime.getTime()){
							if(DateUtil.parse_yyyy_MM_dd(timeList.get(i)).getTime()==endTime.getTime()){
								k=i;
							}
							mode.setUserCnt(userCnt);
							for(int j=i-0;j>=1;j--){
								maxTotal+=NumberUtil.div(mode.getUserCnt()*maxRemainlist.get(j-1), 100.00);
								minTotal+=NumberUtil.div(mode.getUserCnt()*minRemainlist.get(j-1), 100.00);
							}
							maxTotal=maxTotal+userCnt;
							minTotal=minTotal+userCnt;
						}else{
							//推广日期外的dau计算
							for(int j=i-0;j>=1;j--){
								mode.setUserCnt(userCnt);
								//当j小于(当前元素下标-推广结束日期小标)set导入数为0
								if(j<(i-k)){
									mode.setUserCnt(0);
								}
								maxTotal+=NumberUtil.div(mode.getUserCnt()*maxRemainlist.get(j-1), 100.00);
								minTotal+=NumberUtil.div(mode.getUserCnt()*minRemainlist.get(j-1), 100.00);
							}
						}
						mode.setMax_dau(NumberUtil.ajustScale(maxTotal, 2));
						mode.setMin_dau(NumberUtil.ajustScale(minTotal, 2));
						dauList.put(mode.getCurrDate(), mode);
						}
					}
				}
		return dauList;
	} 
	
	
	/**
	 * 排序并转为list
	 * @Title: sortAndToList
	 * @Description: TODO
	 * @param map
	 * @return
	 * List<DauMode>
	 * @author hwj
	 * @date 2017-3-28 下午08:29:37
	 */
	  public List<DauMode> sortAndToList(Map<String, DauMode> map) {
	        if (map == null || map.isEmpty()) {
	            return null;
	        }
	        //排序
	        Map<String, DauMode> sortMap = new TreeMap<String, DauMode>(
	        		new Comparator<String>(){ 
	                    public int compare(String a, String b) { 
	                            return a.toString().toLowerCase().compareTo(b.toString().toLowerCase()); 
	                    } 
	            });
	        sortMap.putAll(map);
	        //转list
	        Collection<DauMode> valueCollection = sortMap.values();
		 
		    List<DauMode> valueList = new ArrayList<DauMode>(valueCollection);
		    System.out.println("map转list:"+valueList);
	        return valueList;
	    }
	  /**
	   * 计算收入
	   * @Title: caculIncome
	   * @Description: TODO
	   * @param dateList
	   * @param dauList
	   * @param dauMode
	   * @return
	   * List<DauMode>
	   * @author hwj
	   * @date 2017-3-28 下午08:36:36
	   */
	  public  List<DauMode> caculIncome(List<String> dateList,List<DauMode> dauList,DauMode dauMode ){
		  List<DauMode> incomeList=new ArrayList<DauMode>();
			//预测收入
			for(int i=0;i<dateList.size();i++){
				DauMode mode=new DauMode();
				mode.setCurrDate(dateList.get(i));
				DauMode dau=dauList.get(i);
				//最大付费人数
				Double maxPayUser=NumberUtil.div(dau.getMax_dau()*dauMode.getPayRate(), 100.00,0);
				//最小付费人数
				Double minPayUser=NumberUtil.div(dau.getMin_dau()*dauMode.getPayRate(), 100.00,0);
				//最大收入
				mode.setMax_income(maxPayUser*dauMode.getArpu());
				//最小收入
				mode.setMin_income(minPayUser*dauMode.getArpu());
				incomeList.add(mode);
			}
		  return incomeList;
	  }
}
