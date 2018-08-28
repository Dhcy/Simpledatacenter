package com.good.plat.datacenter.independ.service.impl.sj3index;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.independ.entity.sj3index.Gold;
import com.good.plat.datacenter.independ.mapper.sj3index.GoldMapper;
import com.good.plat.datacenter.independ.service.sj3index.GoldService;

@Service(value="goldService")
public class GoldServiceImpl implements GoldService {
	@Autowired
	private GoldMapper goldMapper;
	@Override
	public List<Gold> selectLvlAndGoldNumList(BaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		List<Gold> totalList=new ArrayList<Gold>();
		totalList=goldMapper.selectLvlAndGoldNumList(searchData);
		return totalList;
	}

}
