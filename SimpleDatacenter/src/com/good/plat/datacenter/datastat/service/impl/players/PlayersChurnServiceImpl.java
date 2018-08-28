package com.good.plat.datacenter.datastat.service.impl.players;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.datastat.entity.players.PlayersChurn;
import com.good.plat.datacenter.datastat.mapper.players.PlayersChurnMapper;
import com.good.plat.datacenter.datastat.service.players.PlayersChurnService;

/**
 * @ClassName: PlayersChurnMapper
 * @Description: 玩家流失
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
@Service(value="playersChurnService")
public class PlayersChurnServiceImpl implements PlayersChurnService {

	@Autowired
	private PlayersChurnMapper playersChurnMapper;
	@Override
	public List<PlayersChurn> selectPerDayLostNumAndRate(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		List<PlayersChurn> playlist=playersChurnMapper.selectPerDayLostNumAndRate(searchData);
		for(PlayersChurn pc:playlist){
			pc.setRate(NumberUtil.mul(NumberUtil.div((double)pc.getAccounts(), (double)pc.getSumactvcnt(),2), 100.0,NumberUtil.DEFAULT_SCALE));
		}
		return playlist;
	}

	@Override
	public List<PlayersChurn> exportPerDayLostNumAndRate(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return playersChurnMapper.exportPerDayLostNumAndRate(searchData);
	}

	@Override
	public List<PlayersChurn> selectPerDayReturnNumList(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return playersChurnMapper.selectPerDayReturnNumList(searchData);
	}

	@Override
	public List<PlayersChurn> exportPerDayReturnNumList(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return playersChurnMapper.exportPerDayReturnNumList(searchData);
	}

	@Override
	public List<PlayersChurn> selectLostUserAnalysNumList(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		List<PlayersChurn> rlist = new ArrayList();
		List<PlayersChurn> listpc = playersChurnMapper.selectLostUserAnalysNumList(searchData);
		listpc = listpc == null ? new LinkedList() : listpc;
		double totalAccounts = 0.0;
		Collections.sort(listpc,new Comparator<PlayersChurn>() {
			public int compare(PlayersChurn o1, PlayersChurn o2) {
				return o1.getSubtype().compareTo(o2.getSubtype());
			}
		});
		for(PlayersChurn pc:listpc){
			if(pc.getAccounts() == null){
				pc.setAccounts(0);
			}
			totalAccounts += pc.getAccounts();
		}
		
		for(PlayersChurn pc : listpc){
			String typename="";
			
			//
			if(searchData.getChecktype3()!=null&&searchData.getChecktype3().equals("1")){
				//流失等级
				typename = pc.getSubtype() + "";
			}else if(searchData.getChecktype3()!=null&&searchData.getChecktype3().equals("2")){
				//2、用户生命
				if(pc.getSubtype()<=1){
					typename="新用户";
				}else if(pc.getSubtype()<=3){
					typename="2~3日";
				}else if(pc.getSubtype()<=3){
					typename="2~3日";
				}else if(pc.getSubtype()<=4){
					typename="4~7日";
				}else if(pc.getSubtype()<=14){
					typename="8~14日";
				}else if(pc.getSubtype()<=30){
					typename="15~30日";
				}else if(pc.getSubtype()<=90){
					typename="31~90日";
				}else if(pc.getSubtype()<=180){
					typename="91~180日";
				}else if(pc.getSubtype()<=365){
					typename="181~365日";
				}else if(pc.getSubtype()>365){
					typename="1年+";
				}
			}else if(searchData.getChecktype3()!=null&&searchData.getChecktype3().equals("3")){
				//3、用户金额
				if(pc.getSubtype()<12){
					typename="<=11";
				}else if(pc.getSubtype()<=60){
					typename="<=60";
				}else if(pc.getSubtype()<=120){
					typename="<=120";
				}else if(pc.getSubtype()<=240){
					typename="<=240";
				}else if(pc.getSubtype()<=600){
					typename="<=600";
				}else if(pc.getSubtype()<=1200){
					typename="<=1200";
				}else if(pc.getSubtype()<=3600){
					typename="<=3600";
				}else if(pc.getSubtype()<=6000){
					typename="<=6000";
				}else if(pc.getSubtype()<=12000){
					typename="<=12000";
				}else if(pc.getSubtype()>12000){
					typename="<=12000";
				}
			}else if(searchData.getChecktype3()!=null&&searchData.getChecktype3().equals("4")){
				//4、付费次数
				if(pc.getSubtype()==0){
					typename="未付费";
				}else if(pc.getSubtype()<=5){
					typename=pc.getSubtype()+"次";
				}else if(pc.getSubtype()<=10){
					typename="6~10次";
				}else if(pc.getSubtype()<=20){
					typename="11~20次";
				}else if(pc.getSubtype()<=30){
					typename="21~30次";
				}else if(pc.getSubtype()<=40){
					typename="31~40次";
				}else if(pc.getSubtype()>40){
					typename="41+";
				}
			}
			pc.setStatdate(typename);
			boolean found = false;
			for(PlayersChurn e: rlist){
				if(typename.equals(e.getStatdate())){
					found = true;
					//
					e.setAccounts(e.getAccounts() + pc.getAccounts());
					e.setRate(NumberUtil.mul(NumberUtil.div(e.getAccounts().doubleValue(), totalAccounts,2), 100.0,NumberUtil.DEFAULT_SCALE));
					break;
				}
			}
			if(!found){
				pc.setRate(NumberUtil.mul(NumberUtil.div((double)pc.getAccounts(), totalAccounts,2), 100.0,NumberUtil.DEFAULT_SCALE));
				rlist.add(pc);
			}
		}
		return rlist;
	}

	@Override
	public List<PlayersChurn> exportLostUserAnalysNumList(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return playersChurnMapper.exportLostUserAnalysNumList(searchData);
	}
}
