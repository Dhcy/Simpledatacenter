package com.good.plat.datacenter.independ.service.impl.sj3index;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.independ.entity.sj3index.GoodsMaterials;
import com.good.plat.datacenter.independ.mapper.sj3index.GoodsMaterialsMapper;
import com.good.plat.datacenter.independ.service.sj3index.GoodsMaterialsService;
@Service(value="goodsMaterialsService")
public class GoodsMaterialsServiceImpl implements GoodsMaterialsService{
	@Autowired
	private GoodsMaterialsMapper goodsMaterialsMapper;
	@Override
	public List<GoodsMaterials> selectGoodsSummaryList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<GoodsMaterials> list=goodsMaterialsMapper.selectGoodsSummaryList(searchData);
		return list;
	}
	@Override
	public List<GoodsMaterials> selectBugMethodGoodsDetailList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<GoodsMaterials> list=goodsMaterialsMapper.selectBugMethodGoodsDetailList(searchData);
		return list;
	}

}
