package com.good.plat.datacenter.datastat.service.impl.players;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.base.Validator;
import com.good.plat.datacenter.datastat.entity.players.ActiveByChnnel;
import com.good.plat.datacenter.datastat.entity.players.ActiveByTime;
import com.good.plat.datacenter.datastat.entity.players.ActivePlayers;
import com.good.plat.datacenter.datastat.entity.players.PlayersRetention;
import com.good.plat.datacenter.datastat.mapper.players.ActivePlayersMapper;
import com.good.plat.datacenter.datastat.service.players.ActivePlayersService;

/**
 * @ClassName: ActivePlayersMapper
 * @Description: 活跃玩家
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
@Service(value="activePlayersService")
public class ActivePlayersServiceImpl implements ActivePlayersService {

	@Autowired
	private ActivePlayersMapper activePlayersMapper;
	
	@Override
	public List<ActivePlayers> selectDauList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return activePlayersMapper.selectDauList(searchData);
	}

	@Override
	public List<ActivePlayers> exportDauList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return activePlayersMapper.exportDauList(searchData);
	}

	@Override
	public List<ActivePlayers> selectWauList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return activePlayersMapper.selectWauList(searchData);
	}

	@Override
	public List<ActivePlayers> exportWauList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return activePlayersMapper.exportWauList(searchData);
	}

	@Override
	public List<ActivePlayers> selectMauList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return activePlayersMapper.selectMauList(searchData);
	}

	@Override
	public List<ActivePlayers> exportMauList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return activePlayersMapper.exportMauList(searchData);
	}

	@Override
	public List<ActivePlayers> selectDauDivideMauList(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return activePlayersMapper.selectDauDivideMauList(searchData);
	}

	@Override
	public List<ActivePlayers> exportDauDivideMauList(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return activePlayersMapper.exportDauDivideMauList(searchData);
	}

	@Override
	public List<ActivePlayers> selectDaysOfActivePlayer(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return activePlayersMapper.selectDaysOfActivePlayer(searchData);
	}

	@Override
	public List<ActivePlayers> exportDaysOfActivePlayer(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return activePlayersMapper.exportDaysOfActivePlayer(searchData);
	}

	@Override
	public List<ActivePlayers> selectLevelOfActivePlayer(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return activePlayersMapper.selectLevelOfActivePlayer(searchData);
	}

	@Override
	public List<ActivePlayers> exportLevelOfActivePlayer(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return activePlayersMapper.exportLevelOfActivePlayer(searchData);
	}

	@Override
	public List<ActivePlayers> selectCityOfActivePlayer(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return activePlayersMapper.selectCityOfActivePlayer(searchData);
	}

	@Override
	public List<ActivePlayers> exportCityOfActivePlayer(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return activePlayersMapper.exportCityOfActivePlayer(searchData);
	}

	@Override
	public List<ActivePlayers> selectChannelOfActivePlayer(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return activePlayersMapper.selectChannelOfActivePlayer(searchData);
	}

	@Override
	public List<ActivePlayers> exportChannelOfActivePlayer(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return activePlayersMapper.exportChannelOfActivePlayer(searchData);
	}

	@Override
	public List<ActivePlayers> selectActUserCityList(BaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData = searchData.searchDataFilter(searchData);
		List<ActivePlayers> cityList=activePlayersMapper.selectActUserCityList(searchData);
		if(cityList!=null&&!cityList.isEmpty()){
			Iterator<ActivePlayers> it=cityList.iterator();
			if(it.hasNext()){
				ActivePlayers actUserCity=it.next();
				if(Validator.isNullOrBlank(actUserCity.getCity())){
					it.remove();
				}
			}
		}
		return cityList;
	}

	public List<ActiveByTime> selectActUserByTimeList(BaseSearchData searchData) {
		searchData = searchData.searchDataFilter(searchData);
		List<ActiveByTime> list=activePlayersMapper.selectActUserByTimeList(searchData);
		return list;
	}

	public List<ActiveByChnnel> selectActUserChannelList(BaseSearchData searchData) {
		searchData = searchData.searchDataFilter(searchData);
		List<ActiveByChnnel> list=activePlayersMapper.selectActUserChannelList(searchData);
		return list;
	}
}
