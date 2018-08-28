package com.good.plat.datacenter.datastat.service.impl.index;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.base.DateUtil;
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.datastat.entity.index.Report;
import com.good.plat.datacenter.datastat.mapper.index.ReportMapper;
import com.good.plat.datacenter.datastat.service.index.ReportService;

@Service(value="reportService")
public class ReportServiceImpl implements ReportService {
	@Autowired
	private ReportMapper reportMapper;
	@Override
	public Map<String,Report> selectDailyReport(BaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		//
		List<Report> baseDataList=reportMapper.selectDailyBaseDataList(searchData);
		//游戏
		List<Report> GameDataList=reportMapper.selectDailyGameDataList(searchData);
		//新增用户留存
		List<Report> newUserRetationList=reportMapper.selectDailyNewUserRetationList(searchData);
		//设备留存
		List<Report> deviceRetationList=reportMapper.selectDailyDeviceRetationRate(searchData);
		//活跃玩家留存
		List<Report> actUserRetationRateList=reportMapper.selectActUserRetationRate(searchData);
		//在线数据(没区分termtype)
		List<Report> acuAndPcuList=reportMapper.selectAcuAndPcuList(searchData);
		Map<String,Report> map=new HashMap<String,Report>();
		if(deviceRetationList!=null){
			for(Report r:deviceRetationList){
				if(r!=null){
					if(searchData.getAreas()!=null&&searchData.getAreas().size()!=0){
						r.setDeviceDay2(0.0);
						r.setDeviceDay3(0.0);
						r.setDeviceDay7(0.0);
					}
				}
				
			}
		}
		
		if(baseDataList!=null){
			for(Report r:baseDataList){
				if(r!=null){
					String statdate=r.getStatdate();
					if(map.get(statdate)==null){
						Report port=new Report();
						port.setStatdate(statdate);
						port.setNew_device(r.getNew_device());
						port.setNewUser(r.getNewUser());
						port.setNewActor(r.getNewActor());
						port.setPlayerConv(r.getPlayerConv());
						port.setActv_user(r.getActv_user());
						port.setAcu(r.getAcu());
						port.setPcu(r.getPcu());
						map.put(statdate,port);
					}else{
						map.get(statdate).setNew_device(r.getNew_device());
						map.get(statdate).setNewUser(r.getNewUser());
						map.get(statdate).setNewActor(r.getNewActor());
						map.get(statdate).setPlayerConv(r.getPlayerConv());
						map.get(statdate).setActv_user(r.getActv_user());
						map.get(statdate).setAcu(r.getAcu());
						map.get(statdate).setPcu(r.getPcu());
					}
				}
			}
		}
		//游戏
		if(GameDataList!=null){
			for(Report r:GameDataList){
				if(r!=null){
					String statdate=r.getStatdate();
					r.setAvgGameCount(NumberUtil.ajustScale(r.getAvgGameCount(), NumberUtil.DEFAULT_SCALE));
					r.setAvgGameTime(NumberUtil.ajustScale(r.getAvgGameTime(), NumberUtil.DEFAULT_SCALE));
					if(map.get(statdate)==null){
						Report port=new Report();
						port.setStatdate(statdate);
						port.setGameCounts(r.getGameCounts());
						port.setAvgGameCount(r.getAvgGameCount());
						port.setAvgGameTime(r.getAvgGameTime());
						map.put(statdate,port);
					}else{
						map.get(statdate).setGameCounts(r.getGameCounts());//游戏次数
						map.get(statdate).setAvgGameCount(r.getAvgGameCount());//平均游戏次数
						map.get(statdate).setAvgGameTime(r.getAvgGameTime());//平均游戏时长
					}
				}
			}
		}
		//新增用户留存
		if(newUserRetationList!=null){
			for(Report r:newUserRetationList){
				if(r!=null){
					String statdate=r.getStatdate();
					r.setNewuserDay2(NumberUtil.multi100(r.getNewuserDay2(), NumberUtil.DEFAULT_SCALE));
					r.setNewuserDay3(NumberUtil.multi100(r.getNewuserDay3(), NumberUtil.DEFAULT_SCALE));
					r.setNewuserDay7(NumberUtil.multi100(r.getNewuserDay7(), NumberUtil.DEFAULT_SCALE));
					if(map.get(statdate)==null){
						Report port=new Report();
						port.setStatdate(statdate);
						port.setNewuserDay2(r.getNewuserDay2());
						port.setNewuserDay3(r.getNewuserDay3());
						port.setNewuserDay7(r.getNewuserDay7());
						map.put(statdate,port);
					}else{
						map.get(statdate).setNewuserDay2(r.getNewuserDay2());//新增玩家次日留存
						map.get(statdate).setNewuserDay3(r.getNewuserDay3());//新增玩家3日留存
						map.get(statdate).setNewuserDay7(r.getNewuserDay7());//新增玩家7日留存
					}
				}
			}
		}
		//设备留存率
		if(deviceRetationList!=null){
			for(Report r:deviceRetationList){
				if(r!=null){
					String statdate=r.getStatdate();
					r.setDeviceDay2(NumberUtil.multi100(r.getDeviceDay2(), NumberUtil.DEFAULT_SCALE));
					r.setDeviceDay3(NumberUtil.multi100(r.getDeviceDay3(), NumberUtil.DEFAULT_SCALE));
					r.setDeviceDay7(NumberUtil.multi100(r.getDeviceDay7(), NumberUtil.DEFAULT_SCALE));
					if(map.get(statdate)==null){
						Report port=new Report();
						port.setStatdate(statdate);
						port.setDeviceDay2(r.getDeviceDay2());
						port.setDeviceDay3(r.getDeviceDay3());
						port.setDeviceDay7(r.getDeviceDay7());
						map.put(statdate,port);
					}else{
						map.get(statdate).setDeviceDay2(r.getDeviceDay2());
						map.get(statdate).setDeviceDay3(r.getDeviceDay3());
						map.get(statdate).setDeviceDay7(r.getDeviceDay7());
					}
				}
			}
		}
		//活跃用户留存率
		if(actUserRetationRateList!=null){
			for(Report r:actUserRetationRateList){
				if(r!=null){
					String statdate=r.getStatdate();
					r.setActvuserDay2(NumberUtil.multi100(r.getActvuserDay2(), NumberUtil.DEFAULT_SCALE));
					r.setActvuserDay3(NumberUtil.multi100(r.getActvuserDay3(), NumberUtil.DEFAULT_SCALE));
					r.setActvuserDay7(NumberUtil.multi100(r.getActvuserDay7(), NumberUtil.DEFAULT_SCALE));
					if(map.get(statdate)==null){
						Report port=new Report();
						port.setStatdate(statdate);
						port.setActvuserDay2(r.getActvuserDay2());
						port.setActvuserDay3(r.getActvuserDay3());
						port.setActvuserDay7(r.getActvuserDay7());
						map.put(statdate,port);
					}else{
						map.get(statdate).setActvuserDay2(r.getActvuserDay2());
						map.get(statdate).setActvuserDay3(r.getActvuserDay3());
						map.get(statdate).setActvuserDay7(r.getActvuserDay7());
					}
				}
			}
		}
		//在线数据
		if(acuAndPcuList!=null&&acuAndPcuList.size()!=0){
			for(Report r:acuAndPcuList){
				if(r!=null){
					String statdate=r.getStatdate();
					if(map.get(statdate)==null){
						Report port=new Report();
						port.setStatdate(statdate);
						port.setAcu(r.getAcu());
						port.setPcu(r.getPcu());
						map.put(statdate,port);
					}else{
						map.get(statdate).setAcu(r.getAcu());
						map.get(statdate).setPcu(r.getPcu());
					}
				}
			}
		}
		
		return map;
	}
	@Override
	public List<Report> selectPayDataList(BaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		List<Report> list=new ArrayList<Report>();
		list=reportMapper.selectpayDataList(searchData) ;
		if(list!=null){
			for(Report r:list){
				if(r!=null){
					r.setAct_arppu(NumberUtil.ajustScale(r.getAct_arppu(),NumberUtil.DEFAULT_SCALE));
					r.setNew_arppu(NumberUtil.ajustScale(r.getNew_arppu(),NumberUtil.DEFAULT_SCALE));
					r.setAct_arpu(NumberUtil.ajustScale(r.getAct_arpu(),NumberUtil.DEFAULT_SCALE));
					r.setNew_arpu(NumberUtil.ajustScale(r.getNew_arpu(),NumberUtil.DEFAULT_SCALE));
				}
			}
		}
		return list;
	}
	@Override
	public List<Report> selectTotalDataList(BaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		List<Report> reportList=new ArrayList<Report>();
		String startDate=searchData.getStartdate();
		String endDate=searchData.getEnddate();
		List<String> datelist=new ArrayList<String>();
		if(DateUtil.parse_yyyy_MM_dd(startDate).getTime()<DateUtil.parse_yyyy_MM_dd(endDate).getTime()){
			//datelist.add(startDate);
			while(DateUtil.parse_yyyy_MM_dd(startDate).getTime()-DateUtil.parse_yyyy_MM_dd(endDate).getTime()<=0){
				datelist.add(startDate);
				startDate=DateUtil.addDay(startDate, 1);
				
			}
		}else if(DateUtil.parse_yyyy_MM_dd(startDate).getTime()-DateUtil.parse_yyyy_MM_dd(endDate).getTime()==0){
			datelist.add(startDate);
		}else{
			
		}
		
		if(datelist!=null){
			for(String date:datelist){
				if(date!=null){
					searchData.setEnddate(date);
					Report r=reportMapper.getTotalData(searchData);
					if(r!=null){
						r.setStatdate(date);
						if(r.getNew_device()==null){
							r.setNew_device(0);
						}
						if(r.getNewUser()==null){
							r.setNewUser(0);
						}
						if(r.getPayact()==null){
							r.setPayact(0);
						}
						if(r.getdPayRate()==null){
							r.setdPayRate("0.0%");
						}
						if(r.getMoney()==null){
							r.setMoney(0.0);
						}
						if(r.getAct_arpu()==null){
							r.setAct_arpu(0.0);
						}
						if(r.getAct_arppu()==null){
							r.setAct_arppu(0.0);
						}
						r.setAct_arpu(NumberUtil.ajustScale(r.getAct_arpu(),NumberUtil.DEFAULT_SCALE));
						r.setAct_arppu(NumberUtil.ajustScale(r.getAct_arppu(),NumberUtil.DEFAULT_SCALE));
						reportList.add(r);
					}
				}
			}
		}
		return reportList;
	}
	@Override
	public List<Report> selectWeekBaseDataList(BaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		String endDate=searchData.getEnddate();
		int times=0;
		int days=DateUtil.getDateInterval(searchData.getStartdate(),searchData.getEnddate())+1;
		if(days<=7){
			times=2;
		}else{
			times=new Double(Math.ceil(days	/7.0)).intValue();
		}
		List<Report> list=new ArrayList<Report>();
		for(int i=1;i<=times;i++){
			searchData.setEnddate(endDate);
			endDate=DateUtil.addDay(endDate, -6);
			searchData.setStartdate(endDate);
			//周的基础数据
			Report report1=reportMapper.getBaseData(searchData);
			Report report2=reportMapper.getLostAndRet(searchData);
			if(report1!=null||report2!=null){
				Report report=new Report();
				report.setStatdate(endDate+"~"+searchData.getEnddate());
				if(report1!=null){
					report.setWeek_total_device_Cnt(report1.getWeek_total_device_Cnt());
					report.setWeek_new_play_count(report1.getWeek_new_play_count());
					report.setWeek_act_play_count(report1.getWeek_act_play_count());
					report.setWeek_avg_game_count(report1.getWeek_avg_game_count());
					report.setWeek_avg_game_time(report1.getWeek_avg_game_time());
					report.setWeek_avg_daily_game_time(report1.getWeek_avg_daily_game_time());
					report.setWeek_arpu(report1.getWeek_arpu());
					report.setWeek_income(report1.getWeek_income());
					report.setWeek_recharge_cnt(report1.getWeek_recharge_cnt());
					report.setWeek_pay_rate(report1.getWeek_pay_rate());
				}
				if(report2!=null){
					report.setWeek_lostcnt(report2.getWeek_lostcnt());
					report.setWeek_lostrate(report2.getWeek_lostrate());
					report.setWeek_day2ret(report2.getWeek_day2ret());
					report.setWeek_returncnt(report2.getWeek_returncnt());
				}
				list.add(report);
			}
			endDate=DateUtil.addDay(endDate, -1);
		}
		return list;
		
		
	}
	@Override
	public List<Report> selectMonthBaseDataList(BaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		String stateDate=searchData.getStartdate();
		String endDate=searchData.getEnddate();
		int month=DateUtil.getMonthSpace(stateDate, endDate)+1;//相隔对少个月
		List<Report> list=new ArrayList<Report>();
		for(int i=1;i<=month;i++){
			searchData.setStartdate(DateUtil.firstDayOfMonth(endDate));//第一天
			searchData.setEnddate(DateUtil.monthLastDay(endDate));//最后一天
			//当月的基础数据
			Report report1=reportMapper.getBaseData(searchData);
			//当月的留存数据
			Report report2=reportMapper.getLostAndRet(searchData);
			if(report1!=null||report2!=null){
				Report report=new Report();
				Date date=DateUtil.parse_yyyy_MM_dd(endDate);
				report.setStatdate(DateUtil.format_yyyy_MM(date));//取日期年月
				if(report1!=null){
					report.setWeek_total_device_Cnt(report1.getWeek_total_device_Cnt());
					report.setWeek_new_play_count(report1.getWeek_new_play_count());
					report.setWeek_act_play_count(report1.getWeek_act_play_count());
					report.setWeek_avg_game_count(report1.getWeek_avg_game_count());
					report.setWeek_avg_game_time(report1.getWeek_avg_game_time());
					report.setWeek_avg_daily_game_time(report1.getWeek_avg_daily_game_time());
					report.setWeek_arpu(report1.getWeek_arpu());
					report.setWeek_income(report1.getWeek_income());
					report.setWeek_recharge_cnt(report1.getWeek_recharge_cnt());
					report.setWeek_pay_rate(report1.getWeek_pay_rate());
				}
				if(report2!=null){
					report.setWeek_lostcnt(report2.getWeek_lostcnt());
					report.setWeek_lostrate(report2.getWeek_lostrate());
					report.setWeek_day2ret(report2.getWeek_day2ret());
					report.setWeek_returncnt(report2.getWeek_returncnt());
				}
				list.add(report);
			}
			//求上个月
			Date curr=DateUtil.parse_yyyy_MM_dd(endDate);
			Date preMonth=DateUtil.add(curr, Calendar.MONTH, -1);
			endDate=DateUtil.format_yyyy_MM_dd(preMonth);
			
		}
		return list;
	}

}
