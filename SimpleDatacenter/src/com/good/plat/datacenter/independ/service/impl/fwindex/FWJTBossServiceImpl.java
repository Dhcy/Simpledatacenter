package com.good.plat.datacenter.independ.service.impl.fwindex;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.DateUtil;
import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.independ.entity.fwindex.FWJTBoss;
import com.good.plat.datacenter.independ.mapper.fwindex.FWJTBossMapper;
import com.good.plat.datacenter.independ.service.fwindex.FWJTBossService;
@Service(value="fWJTBossServiceImpl")
public class FWJTBossServiceImpl implements FWJTBossService{
	@Autowired
	private FWJTBossMapper fWJTBossMapper;
	@Override
	public List<FWJTBoss> selectKillBossDetailList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		List<FWJTBoss> list=null;
		list=fWJTBossMapper.selectKillBossDetailList(searchData);
		return list;
	}

	@Override
	public List<FWJTBoss> selectKillBossJTInfoList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		List<FWJTBoss> list=null;
		list=fWJTBossMapper.selectKillBossJTInfoList(searchData);
		if(list!=null&&list.size()>0){
			for(FWJTBoss e:list){
				Date beforeTime=sdf.parse(e.getStatdate());
				String refreshTime=e.getRefreshTime();//获取刷新时间
				String[] hour_time=refreshTime.split(":");
				int hour=Integer.parseInt(hour_time[0]);
				int minute=Integer.parseInt(hour_time[1]);
				int second=0;
				//修改时间的小时,分钟,秒
				Date aferTime=DateUtil.setHour_minute_second(beforeTime, hour, minute, second);
				//时间加秒
				Date newTime=DateUtil.add(aferTime, Calendar.SECOND, e.getKillTime().intValue());
				//格式化时间为HH:mm
				String newKillTime=DateUtil.format_HH_mm(newTime);
				e.setJtKillTime(newKillTime);
			}
		}
		return list;
	}

	@Override
	public List<FWJTBoss> selectPerDayJtKillInfoList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		List<FWJTBoss> list=null;
		list=fWJTBossMapper.selectPerDayJtKillInfoList(searchData);
		if(list!=null&&list.size()>0){
			for(FWJTBoss e:list){
				Integer fKillTime=e.getfKillTime();
				Integer avgKillTime=e.getAvgKillTime();
				if(fKillTime!=null){
					String currentFkillTime=DateUtil.secondTransferToHMS(fKillTime.intValue());//秒格式化为xx时xx分xx秒
					e.setCurrentFKillTime(currentFkillTime);
				}
				if(avgKillTime!=null){
					String currentAvgKillTime=DateUtil.secondTransferToHMS(avgKillTime.intValue());//秒格式化为xx时xx分xx秒
					e.setCurrentAvgKillTime(currentAvgKillTime);
				}
			}
		}
		return list;
	}

}
