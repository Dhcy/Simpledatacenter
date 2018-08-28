package com.good.plat.datacenter.datastat.service.impl.gift;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.datastat.entity.gift.GiftData;
import com.good.plat.datacenter.datastat.mapper.gift.GiftMapper;
import com.good.plat.datacenter.datastat.service.gift.IGiftService;

@Service(value="giftService")
public class GiftServiceImpl implements IGiftService{
	@Autowired
	private GiftMapper giftMapper;
	
	@Override
	public List<GiftData> selectGiftReceive(BaseSearchData searchData) {
		searchData.searchDataFilter(searchData);
		return giftMapper.selectGiftReceive(searchData);
	}
	
	@Override
	public List<GiftData> selectGiftReceiveDetail(BaseSearchData searchData) {
		searchData.searchDataFilter(searchData);
		return giftMapper.selectGiftReceiveDetail(searchData);
	}
}
