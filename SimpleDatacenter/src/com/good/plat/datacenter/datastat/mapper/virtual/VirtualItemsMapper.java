package com.good.plat.datacenter.datastat.mapper.virtual;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.virtual.VirtualItems;

/**
 * @ClassName: VirtualItemsMapper
 * @Description: 消费点
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
public interface VirtualItemsMapper {
	
	/**
	 * @Title: selectItemPointList
	 * @Description: 热门物品
	 * @param searchData
	 * @return
	 * List<VirtualItems>
	 * @author dmw
	 * @date 2016年3月17日 上午9:53:42
	 */
	List<VirtualItems> selectItemPointList (BaseSearchData searchData);
	
	//导出
	List<VirtualItems> exportItemPointList (BaseSearchData searchData);

	List<VirtualItems> selectDailyItemPointDetailList (BaseSearchData searchData);
	List<VirtualItems> selectLevelItemPointDetailList (BaseSearchData searchData);
	/**
	 * @Title: selectPurchaseItemNumberList
	 * @Description: 购买数量
	 * @param searchData
	 * @return
	 * List<VirtualItems>
	 * @author dmw
	 * @date 2016年3月17日 上午9:54:13
	 */
	List<VirtualItems> selectPurchaseItemNumberList (BaseSearchData searchData);

	
	/**
	 * @Title: selectPurchaseVirtualCurrencyList
	 * @Description: 虚拟币总值
	 * @param searchData
	 * @return
	 * List<VirtualItems>
	 * @author dmw
	 * @date 2016年3月17日 上午9:54:38
	 */
	List<VirtualItems> selectPurchaseVirtualCurrencyList (BaseSearchData searchData);

	
	/**
	 * @Title: selectUseinfoItemNumberList
	 * @Description: 消耗数量
	 * @param searchData
	 * @return
	 * List<VirtualItems>
	 * @author dmw
	 * @date 2016年3月17日 上午9:55:13
	 */
	List<VirtualItems> selectUseinfoItemNumberList (BaseSearchData searchData);

	/**
	 * @Title: selectVcConsumeNumOfPeople
	 * @Description: 购买人数
	 * @param searchData
	 * @return
	 * List<VirtualItems>
	 * @author dmw
	 * @date 2016年3月17日 上午9:55:16
	 */
	List<VirtualItems> selectVcConsumeNumOfPeople (BaseSearchData searchData);

	
}
