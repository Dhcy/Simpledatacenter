package com.good.plat.datacenter.independ.service.impl.fwindex;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.independ.entity.fwindex.FWPrologue;
import com.good.plat.datacenter.independ.mapper.fwindex.FWPrologueMapper;
import com.good.plat.datacenter.independ.service.fwindex.FWPrologueService;
@Service(value="fWPrologueServiceImpl")
public class FWPrologueServiceImpl implements FWPrologueService {
	@Autowired
	private FWPrologueMapper fWPrologueMapper;
	@Override
	public List<FWPrologue> selectPrologueList(BaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		List<FWPrologue> list=fWPrologueMapper.selectPrologueList(searchData);
		return list;
	}

}
