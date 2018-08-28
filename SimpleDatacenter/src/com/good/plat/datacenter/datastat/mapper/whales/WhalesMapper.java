package com.good.plat.datacenter.datastat.mapper.whales;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.whales.Whales;

/**
 * @ClassName: WhalesMapper
 * @Description: 鲸鱼用户
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
public interface WhalesMapper {
	
	/**
	 * @Title: selectWhaleUserList
	 * @Description: 充值玩家
	 * @param searchData
	 * @return
	 * List<Whales>
	 * @author dmw
	 * @date 2016年3月15日 上午10:52:34
	 */
	List<Whales> selectWhaleUserList (BaseSearchData searchData);
	
	
	/**
	 * @Title: exportWhaleUserList
	 * @Description: 导出充值玩家
	 * @param searchData
	 * @return
	 * List<Whales>
	 * @author dmw
	 * @date 2016年3月15日 上午10:53:05
	 */
	List<Whales> exportWhaleUserList (BaseSearchData searchData);
	
	
	/**
	 * @Title: selectChargeUserList
	 * @Description: 鲸鱼用户详细信息
	 * @param searchData
	 * @return
	 * List<Whales>
	 * @author dmw
	 * @date 2016年3月15日 上午11:09:36
	 */
	List<Whales> selectChargeUserList (BaseSearchData searchData);
	
	/**
	 * @Title: selectChargeInfoList
	 * @Description: 鲸鱼用户充值记录
	 * @param searchData
	 * @return
	 * List<Whales>
	 * @author dmw
	 * @date 2016年3月15日 上午11:10:57
	 */
	List<Whales> selectChargeInfoList (BaseSearchData searchData);
}
