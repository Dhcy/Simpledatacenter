package com.good.plat.datacenter.independ.service.impl.sj3index;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.independ.entity.sj3index.FamSummary;
import com.good.plat.datacenter.independ.mapper.sj3index.FamSummaryMapper;
import com.good.plat.datacenter.independ.service.sj3index.FamService;
@Service(value="famService")
public class FamServiceImpl implements FamService {
	@Autowired
	private FamSummaryMapper famSummaryMapper;
	@Override
	public List<FamSummary> selectFamSummaryList(BaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub\
		searchData=searchData.searchDataFilter(searchData);
		 List<FamSummary> list=famSummaryMapper.selectFamSummaryList(searchData);
		 if(list!=null&&!list.isEmpty()){
			 for(FamSummary e:list){
				 e.setAvgTime(e.getSuccdCnt().intValue()==0?0.0:NumberUtil.ajustScale(e.getTotalTime().doubleValue()/e.getSuccdCnt(),NumberUtil.DEFAULT_SCALE));
				 e.setAvgEnterTimes(e.getAvgEnterTimes()==null?null:NumberUtil.ajustScale(e.getAvgEnterTimes(), NumberUtil.DEFAULT_SCALE));
			 }
		 }
		return list;
	}

}
