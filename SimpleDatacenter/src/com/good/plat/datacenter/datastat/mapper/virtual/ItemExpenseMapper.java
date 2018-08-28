package com.good.plat.datacenter.datastat.mapper.virtual;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.virtual.ItemExpense;

/**
 * @ClassName: ItemExpenseMapper
 * @Description: 消费喜好
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
public interface ItemExpenseMapper {
	
	List<ItemExpense> selectItemPointList (BaseSearchData searchData);
	

	List<ItemExpense> exportItemPointList (BaseSearchData searchData);


	List<ItemExpense> selectPurchaseItemNumberList (BaseSearchData searchData);

	
	List<ItemExpense> exportPurchaseItemNumberList (BaseSearchData searchData);

	
	List<ItemExpense> selectPurchaseVirtualCurrencyList (BaseSearchData searchData);

	
	List<ItemExpense> selectUseinfoItemNumberList (BaseSearchData searchData);

	
	List<ItemExpense> selectVcConsumeNumOfPeople (BaseSearchData searchData);

	
	
}
