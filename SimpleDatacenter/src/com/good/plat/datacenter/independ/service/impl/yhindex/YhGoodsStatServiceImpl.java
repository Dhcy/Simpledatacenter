package com.good.plat.datacenter.independ.service.impl.yhindex;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.independ.entity.yhindex.YhGoodsStat;
import com.good.plat.datacenter.independ.mapper.yhindex.YhGoodsStatMapper;
import com.good.plat.datacenter.independ.service.yhindex.YhGoodsStatService;
@Service(value="yhGoodsStatService")
public class YhGoodsStatServiceImpl implements YhGoodsStatService {
	@Autowired
	private YhGoodsStatMapper yhGoodsStatMapper;
	@Override
	public List<YhGoodsStat> selectYhGoodsSoldList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<YhGoodsStat> list=yhGoodsStatMapper.selectYhGoodsSoldList(searchData);
		return list;
	}

}
