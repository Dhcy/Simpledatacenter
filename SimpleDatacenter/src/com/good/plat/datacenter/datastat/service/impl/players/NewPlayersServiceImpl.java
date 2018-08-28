package com.good.plat.datacenter.datastat.service.impl.players;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.players.NewPlayers;
import com.good.plat.datacenter.datastat.mapper.players.NewPlayersMapper;
import com.good.plat.datacenter.datastat.service.players.NewPlayersService;

/**
 * @ClassName: NewPlayersMapper
 * @Description: 新增玩家
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
@Service(value="newPlayersService")
public class NewPlayersServiceImpl implements NewPlayersService{

	@Autowired
	private NewPlayersMapper newPlayersMapper;
	
	@Override
	public List<NewPlayers> selectNewPlayerList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return newPlayersMapper.selectNewPlayerList(searchData);
	}

	@Override
	public List<NewPlayers> exportNewPlayerList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return newPlayersMapper.exportNewPlayerList(searchData);
	}

	@Override
	public List<NewPlayers> selectConversionList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return newPlayersMapper.selectConversionList(searchData);
	}

	@Override
	public List<NewPlayers> exportConversionList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return newPlayersMapper.exportConversionList(searchData);
	}

	@Override
	public List<NewPlayers> selectNewPlayerOfCityList(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return newPlayersMapper.selectNewPlayerOfCityList(searchData);
	}

	@Override
	public List<NewPlayers> exportNewPlayerOfCityList(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return newPlayersMapper.exportNewPlayerOfCityList(searchData);
	}

	@Override
	public List<NewPlayers> selectNewPlayerOfChannelList(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		List<NewPlayers> list = newPlayersMapper.selectNewPlayerOfChannelList(searchData);
		return list;
	}

	@Override
	public List<NewPlayers> exportNewPlayerOfChannelList(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return newPlayersMapper.exportNewPlayerOfChannelList(searchData);
	}

	@Override
	public List<NewPlayers> selectNewPlayerOfSubsidiaryList(
			BaseSearchData searchData, HttpSession session) throws Exception {
		return newPlayersMapper.selectNewPlayerOfSubsidiaryList(searchData);
	}

	@Override
	public List<NewPlayers> exportNewPlayerOfSubsidiaryList(
			BaseSearchData searchData, HttpSession session) throws Exception {
		return newPlayersMapper.exportNewPlayerOfSubsidiaryList(searchData);
	}

	@Override
	public List<NewPlayers> selectNewPlayerCityList(BaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData = searchData.searchDataFilter(searchData);
		List<NewPlayers> list=newPlayersMapper.selectNewPlayerCityList(searchData);
		if(list!=null){
			Iterator<NewPlayers> iter = list.iterator();
			while(iter.hasNext()){
				NewPlayers player=iter.next();
				if(player==null){
					iter.remove();
				}
			}
		}
		return list;
	}

	@Override
	public List<NewPlayers> selectNewPlayerListOfCity(BaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData = searchData.searchDataFilter(searchData);
		List<NewPlayers> list=newPlayersMapper.selectNewPlayerListOfCity(searchData);
		return list;
	}

	@Override
	public List<NewPlayers> selectNewPlayerListOfHour(BaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData = searchData.searchDataFilter(searchData);
		List<NewPlayers> list=newPlayersMapper.selectNewPlayerListOfHour(searchData);
		if(list!=null&!list.isEmpty()){
			for(NewPlayers e:list){
				e.setHourDateDesc(e.getHourdate()==null?"NULL":e.getHourdate()+"点");
			}
		}
		return list;
	}
	
	

}
