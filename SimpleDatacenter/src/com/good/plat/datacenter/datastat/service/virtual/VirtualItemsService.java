package com.good.plat.datacenter.datastat.service.virtual;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.virtual.VirtualItems;

/**
 * @ClassName: VirtualItemsMapper
 * @Description: 消费点
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
public interface VirtualItemsService {

	/**
	 * @Title: selectItemPointList
	 * @Description: 热门物品
	 * @param searchData
	 * @return List<VirtualItems>
	 * @author dmw
	 * @date 2016年3月17日 上午9:53:42
	 */
	List<VirtualItems> selectItemPointList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	// 导出
	List<VirtualItems> exportItemPointList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	List<VirtualItems> selectDailyItemPointDetailList(BaseSearchData searchData,
			HttpSession session) throws Exception;
	List<VirtualItems> selectLevelItemPointDetailList(BaseSearchData searchData,
			HttpSession session) throws Exception;
	
	/**
	 * @Title: selectPurchaseItemNumberList
	 * @Description: 购买数量
	 * @param searchData
	 * @return List<VirtualItems>
	 * @author dmw
	 * @date 2016年3月17日 上午9:54:13
	 */
	List<VirtualItems> selectPurchaseItemNumberList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: selectPurchaseVirtualCurrencyList
	 * @Description: 虚拟币总值
	 * @param searchData
	 * @return List<VirtualItems>
	 * @author dmw
	 * @date 2016年3月17日 上午9:54:38
	 */
	List<VirtualItems> selectPurchaseVirtualCurrencyList(
			BaseSearchData searchData, HttpSession session) throws Exception;

	/**
	 * @Title: selectUseinfoItemNumberList
	 * @Description: 消耗数量
	 * @param searchData
	 * @return List<VirtualItems>
	 * @author dmw
	 * @date 2016年3月17日 上午9:55:13
	 */
	List<VirtualItems> selectUseinfoItemNumberList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	/**
	 * @Title: selectVcConsumeNumOfPeople
	 * @Description: 购买人数
	 * @param searchData
	 * @return List<VirtualItems>
	 * @author dmw
	 * @date 2016年3月17日 上午9:55:16
	 */
	List<VirtualItems> selectVcConsumeNumOfPeople(BaseSearchData searchData,
			HttpSession session) throws Exception;

}
