package com.good.plat.datacenter.datastat.service.impl.players;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.datastat.entity.players.Conversion;
import com.good.plat.datacenter.datastat.mapper.players.ConversionMapper;
import com.good.plat.datacenter.datastat.service.players.ConversionService;

/**
 * @ClassName: ConversionMapper
 * @Description: 付费转化
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
@Service(value="playerConversionService")
public class ConversionServiceImpl implements ConversionService{

	@Autowired
	private ConversionMapper conversionMapper;
	
	@Override
	public List<Conversion> selectNewChargeUserNumList(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		
		List<Conversion> resultList = new ArrayList<Conversion>();
		List<Conversion> dataList = new ArrayList<Conversion>();
		dataList =  conversionMapper.selectNewChargeUserNumList(searchData);
		
		if (dataList != null) {
			Conversion conversion = new Conversion();
			int sumcount=0;
			int totalPayAccounts=0;
			Double totalPayAccountRate=0.0;
			for (Conversion data : dataList) {
				sumcount+=data.getAccounts();
				totalPayAccounts+=data.getNewPayAccounts();
				totalPayAccountRate+=data.getNewPayAccountsRate();
				Double newPayAccountRate=data.getNewPayAccountsRate();
				if(newPayAccountRate!=null){
					data.setNewPayAccountsRate(NumberUtil.multi100(newPayAccountRate, 2));
				}
			}
			conversion.setNewPaidPlayers(dataList);
			conversion.setSUM(sumcount);//总的付费玩家数（按角色）
			conversion.setTotalNewPayAccount(totalPayAccounts);//总的付费玩家数（按账号）
			conversion.setTotalnewPayAccountsRate(NumberUtil.multi100(totalPayAccountRate, 2));//总的付费率（按账号）
			resultList.add(conversion);
		}
		
		return resultList;
	}

	@Override
	public List<Conversion> exportNewChargeUserNumList(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.exportNewChargeUserNumList(searchData);
	}

	@Override
	public List<Conversion> selectChargeUserNumList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.selectChargeUserNumList(searchData);
	}

	@Override
	public List<Conversion> exportChargeUserNumList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.exportChargeUserNumList(searchData);
	}

	@Override
	public List<Conversion> selectTotalPaidRate(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		List<Conversion> listcs=conversionMapper.selectTotalPaidRate(searchData);
		for(Conversion c:listcs){
			if(c == null){
				continue;
			}else{
				if(c.getAccounts() == null){
					c.setAccounts(0);
				}
				if(c.getAccountSUM() == null){
					c.setAccountSUM(0);
				}
				c.setRate(NumberUtil.mul(NumberUtil.div((double)c.getAccounts(), (double)c.getAccountSUM(),3), 100.0));
				//总体账号付费率
				c.setPayRate(NumberUtil.mul(NumberUtil.div((double)c.getRechargeUser(), (double)c.getTotalUser(),2), 100.0));
			}
		}
		
		return listcs;
	}

	@Override
	public List<Conversion> exportTotalPaidRate(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.exportTotalPaidRate(searchData);
	}

	@Override
	public List<Conversion> selectFirstDayChargeRate(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		List<Conversion> listcs=conversionMapper.selectFirstDayChargeRate(searchData);
		for(Conversion c:listcs){
			System.out.println(c.getAccounts()+"===========首日付费===================="+c.getAccountSUM());
			if(c.getAccountSUM()==0||c.getAccounts()==0){
				c.setRate(0.0);
			}else{
				c.setRate(NumberUtil.mul( NumberUtil.div((double)c.getAccounts(), (double)c.getAccountSUM(),3),100.0));
				System.out.println("比率："+NumberUtil.div((double)c.getAccounts(), (double)c.getAccountSUM(),3));
				System.out.println("比率："+NumberUtil.div((double)c.getAccounts(), (double)c.getAccountSUM(),3)*100);
			}
			
		}
		return listcs;
	}

	@Override
	public List<Conversion> exportFirstDayChargeRate(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.exportFirstDayChargeRate(searchData);
	}

	@Override
	public List<Conversion> selectFirstWeekChargeRate(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		
		List<Conversion> listcs=conversionMapper.selectFirstWeekChargeRate(searchData);
		for(Conversion c:listcs){
			if(c.getAccountSUM()==0||c.getAccounts()==0){
				c.setRate(0.0);
			}else{
				c.setRate(NumberUtil.mul(NumberUtil.div((double)c.getAccounts(), (double)c.getAccountSUM(),3),100.0));
			}
			
		}
		return listcs;
	}

	@Override
	public List<Conversion> exportFirstWeekChargeRate(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.exportFirstWeekChargeRate(searchData);
	}

	@Override
	public List<Conversion> selectFirstMonthChargeRate(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		
		List<Conversion> listcs=conversionMapper.selectFirstMonthChargeRate(searchData);
		for(Conversion c:listcs){
			System.out.println(c.getAccounts()+"===========首月付费===================="+c.getAccountSUM());
			if(c.getAccountSUM()==0||c.getAccounts()==0){
				c.setRate(0.0);
			}else{
				c.setRate(NumberUtil.mul(NumberUtil.div((double)c.getAccounts(), (double)c.getAccountSUM(),3),100.0));
			}
			
		}
		return listcs;
	}

	@Override
	public List<Conversion> exportFirstMonthChargeRate(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.exportFirstMonthChargeRate(searchData);
	}

	@Override
	public List<Conversion> selectFirstChargeTotalDayNumList(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		Conversion returndata=new Conversion();
		List<Conversion> resultlist=new ArrayList<Conversion>();
		List<Conversion> listcs=conversionMapper.selectFirstChargeTotalDayNumList(searchData);
		List<Conversion> rlist = new ArrayList();
		listcs = listcs == null ? new ArrayList() : listcs;
		//System.out.println(c.getAccounts()+"===========游戏天数===================="+c.getAccountSUM());
		//所有付费玩家数量
		int allcount=0;
		for(Conversion c:listcs){
			Integer daycount=c.getDaycount();
			if(daycount==null){
				//没有注册日期的付费玩家，过滤掉
			}else{
				allcount+=c.getAccounts();
			}
		}
		System.out.println("=============所有首付费玩家数量:"+allcount);
		
		for(int i = 0;i < listcs.size(); ++i){
			Conversion c = listcs.get(i);
			Integer daycount=c.getDaycount();
			String title="";
			if(daycount==null){
				//没有注册日期的付费玩家，过滤掉
			}else if(daycount==0){
				title="首日";
			}else if(daycount<=2){
				title="2~3日";
			}else if(daycount<=7){
				title="4~7日";
			}else if(daycount<=14){
				title="2周";
			}else if(daycount<=21){
				title="3周";
			}else if(daycount==28){
				title="4周";
			}else if(daycount==35){
				title="5周";
			}else if(daycount==42){
				title="6周";
			}else if(daycount==49){
				title="7周";
			}else if(daycount==56){
				title="8周";
			}else if(daycount>56 &&daycount<=84){
				title="9~12周";
			}else if(daycount>84){
				title=">12周";
			}
			boolean found = false;
			for(int j = 0; j < rlist.size(); ++j){
				Conversion c2 = rlist.get(j);
				if(c2.getTitle().equals(title)){
					c2.setAccounts(c2.getAccounts() + c.getAccounts());
					double d= NumberUtil.div(c2.getAccounts().doubleValue(), (double)allcount,4);
					c2.setPercentage(NumberUtil.mul(d, 100.0));
					found = true;
				}
			}
			if(!found){
				c.setTitle(title);
				Integer account=c.getAccounts();
				double d=NumberUtil.div((double)account, (double)allcount,4);
				c.setPercentage(NumberUtil.mul(d, 100.0));
				rlist.add(c);
			}
		}
		returndata.setGamePlaytittle(rlist);
		returndata.setAVG(0);
		returndata.setMD(0);
		resultlist.add(returndata);
		return resultlist;
	}

	@Override
	public List<Conversion> exportFirstChargeTotalDayNumList(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.exportFirstChargeTotalDayNumList(searchData);
	}

	@Override
	public List<Conversion> selectFirstChargeTotalTimeList(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.selectFirstChargeTotalTimeList(searchData);
	}

	@Override
	public List<Conversion> exportFirstChargeTotalTimeList(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.exportFirstChargeTotalTimeList(searchData);
	}

	@Override
	public List<Conversion> selectFirstChargeLevel(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		Conversion returndata=new Conversion();
		List<Conversion> resultlist=new ArrayList<Conversion>();
		List<Conversion> listcs=conversionMapper.selectFirstChargeLevel(searchData);
		//System.out.println(c.getAccounts()+"===========游戏天数===================="+c.getAccountSUM());
		//所有付费玩家数量
		int allcount=0;
		for(Conversion c:listcs){
				allcount+=c.getAccounts();
		}
		System.out.println("=============所有首付等级玩家数量:"+allcount);
		
		for(Conversion c:listcs){
			Integer daycount=c.getDaycount();
			Integer account=c.getAccounts();
			double d=NumberUtil.div((double)account, (double)allcount,4);
			c.setPercentage(NumberUtil.mul(d, 100.0));
		}
		returndata.setFirstPayByLevel(listcs);
		returndata.setAVG(0);
		returndata.setMD(0);
		resultlist.add(returndata);
		return resultlist;
		
		
	}

	@Override
	public List<Conversion> exportFirstChargeLevel(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.exportFirstChargeLevel(searchData);
	}

	@Override
	public List<Conversion> selectFirstChargeMoney(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.selectFirstChargeMoney(searchData);
	}

	@Override
	public List<Conversion> exportFirstChargeMoney(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return conversionMapper.exportFirstChargeMoney(searchData);
	}


	@Override
	public List<Conversion> selectFirstPayWay(BaseSearchData searchData) {
		// TODO Auto-generated method stub
		searchData = searchData.searchDataFilter(searchData);
		List<Conversion> list= conversionMapper.selectFirstPayWay(searchData);
		if(list!=null){
			for(Conversion c:list){
				if(c.getAccounts()==null){
					c.setAccounts(0);
				}
			}
		}
		 return list;
	}

	@Override
	public List<Conversion> selectNumOfFirstPayLevel(BaseSearchData searchData) {
		// TODO Auto-generated method stub
		searchData = searchData.searchDataFilter(searchData);
		List<Conversion> list= conversionMapper.selectNumOfFirstPayLevel(searchData);
		return list;
	}

	@Override
	public List<Conversion> selectNumOfFirstPayMoney(BaseSearchData searchData) {
		// TODO Auto-generated method stub
		searchData = searchData.searchDataFilter(searchData);
		List<Conversion> list=conversionMapper.selectNumOfFirstPayMoney(searchData);
		return list;
	}

	

}
