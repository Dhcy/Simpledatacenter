package com.good.plat.datacenter.independ.service.impl.sj3index;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.independ.entity.sj3index.YuanLing;
import com.good.plat.datacenter.independ.mapper.sj3index.YuanLingMapper;
import com.good.plat.datacenter.independ.service.sj3index.YuanLingService;
@Service(value="yuanLingService")
public class YuanLingServiceImpl implements YuanLingService {
	@Autowired
	private YuanLingMapper yuanLingMapper;
	@Override
	public List<YuanLing> selectYlNameAndUserCntList(BaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		List<YuanLing> list=yuanLingMapper.selectYlNameAndUserCntList(searchData);
		return list;
	}
	@Override
	public List<YuanLing> selectYlslcCntList(BaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		List<YuanLing> list=yuanLingMapper.selectYlslcCntList(searchData);
		return list;
	}
	@Override
	public List<YuanLing> selectYlLevelAndUserCntList(BaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		List<YuanLing> list=yuanLingMapper.selectYlLevelAndUserCntList(searchData);
		return list;
	}
	@Override
	public List<YuanLing> selectYlSimExtractCntList(BaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		List<YuanLing> list=yuanLingMapper.selectYlSimExtractCntList(searchData);
		return list;
	}
	@Override
	public List<YuanLing> selectYlDetailList(BaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		List<YuanLing> list=yuanLingMapper.selectYlDetailList(searchData);
		return list;
	}

}
