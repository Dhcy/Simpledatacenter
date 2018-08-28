package com.good.plat.datacenter.datastat.service.impl.players;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.players.Device;
import com.good.plat.datacenter.datastat.mapper.players.DeviceMapper;
import com.good.plat.datacenter.datastat.service.players.PlayerDeviceService;

@Service(value="playerDeviceService")
public class PlayerDeviceServiceImpl implements PlayerDeviceService {
	@Autowired
	private DeviceMapper deviceMapper;
	
	@Override
	public List<Device> selectPlayerMachineModel(BaseSearchData searchData) {
		searchData.searchDataFilter(searchData);
		return deviceMapper.selectPlayerMachineModel(searchData);
	}
	
	@Override
	public List<Device> selectPlayerOperationSystem(BaseSearchData searchData) {
		searchData.searchDataFilter(searchData);
		return deviceMapper.selectPlayerOperationSystem(searchData);
	}
}
