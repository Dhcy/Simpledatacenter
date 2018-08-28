package com.good.plat.datacenter.independ.service.impl.yhindex;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.independ.entity.yhindex.YhStageStat;
import com.good.plat.datacenter.independ.mapper.yhindex.YhStageStatMapper;
import com.good.plat.datacenter.independ.service.yhindex.YhStageStatService;
@Service(value="yhStageStatService")
public class YhStageStatServiceImpl implements YhStageStatService {
	@Autowired
	private YhStageStatMapper yhStageStatMapper;
	@Override
	public List<YhStageStat> selectYhStageList(IndependBaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<YhStageStat> stageList=yhStageStatMapper.selectYhStageList(searchData);
		if(stageList!=null&&!stageList.isEmpty()){
			for(YhStageStat e:stageList){
				if(e.getStageid().intValue()==1){
					e.setFitActUser(e.getRegUser());
				}
				e.setSuccActUserRate(e.getSuccActUserRate()==null?0.0:NumberUtil.multi100(e.getSuccActUserRate(), 2));
				e.setSuccActUserTotalRate(e.getSuccActUserTotalRate()==null?0.0:NumberUtil.multi100(e.getSuccActUserTotalRate(), 2));
			}
		}
		return stageList;
	}
	@Override
	public List<YhStageStat> selectYhStageDataList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<YhStageStat> stageDataList=yhStageStatMapper.selectYhStageDataList(searchData);
		return stageDataList;
	}
	@Override
	public List<YhStageStat> selectYhStageStarNumList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<YhStageStat> list=yhStageStatMapper.selectYhStageStarNumList(searchData);
		if(list!=null&&!list.isEmpty()){
			for(YhStageStat e:list){
				Double onestarRate=e.getSuccessActUser()==0?0.0:e.getOnestarcnt().doubleValue()/e.getSuccessActUser();
				Double twostarRate=e.getSuccessActUser()==0?0.0:e.getTwostarcnt().doubleValue()/e.getSuccessActUser();
				Double threestarRate=e.getSuccessActUser()==0?0.0:e.getThreestarcnt().doubleValue()/e.getSuccessActUser();
				e.setOnestarcntRate(NumberUtil.multi100(onestarRate, 2));
				e.setTwostarcntRate(NumberUtil.multi100(twostarRate, 2));
				e.setThreestarcntRate(NumberUtil.multi100(threestarRate, 2));
			}
		}
		return list;
	}
	@Override
	public List<YhStageStat> selectActingStageActUserNumList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<YhStageStat> list=yhStageStatMapper.selectActingStageActUserNumList(searchData);
		return list;
	}
	@Override
	public List<YhStageStat> selectActingStageCountList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<YhStageStat> resultList=new LinkedList<YhStageStat>();
		List<YhStageStat> list=yhStageStatMapper.selectActingStageCountList(searchData);
		if(!list.isEmpty()){
			for(YhStageStat stat:list){
				//挑战次数为0，不显示
				if(stat.getCount().intValue()!=0){
					resultList.add(stat);
				}
			}
		}
		return resultList;
	}
	@Override
	public List<YhStageStat> selectUnitStageActUserNumList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<YhStageStat> list=yhStageStatMapper.selectUnitStageActUserNumList(searchData);
		return list;
	}
	@Override
	public List<YhStageStat> selectUnitStageCountList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<YhStageStat> list=yhStageStatMapper.selectUnitStageCountList(searchData);
		if(!list.isEmpty()){
			for(YhStageStat e:list){
				if(e.getJtCnt()==null){
					e.setJtCnt(0);
					}
				if(e.getFriendCnt()==null){
					e.setFriendCnt(0);
				}
				if(e.getRandomCnt()==null){
					e.setRandomCnt(0);
				}
				Integer totalHelpCnt=e.getJtCnt()+e.getFriendCnt()+e.getRandomCnt();//进入关卡总的助战次数
				if(totalHelpCnt==0){
					e.setJtRate(0.0);
					e.setFriendRate(0.0);
					e.setRandomRate(0.0);
				}else{
					//军团助战占比
					Double jtRate=NumberUtil.div(e.getJtCnt().doubleValue(), totalHelpCnt.doubleValue(), 4);
					//好友助战占比
					Double friendsRate=NumberUtil.div(e.getFriendCnt().doubleValue(), totalHelpCnt.doubleValue(), 4);
					//随机玩家助战占比
					Double randomRate=NumberUtil.div(e.getRandomCnt().doubleValue(), totalHelpCnt.doubleValue(), 4);
					e.setJtRate(NumberUtil.multi100(jtRate, 2));
					e.setJtRate(NumberUtil.multi100(friendsRate, 2));
					e.setJtRate(NumberUtil.multi100(randomRate, 2));
				}
			}
		}
		return list;
	}
	@Override
	public List<YhStageStat> selectYhStageDataDisList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<YhStageStat> list=yhStageStatMapper.selectYhStageDataDisList(searchData);
		return list;
	}
	@Override
	public List<YhStageStat> selectYhStageDisList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<YhStageStat> list=yhStageStatMapper.selectYhStageDisList(searchData);
		return list;
	}
	@Override
	public List<YhStageStat> selectYhStageStarNumDisList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<YhStageStat> list=yhStageStatMapper.selectYhStageStarNumDisList(searchData);
		return list;
	}

}
