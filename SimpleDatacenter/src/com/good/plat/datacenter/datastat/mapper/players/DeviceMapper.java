package com.good.plat.datacenter.datastat.mapper.players;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.players.Device;
import com.good.plat.datacenter.datastat.entity.players.PlayersChurn;

/**
 * 
 * @ClassName: DeviceMapper
 * @Description: TODO
 * @author moxw
 * @date 2016年4月27日 上午11:02:55
 */
public interface DeviceMapper {
	
	/**
	 * 
	 * @Title: selectPlayerMachineModel
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<Device>
	 * @author moxw
	 * @date 2016年4月27日 上午11:02:19
	 */
	List<Device> selectPlayerMachineModel (BaseSearchData searchData);
	
	
	/**
	 * 
	 * @Title: selectPlayerOperationSystem
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<Device>
	 * @author moxw
	 * @date 2016年4月27日 上午11:02:48
	 */
	List<Device> selectPlayerOperationSystem (BaseSearchData searchData);
}
