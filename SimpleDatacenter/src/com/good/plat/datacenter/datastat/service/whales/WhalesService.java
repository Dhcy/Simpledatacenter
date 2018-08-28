package com.good.plat.datacenter.datastat.service.whales;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.whales.Whales;

/**
 * @ClassName: WhalesMapper
 * @Description: 鲸鱼用户
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
public interface WhalesService {

	/**
	 * 对结果集作了长度限制
	 * @Title: selectWhaleUserList
	 * @Description: 充值玩家
	 * @param searchData
	 * @return List<Whales>
	 * @author dmw
	 * @date 2016年3月15日 上午10:52:34
	 */
	List<Whales> selectWhaleUserList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * 对结果集长度不做限制
	 * @Title: exportWhaleUserList
	 * @Description: 导出充值玩家
	 * @param searchData
	 * @return List<Whales>
	 * @author dmw
	 * @date 2016年3月15日 上午10:53:05
	 */
	List<Whales> exportWhaleUserList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: selectChargeUserList
	 * @Description: 鲸鱼用户详细信息
	 * @param searchData
	 * @return List<Whales>
	 * @author dmw
	 * @date 2016年3月15日 上午11:09:36
	 */
	List<Whales> selectChargeUserList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: selectChargeInfoList
	 * @Description: 鲸鱼用户充值记录
	 * @param searchData
	 * @return List<Whales>
	 * @author dmw
	 * @date 2016年3月15日 上午11:10:57
	 */
	List<Whales> selectChargeInfoList(BaseSearchData searchData,
			HttpSession session) throws Exception;
}
