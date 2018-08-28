package com.good.plat.datacenter.datastat.service.impl.virtual;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.virtual.ItemExpense;
import com.good.plat.datacenter.datastat.mapper.virtual.ItemExpenseMapper;
import com.good.plat.datacenter.datastat.service.virtual.ItemExpenseService;

/**
 * @ClassName: ItemExpenseMapper
 * @Description: 消费喜好
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
@Service(value="itemExpenseService")
public class ItemExpenseServiceImpl implements ItemExpenseService{

	@Autowired
	private ItemExpenseMapper itemExpenseMapper;
	
	
	@Override
	public List<ItemExpense> selectItemPointList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return itemExpenseMapper.selectItemPointList(searchData);
	}

	@Override
	public List<ItemExpense> exportItemPointList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return itemExpenseMapper.exportItemPointList(searchData);
	}

	@Override
	public List<ItemExpense> selectPurchaseItemNumberList(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return itemExpenseMapper.selectPurchaseItemNumberList(searchData);
	}

	@Override
	public List<ItemExpense> exportPurchaseItemNumberList(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return itemExpenseMapper.exportPurchaseItemNumberList(searchData);
	}

	@Override
	public List<ItemExpense> selectPurchaseVirtualCurrencyList(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return itemExpenseMapper.selectPurchaseVirtualCurrencyList(searchData);
	}

	@Override
	public List<ItemExpense> selectUseinfoItemNumberList(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return itemExpenseMapper.selectUseinfoItemNumberList(searchData);
	}

	@Override
	public List<ItemExpense> selectVcConsumeNumOfPeople(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return itemExpenseMapper.selectVcConsumeNumOfPeople(searchData);
	}
	

	
	
}
