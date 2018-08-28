package com.good.plat.datacenter.datastat.service.virtual;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.virtual.ItemExpense;

/**
 * @ClassName: ItemExpenseMapper
 * @Description: 消费喜好
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
public interface ItemExpenseService {

	List<ItemExpense> selectItemPointList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	List<ItemExpense> exportItemPointList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	List<ItemExpense> selectPurchaseItemNumberList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	List<ItemExpense> exportPurchaseItemNumberList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	List<ItemExpense> selectPurchaseVirtualCurrencyList(
			BaseSearchData searchData, HttpSession session) throws Exception;

	List<ItemExpense> selectUseinfoItemNumberList(BaseSearchData searchData,
			HttpSession session) throws Exception;

	List<ItemExpense> selectVcConsumeNumOfPeople(BaseSearchData searchData,
			HttpSession session) throws Exception;

}
