package com.good.plat.datacenter.independ.service.impl.sj3index;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.independ.entity.sj3index.SySummary;
import com.good.plat.datacenter.independ.mapper.sj3index.SySummaryMapper;
import com.good.plat.datacenter.independ.service.sj3index.SySummaryService;
@Service(value="sySummaryService")
public class SySummaryServiceImpl implements SySummaryService {
	@Autowired
	private SySummaryMapper sySummaryMapper;
	@Override
	public List<SySummary> selectSyFengMoTuanList(BaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		List<SySummary> list=sySummaryMapper.selectSyFengMoTuanList(searchData);
		if(list!=null&&list.size()!=0){
			for(SySummary e:list){
				if(e!=null){
					Double avgSpeedTime=e.getSucctCnt().intValue()==0?0.0:e.getTotalTime().doubleValue()/e.getSucctCnt();
					//平均通关用时
					e.setAvgTime(NumberUtil.ajustScale(avgSpeedTime, NumberUtil.DEFAULT_SCALE));
					e.setAvgStartCnt(NumberUtil.ajustScale(e.getAvgStartCnt(), NumberUtil.DEFAULT_SCALE));
				}
			}
		}
		return list;
	}
	@Override
	public List<SySummary> selectSyJueWeiList(BaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		List<SySummary> list=sySummaryMapper.selectSyJueWeiList(searchData);
		if(list!=null&&list.size()!=0){
			for(SySummary e:list){
				if(e!=null){
					//平均战斗用时
					Double avgSpeedTime=e.getStartCnt().intValue()==0?0.0:e.getTotalTime().doubleValue()/e.getStartCnt();
					e.setAvgTime(NumberUtil.ajustScale(avgSpeedTime, NumberUtil.DEFAULT_SCALE));
				}
			}
		}
		return list;
	}
	@Override
	public List<SySummary> selectSyLieMoList(BaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		List<SySummary> list=sySummaryMapper.selectSyLieMoList(searchData);
		if(list!=null&&list.size()!=0){
			for(SySummary e:list){
				if(e!=null){
					//完成平均用时
					Double avgSpeedTime=e.getSucctCnt().intValue()==0?0.0:e.getTotalTime().doubleValue()/e.getSucctCnt();
					e.setAvgTime(NumberUtil.ajustScale(avgSpeedTime, NumberUtil.DEFAULT_SCALE));
					//人均开启次数
					e.setAvgStartTimes(e.getAvgStartTimes()!=null?NumberUtil.ajustScale(e.getAvgStartTimes(), NumberUtil.DEFAULT_SCALE):null);
				}
			}
		}
		return list;
	}
	@Override
	public List<SySummary> selectSyMoShenList(BaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		List<SySummary> list=sySummaryMapper.selectSyMoShenList(searchData);
		if(list!=null&&list.size()!=0){
			for(SySummary e:list){
				if(e!=null){
					//击杀平均用时
					e.setAvgTime(NumberUtil.ajustScale(e.getBossCnt().intValue()==0?0.0:e.getTotalTime().doubleValue()/e.getBossCnt(),NumberUtil.DEFAULT_SCALE));
				}
			}
		}
		return list;
	}

}
