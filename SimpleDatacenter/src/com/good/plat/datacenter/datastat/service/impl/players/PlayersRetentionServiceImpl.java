package com.good.plat.datacenter.datastat.service.impl.players;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.base.Validator;
import com.good.plat.datacenter.datastat.entity.players.PlayersRetention;
import com.good.plat.datacenter.datastat.mapper.players.PlayersRetentionMapper;
import com.good.plat.datacenter.datastat.service.players.PlayersRetentionService;

/**
 * @ClassName: PlayersRetentionMapper
 * @Description: 玩家留存
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
@Service(value="playersRetentionService")
public class PlayersRetentionServiceImpl implements PlayersRetentionService{

	@Autowired
	private PlayersRetentionMapper playersRetentionMapper;
	
	@Override
	public List<PlayersRetention> selectRetentionOfNewUser(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		List<PlayersRetention> list = playersRetentionMapper.selectRetentionOfNewUser(searchData);
		if(list != null){
			for(PlayersRetention e: list){
				if(e.getDay1() == null){
					e.setDay1(0.0);
				}
				if(e.getDay2() == null){
					e.setDay2(0.0);
				}
				if(e.getDay3() == null){
					e.setDay3(0.0);
				}
				if(e.getDay4() == null){
					e.setDay4(0.0);
				}
				if(e.getDay5() == null){
					e.setDay5(0.0);
				}
				if(e.getDay6() == null){
					e.setDay6(0.0);
				}
				if(e.getDay7() == null){
					e.setDay7(0.0);
				}
				if(e.getDay14() == null){
					e.setDay14(0.0);
				}
				if(e.getDay30() == null){
					e.setDay30(0.0);
				}
				if(e.getDay60()==null){
				e.setDay60(0.0);
				}
				if(e.getDay90()==null){
					e.setDay90(0.0);
				}
				if(e.getDay120()==null){
					e.setDay120(0.0);
				}
				if(e.getDay150()==null){
					e.setDay150(0.0);
				}
				if(e.getDay180()==null){
					e.setDay180(0.0);
				}
			}
		}
		return list;
	}
	
	
	@Override
	public List<PlayersRetention> exportRetentionOfNewUser(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return playersRetentionMapper.exportRetentionOfNewUser(searchData);
	}

	@Override
	public List<PlayersRetention> selectRetentionOfDevice(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return playersRetentionMapper.selectRetentionOfDevice(searchData);
	}

	@Override
	public List<PlayersRetention> exportRetentionOfDevice(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return playersRetentionMapper.exportRetentionOfDevice(searchData);
	}

	@Override
	public List<PlayersRetention> selectRetentionUserAnalys(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		List<PlayersRetention> list = playersRetentionMapper.selectRetentionUserAnalys(searchData); 
		if(list != null){
			switch (Integer.valueOf(searchData.getChecktype1())) {
			case 1:
				Collections.sort(list, new Comparator<PlayersRetention>() {
					public int compare(PlayersRetention o1, PlayersRetention o2) {
						Integer v1 = Integer.valueOf(o1.getStatType());
						int v2 = Integer.valueOf(o2.getStatType());
						return v1.compareTo(v2);
					}
				});
				break;
			case 2:
				Collections.sort(list, new Comparator<PlayersRetention>() {
					public int compare(PlayersRetention o1, PlayersRetention o2) {
						Integer v1 = Integer.valueOf(o1.getStatType());
						int v2 = Integer.valueOf(o2.getStatType());
						return v1.compareTo(v2);
					}
				});
				//游戏次数区间: 1,2-3,4-5,6-10,11-20,21-50,50-
				PlayersRetention e1 = new PlayersRetention();
				e1.setStatType("<=1");
				e1.setAccounts(0);
				PlayersRetention e2_3 = new PlayersRetention();
				e2_3.setStatType("<=3");
				e2_3.setAccounts(0);
				PlayersRetention e4_5 = new PlayersRetention();
				e4_5.setStatType("<=5");
				e4_5.setAccounts(0);
				PlayersRetention e6_10 = new PlayersRetention();
				e6_10.setStatType("<=10");
				e6_10.setAccounts(0);
				PlayersRetention e11_20 = new PlayersRetention();
				e11_20.setStatType("<=20");
				e11_20.setAccounts(0);
				PlayersRetention e21_50 = new PlayersRetention();
				e21_50.setStatType("<=50");
				e21_50.setAccounts(0);
				PlayersRetention e51_ = new PlayersRetention();
				e51_.setStatType(">=51");
				e51_.setAccounts(0);
				for(int i = 0,length = list.size();i < length; ++i){
					PlayersRetention e = list.get(i);
					int times = Integer.valueOf(e.getStatType());
					if(times == 1){
						e1.setAccounts(null2Zero(e1.getAccounts()) + null2Zero(e.getAccounts()));
						//e1.setRate(e.getRate());
					}else if(times >= 2 && times <= 3){
						e2_3.setAccounts(null2Zero(e2_3.getAccounts()) + null2Zero(e.getAccounts()));
					}else if(times >= 4 && times <= 5){
						e4_5.setAccounts(null2Zero(e4_5.getAccounts()) + null2Zero(e.getAccounts()));
					}else if(times >= 6 && times <= 10){
						e6_10.setAccounts(null2Zero(e6_10.getAccounts()) + null2Zero(e.getAccounts()));
					}else if(times >= 11 && times <= 20){
						e11_20.setAccounts(null2Zero(e11_20.getAccounts()) + null2Zero(e.getAccounts()));
					}else if(times >= 21 && times <= 50){
						e21_50.setAccounts(null2Zero(e21_50.getAccounts()) + null2Zero(e.getAccounts()));
					}else if(times >= 51){
						e51_.setAccounts(null2Zero(e51_.getAccounts()) + null2Zero(e.getAccounts()));
					}
				}
				list.clear();
				list.add(e1);
				list.add(e2_3);
				list.add(e4_5);
				list.add(e6_10);
				list.add(e11_20);
				list.add(e21_50);
				list.add(e51_);
				break;
			default:
				break;
			}
		}
		return list;
	}
	public static Integer null2Zero(Integer obj){
		return obj == null ? 0 : obj;
	}
	@Override
	public List<PlayersRetention> exportRetentionUserAnalys(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return playersRetentionMapper.exportRetentionUserAnalys(searchData);
	}


	@Override
	public List<PlayersRetention> selectRetentionOfActUser(
			BaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData = searchData.searchDataFilter(searchData);
		List<PlayersRetention> list=playersRetentionMapper.selectRetentionOfActUser(searchData);
		if(list!=null){
			for(PlayersRetention p:list){
				if(p.getDay1()==null){
					p.setDay1(0.0);
				}
				if(p.getDay2()==null){
					p.setDay2(0.0);
				}
				if(p.getDay3()==null){
					p.setDay3(0.0);
				}
				if(p.getDay4()==null){
					p.setDay4(0.0);
				}
				if(p.getDay5()==null){
					p.setDay5(0.0);
				}
				if(p.getDay6()==null){
					p.setDay6(0.0);
				}
				if(p.getDay7()==null){
					p.setDay7(0.0);
				}
				if(p.getDay14()==null){
					p.setDay14(0.0);
				}
				if(p.getDay30()==null){
					p.setDay30(0.0);
				}
			}
		}
		return list;
	}


	@Override
	public List<PlayersRetention> selectRetentionOfreturnUser(
			BaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData = searchData.searchDataFilter(searchData);
		List<PlayersRetention> list=playersRetentionMapper.selectRetentionOfreturnUser(searchData);
		if(list!=null){
			for(PlayersRetention p:list){
				if(p.getDay1()==null){
					p.setDay1(0.0);
				}
				if(p.getDay2()==null){
					p.setDay2(0.0);
				}
				if(p.getDay3()==null){
					p.setDay3(0.0);
				}
				if(p.getDay4()==null){
					p.setDay4(0.0);
				}
				if(p.getDay5()==null){
					p.setDay5(0.0);
				}
				if(p.getDay6()==null){
					p.setDay6(0.0);
				}
				if(p.getDay7()==null){
					p.setDay7(0.0);
				}
				if(p.getDay14()==null){
					p.setDay14(0.0);
				}
				if(p.getDay30()==null){
					p.setDay30(0.0);
				}
			}
		}
		return list;
	}


	@Override
	public List<PlayersRetention> selectRetentionOfNewAccount(
			BaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData = searchData.searchDataFilter(searchData);
		List<PlayersRetention> list = playersRetentionMapper.selectRetentionOfNewAccount(searchData);
		if(list != null&&list.size()>0){
			for(PlayersRetention e: list){
				if(e.getDay1() == null){
					e.setDay1(0.0);
				}
				if(e.getDay2() == null){
					e.setDay2(0.0);
				}
				if(e.getDay3() == null){
					e.setDay3(0.0);
				}
				if(e.getDay4() == null){
					e.setDay4(0.0);
				}
				if(e.getDay5() == null){
					e.setDay5(0.0);
				}
				if(e.getDay6() == null){
					e.setDay6(0.0);
				}
				if(e.getDay7() == null){
					e.setDay7(0.0);
				}
				if(e.getDay14() == null){
					e.setDay14(0.0);
				}
				if(e.getDay30() == null){
					e.setDay30(0.0);
				}
				if(e.getDay60()==null){
					e.setDay60(0.0);
				}
				if(e.getDay90()==null){
					e.setDay90(0.0);
				}
				if(e.getDay120()==null){
					e.setDay120(0.0);
				}
				if(e.getDay150()==null){
					e.setDay150(0.0);
				}
				if(e.getDay180()==null){
					e.setDay180(0.0);
				}
			}
		}
		return list;
	}


	@Override
	public List<PlayersRetention> selectUserRetainCityList(
			BaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData = searchData.searchDataFilter(searchData);
		List<PlayersRetention> cityList=playersRetentionMapper.selectUserRetainCityList(searchData);
		if(cityList!=null&&!cityList.isEmpty()){
			Iterator<PlayersRetention> it=cityList.iterator();
			if(it.hasNext()){
				PlayersRetention retentionCity=it.next();
				if(Validator.isNullOrBlank(retentionCity.getCity())){
					it.remove();
				}
			}
		}
		return cityList;
	}
}
