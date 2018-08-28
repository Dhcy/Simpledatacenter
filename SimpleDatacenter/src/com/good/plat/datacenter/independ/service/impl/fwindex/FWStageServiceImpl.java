package com.good.plat.datacenter.independ.service.impl.fwindex;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.independ.entity.fwindex.FWStage;
import com.good.plat.datacenter.independ.mapper.fwindex.FWStageMapper;
import com.good.plat.datacenter.independ.service.fwindex.FWStageService;
@Service(value="fWStageServiceImpl")
public class FWStageServiceImpl implements FWStageService {
	@Autowired
	private FWStageMapper fWStageMapper;
	@Override
	public List<FWStage> selectStageList(BaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		List<FWStage> stageList=fWStageMapper.selectStageList(searchData);
		if(stageList!=null&&stageList.size()>0){
			for(FWStage f:stageList){
				f.setSuccdRate(NumberUtil.multi100(f.getSuccdRate(), NumberUtil.DEFAULT_SCALE));
			}
		}
		return stageList;
	}
	@Override
	public List<FWStage> selectFtwStageList(BaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		List<FWStage> ftwstageList=fWStageMapper.selectFtwStageList(searchData);
		if(ftwstageList!=null&&ftwstageList.size()>0){
			for(FWStage f:ftwstageList){
				f.setSuccdRate(NumberUtil.multi100(f.getSuccdRate(), NumberUtil.DEFAULT_SCALE));
			}
		}
		return ftwstageList;
	}

}
