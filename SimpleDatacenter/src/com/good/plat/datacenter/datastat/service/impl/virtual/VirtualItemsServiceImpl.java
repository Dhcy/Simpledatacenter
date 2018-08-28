package com.good.plat.datacenter.datastat.service.impl.virtual;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.virtual.VirtualItems;
import com.good.plat.datacenter.datastat.mapper.virtual.VirtualItemsMapper;
import com.good.plat.datacenter.datastat.service.virtual.VirtualItemsService;

/**
 * @ClassName: VirtualItemsMapper
 * @Description: 消费点
 * @author dmw
 * @date 2016年3月15日 上午10:32:49
 */
@Service(value="virtualItemsService")
public class VirtualItemsServiceImpl implements VirtualItemsService {

	@Autowired
	private VirtualItemsMapper virtualItemsMapper;
	
	@Override
	public List<VirtualItems> selectItemPointList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return virtualItemsMapper.selectItemPointList(searchData);
	}

	@Override
	public List<VirtualItems> exportItemPointList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return virtualItemsMapper.exportItemPointList(searchData);
	}
	
	@Override
	public List<VirtualItems> selectDailyItemPointDetailList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return virtualItemsMapper.selectDailyItemPointDetailList(searchData);
	}
	
	@Override
	public List<VirtualItems> selectLevelItemPointDetailList(BaseSearchData searchData,
			HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return virtualItemsMapper.selectLevelItemPointDetailList(searchData);
	}

	@Override
	public List<VirtualItems> selectPurchaseItemNumberList(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return virtualItemsMapper.selectPurchaseItemNumberList(searchData);
	}

	@Override
	public List<VirtualItems> selectPurchaseVirtualCurrencyList(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return virtualItemsMapper.selectPurchaseVirtualCurrencyList(searchData);
	}

	@Override
	public List<VirtualItems> selectUseinfoItemNumberList(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return virtualItemsMapper.selectUseinfoItemNumberList(searchData);
	}

	@Override
	public List<VirtualItems> selectVcConsumeNumOfPeople(
			BaseSearchData searchData, HttpSession session) throws Exception {
		searchData = searchData.searchDataFilter(searchData);
		return virtualItemsMapper.selectVcConsumeNumOfPeople(searchData);
	}
	
}
