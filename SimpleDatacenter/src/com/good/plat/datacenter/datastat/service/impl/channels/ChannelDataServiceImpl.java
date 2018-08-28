package com.good.plat.datacenter.datastat.service.impl.channels;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.base.Validator;
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.datastat.entity.channels.ChannelData;
import com.good.plat.datacenter.datastat.mapper.channels.ChannelDataMapper;
import com.good.plat.datacenter.datastat.service.channels.ChannelDataService;

/**
 * @ClassName: ChannelDataService
 * @Description: 渠道数据
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
@Service(value = "channelDataService")
public class ChannelDataServiceImpl implements ChannelDataService {

	@Autowired
	private ChannelDataMapper channelDataMapper;
	

	@Override
	public List<ChannelData> selectPartnerAmount(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return channelDataMapper.selectPartnerAmount(searchData);
	}

	@Override
	public List<ChannelData> selectPartnerAmountDetail(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData.searchDataFilter(searchData);
		return channelDataMapper.selectPartnerAmountDetail(searchData);
	}

	@Override
	public List<ChannelData> selectChannelDau(BaseSearchData searchData,
			HttpSession session) throws Exception {
		return channelDataMapper.selectChannelDau(searchData);
	}

	@Override
	public List<ChannelData> selectChannelDayRetention(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData.searchDataFilter(searchData);
		return channelDataMapper.selectChannelDayRetention(searchData);
	}

	@Override
	public List<ChannelData> selectChannelActorFirstRechargeInTime(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData.searchDataFilter(searchData);
		return channelDataMapper
				.selectChannelActorFirstRechargeInTime(searchData);
	}

	@Override
	public List<ChannelData> exportPartnerAmount(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return channelDataMapper.exportPartnerAmount(searchData);
	}
	private List<ChannelData> initIfNecessary(List<ChannelData> clist){
		for(ChannelData e : clist){
			if(e.getChannelid() == null){
				e.setChannelid(0);
			}
			if(e.getStatdate() == null){
				e.setStatdate("");
			}
			if(e.getPayRate() == null){
				e.setPayRate(0.0);
			}
			if(e.getDay1Retention() == null){
				e.setDay1Retention(0.0);
			}
			if(e.getRetentionRate() == null){
				e.setRetentionRate(0.0);
			}
			if(e.getActiveAccounts() == null){
				e.setActiveAccounts(0);
			}
		}
		return clist;
	}
	
	private List<ChannelData> sortByChannel(List<ChannelData> clist){
		Collections.sort(clist,new Comparator<ChannelData>() {
			@Override
			public int compare(ChannelData o1, ChannelData o2) {
				try{
					return o1.getChannelid().compareTo(o2.getChannelid());
				}catch(Exception e){e.printStackTrace();}
				return -1;
			}
		});
		return clist;
	}
	
	@Override
	public List<ChannelData> selectPartnerQuality(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		//分渠道统计
		List<ChannelData> listRecharge = channelDataMapper.selectChannelActorFirstRechargeInTime(searchData);
		//channelid,payRate
		List<ChannelData> listChannelRet1dayret = channelDataMapper.selectChannelRetentionAnd1DayRetRate(searchData);
		//channelid,statdate,day1Retention,retentionRate
		List<ChannelData> listDailyDAU = channelDataMapper.selectChannelDau(searchData);
		//channelid,statdate ,activeAccounts
		//
		listRecharge = sortByChannel(initIfNecessary(listRecharge));
		listChannelRet1dayret = sortByChannel(initIfNecessary(listChannelRet1dayret));
		listDailyDAU = sortByChannel(initIfNecessary(listDailyDAU));
		//
		for(ChannelData e :listRecharge){
			e.setPayRate(NumberUtil.mul(e.getPayRate(), 100.0));
		}
		//
		Double dayscount = 0.0;
		//avg-retention
		List<ChannelData> listRetention = new ArrayList<ChannelData>();
		dayscount = 0.0;////////////////////////////////////////////////////////////////////////////////////
		for(int i = 0,size = listChannelRet1dayret.size();i < size;i++ ){
			ChannelData cd = listChannelRet1dayret.get(i);
			if(cd != null && cd.getChannelid() != null){
				dayscount = 1.0;
				int k = i + 1;
				for(int j = i + 1;j < size;j++ ){
					ChannelData cd2 = listChannelRet1dayret.get(j);
					if(cd.getChannelid().equals(cd2.getChannelid())){
						dayscount += 1;
						i = j;
						cd.setDay1Retention(cd.getDay1Retention() + cd2.getDay1Retention());
						cd.setRetentionRate(cd.getRetentionRate() + cd2.getRetentionRate());
					}else{
						i = j - 1;
						break;
					}
				}
				cd.setDay1Retention(NumberUtil.div(cd.getDay1Retention(), dayscount));
				cd.setRetentionRate(NumberUtil.mul(NumberUtil.div(cd.getRetentionRate(), dayscount),100.0));
				listRetention.add(cd);
			}
		}
		//avg-dau
		List<ChannelData> listDAU = new ArrayList<ChannelData>();
		dayscount = 0.0;////////////////////////////////////////////////////////////////////////////////////
		for(int i = 0,size = listDailyDAU.size();i < size;i++ ){
			ChannelData cd = listDailyDAU.get(i);
			if(cd != null && cd.getChannelid() != null){
				dayscount = 1.0;
				for(int j = i + 1;j < size;j++ ){
					ChannelData cd2 = listDailyDAU.get(j);
					if(cd.getChannelid().equals(cd2.getChannelid())){
						dayscount += 1;
						i = j;
						cd.setActiveAccounts(cd.getActiveAccounts() + cd2.getActiveAccounts());
					}else{
						i = j - 1;
						break;
					}
				}
				cd.setActiveAccounts((int) NumberUtil.div(cd.getActiveAccounts().doubleValue(), dayscount).doubleValue());
				listDAU.add(cd);
			}
		}
		//merge: listRecharge,listRetention,listDAU
		List<ChannelData> rlist1 = new ArrayList();
		while(!listRecharge.isEmpty() && !listRetention.isEmpty()){
			ChannelData e1 = listRecharge.get(0);//payRate
			ChannelData e2 = listRetention.get(0);//retentionRate,day1Retentaion
			int cmp = e1.getChannelid().compareTo(e1.getChannelid());
			if(cmp == 0){
				e1.setRetentionRate(e2.getRetentionRate());
				e1.setDay1Retention(e2.getDay1Retention());
				rlist1.add(e1);
				listRecharge.remove(e1);
				listRetention.remove(e2);
			}else if(cmp < 0){
				e1.setRetentionRate(e1.getRetentionRate());
				e1.setDay1Retention(e2.getDay1Retention());
				rlist1.add(e1);
				listRecharge.remove(e1);
			}else{
				e2.setPayRate(e1.getPayRate());
				rlist1.add(e2);
				listRetention.remove(e2);
			}
		}
		while(!listRecharge.isEmpty()){
			ChannelData e1 = listRecharge.get(0);
			e1.setRetentionRate(0.0);
			e1.setDay1Retention(0.0);
			rlist1.add(e1);
			listRecharge.remove(e1);
		}
		while(!listRetention.isEmpty()){
			ChannelData e2 = listRetention.get(0);
			e2.setActiveAccounts(0);
			rlist1.add(e2);
			listRetention.remove(e2);
		}
		List<ChannelData> rlist2 = new ArrayList();
		while(!rlist1.isEmpty() && !listDAU.isEmpty()){
			ChannelData e1 = rlist1.get(0);//retentionRate,day1Retentaion,payRate
			ChannelData e2 = listDAU.get(0);//activeAccounts
			int cmp = e1.getChannelid().compareTo(e1.getChannelid());
			if(cmp == 0){
				e1.setActiveAccounts(e2.getActiveAccounts());
				rlist2.add(e1);
				rlist1.remove(e1);
				listDAU.remove(e2);
			}else if(cmp < 0){
				e1.setActiveAccounts(e2.getActiveAccounts());
				rlist2.add(e1);
				rlist1.remove(e1);
			}else{
				e2.setRetentionRate(e1.getRetentionRate());
				e2.setDay1Retention(e1.getDay1Retention());
				e2.setPayRate(e1.getPayRate());
				rlist2.add(e2);
				listDAU.remove(e2);
			}
		}
		while(!rlist1.isEmpty()){
			ChannelData e1 = rlist1.get(0);
			e1.setActiveAccounts(0);
			rlist2.add(e1);
			rlist1.remove(e1);
		}
		while(!listDAU.isEmpty()){
			ChannelData e2 = listDAU.get(0);
			e2.setRetentionRate(0.0);
			e2.setDay1Retention(0.0);
			e2.setPayRate(0.0);
			rlist2.add(e2);
			listDAU.remove(e2);
		}
		return rlist2;
	}
	
	
	public List<ChannelData> selectPartnerQuality2(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		//分渠道统计
		List<ChannelData> listRecharge = channelDataMapper.selectChannelActorFirstRechargeInTime(searchData);
		//分渠道\日统计
		List<ChannelData> listDailyDAU = channelDataMapper.selectChannelDau(searchData);
		List<ChannelData> listDailyRetention = channelDataMapper.selectChannelDayRetention(searchData);
		
		if(listRecharge != null){
			for(ChannelData c: listRecharge){
				if(c.getRate() == null){
					c.setRate(0.0);
				}
				c.setActiveAccounts(0);
				c.setDay1Retention(0.0);//次日留存数
				c.setRate(NumberUtil.mul(c.getRate(), 100.0,NumberUtil.DEFAULT_SCALE));//首周付费比例
			}
		}
		if(listDailyDAU != null){
			for(ChannelData c: listDailyDAU){
				if(c.getActiveAccounts() == null){
					c.setActiveAccounts(0);
				}
				c.setDay1Retention(0.0);
				c.setRate(0.0);
			}
		}
		if(listDailyRetention != null){
			for(ChannelData c: listDailyRetention){
				if(c.getDay1Retention() == null){
					c.setDay1Retention(0.0);
				}
				c.setActiveAccounts(0);
				c.setRate(0.0);
			}
		}
		Collections.sort(listDailyDAU,new Comparator<ChannelData>() {
			@Override
			public int compare(ChannelData o1, ChannelData o2) {
				try{
					return o1.getChannelid().compareTo(o2.getChannelid());
				}catch(Exception e){e.printStackTrace();}
				return -1;
			}
		});
		
		Collections.sort(listDailyRetention,new Comparator<ChannelData>() {
			@Override
			public int compare(ChannelData o1, ChannelData o2) {
				try{
					return o1.getChannelid().compareTo(o2.getChannelid());
				}catch(Exception e){e.printStackTrace();}
				return -1;
			}
		});
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		double dayscount = 0.0;
		
		//avg-retention
		List<ChannelData> listRetention = new ArrayList<ChannelData>();
		dayscount = 0;////////////////////////////////////////////////////////////////////////////////////
		for(int i = 0,size = listDailyRetention.size();i < size;i++ ){
			ChannelData cd = listDailyRetention.get(i);
			if(cd != null && cd.getChannelid() != null){
				dayscount = 1;
				for(int j = i + 1;j < size;j++ ){
					ChannelData cd2 = listDailyRetention.get(j);
					if(cd.getChannelid().equals(cd2.getChannelid())){
						dayscount += 1;
						i = j;
						cd.setDay1Retention(cd.getDay1Retention() + cd2.getDay1Retention());
					}else{
						i = j - 1;
						break;
					}
				}
				cd.setDay1Retention(NumberUtil.div(cd.getDay1Retention(), dayscount));
				listRetention.add(cd);
			}
		}
		//avg-dau
		List<ChannelData> listDAU = new ArrayList<ChannelData>();
		dayscount = 0;////////////////////////////////////////////////////////////////////////////////////
		for(int i = 0,size = listDailyDAU.size();i < size;i++ ){
			ChannelData cd = listDailyDAU.get(i);
			if(cd != null && cd.getChannelid() != null){
				dayscount = 1;
				for(int j = i + 1;j < size;j++ ){
					ChannelData cd2 = listDailyDAU.get(j);
					if(cd.getChannelid().equals(cd2.getChannelid())){
						dayscount += 1;
						i = j;
						cd.setActiveAccounts(cd.getActiveAccounts() + cd2.getActiveAccounts());
					}else{
						i = j - 1;
						break;
					}
				}
				cd.setActiveAccounts((int) NumberUtil.div(cd.getActiveAccounts().doubleValue(), dayscount).doubleValue());
				listDAU.add(cd);
			}
		}
		
		//merge
		List<ChannelData> list1 = new ArrayList<ChannelData>();
		while(listRetention.size() != 0 
				&& listRecharge.size() != 0){
			ChannelData ret = listRetention.get(0),recharge = listRecharge.get(0),cd;
			int cmp = ret.getChannelid().compareTo(recharge.getChannelid());
			if(cmp == 0){
				cd = ret;
				cd.setRate(recharge.getRate());
				listRetention.remove(0);
				listRecharge.remove(0);
			}else if(cmp < 0){
				cd = ret;
				cd.setRate(0.0);
				listRetention.remove(0);
			}else{
				cd = recharge;
				cd.setDay1Retention(0.0);
				listRecharge.remove(0);
			}
			list1.add(cd);
		}
		while(listRetention.size() != 0){
			ChannelData ret = listRetention.get(0);
			ret.setRate(0.0);
			list1.add(ret);
			listRetention.remove(0);
		}
		while(listRecharge.size() != 0){
			ChannelData recharge = listRecharge.get(0);
			recharge.setDay1Retention(0.0);
			list1.add(recharge);
			listRecharge.remove(0);
		}
		
		List<ChannelData> list2 = new ArrayList<ChannelData>();
		while(list1.size() != 0 && listDAU.size() != 0){
			ChannelData o1 = list1.get(0),cd = listDAU.get(0);
			int cmp = o1.getChannelid().compareTo(cd.getChannelid());
			if(cmp == 0){
				o1.setActiveAccounts(cd.getActiveAccounts());
				cd = o1;
				listDAU.remove(0);
				list1.remove(0);
			}else if(cmp < 0){
				o1.setActiveAccounts(0);
				cd = o1;
				list1.remove(0);
			}else{
				cd.setDay1Retention(0.0);
				cd.setRate(0.0);
				listDAU.remove(0);
			}
			list2.add(cd);
		}
		while(list1.size() != 0){
			ChannelData o1 = list1.get(0);
			o1.setActiveAccounts(0);
			list2.add(o1);
			list1.remove(0);
		}
		while(listDAU.size() != 0){
			ChannelData o1 = listDAU.get(0);
			o1.setDay1Retention(0.0);
			o1.setRate(0.0);
			list2.add(o1);
			listDAU.remove(0);
		}
		
		return list2;
	}
	
	@Override
	public List<ChannelData> exportPartnerQuality(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return channelDataMapper.exportPartnerQuality(searchData);
	}

	@Override
	public List<ChannelData> selectPartnerIncome(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return channelDataMapper.selectPartnerIncome(searchData);
	}
	
	@Override
	public List<ChannelData> selectPartnerDailyIncome(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return channelDataMapper.selectPartnerDailyIncome(searchData);
	}

	@Override
	public List<ChannelData> exportPartnerIncome(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return channelDataMapper.exportPartnerIncome(searchData);
	}

	@Override
	public List<ChannelData> selectInstallList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return channelDataMapper.selectInstallList(searchData);
	}

	@Override
	public List<ChannelData> selectNewPlayerList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return channelDataMapper.selectNewPlayerList(searchData);
	}

	@Override
	public List<ChannelData> selectDauList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return channelDataMapper.selectDauList(searchData);
	}

	@Override
	public List<ChannelData> selectIncomeNumList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return channelDataMapper.selectIncomeNumList(searchData);
	}

	@Override
	public List<ChannelData> selectChargePlayerNumList(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return channelDataMapper.selectChargePlayerNumList(searchData);
	}

	@Override
	public List<ChannelData> selectDailyARPU(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return channelDataMapper.selectDailyARPU(searchData);
	}

}
