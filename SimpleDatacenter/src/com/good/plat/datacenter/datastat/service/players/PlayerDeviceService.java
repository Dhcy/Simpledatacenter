package com.good.plat.datacenter.datastat.service.players;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.players.Device;
import com.good.plat.datacenter.datastat.entity.players.PlayersChurn;

/**
 * 
 * @ClassName: PlayerDeviceService
 * @Description: TODO
 * @author moxw
 * @date 2016年4月27日 上午11:04:23
 */
public interface PlayerDeviceService {
	/**
	 * 
	 * @Title: selectPlayerMachineModel
	 * @Description: 玩家设备机型
	 * @param searchData
	 * @return
	 * List<Device>
	 * @author moxw
	 * @date 2016年4月27日 上午11:04:47
	 */
	List<Device> selectPlayerMachineModel (BaseSearchData searchData);
	
	/**
	 * 
	 * @Title: selectPlayerOperationSystem
	 * @Description: 玩家设备操作系统
	 * @param searchData
	 * @return
	 * List<Device>
	 * @author moxw
	 * @date 2016年4月27日 上午11:04:53
	 */
	List<Device> selectPlayerOperationSystem (BaseSearchData searchData);
}
