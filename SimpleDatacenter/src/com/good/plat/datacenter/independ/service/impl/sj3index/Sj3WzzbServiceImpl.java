package com.good.plat.datacenter.independ.service.impl.sj3index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.independ.entity.sj3index.Sj3Wzzb;
import com.good.plat.datacenter.independ.mapper.sj3index.Sj3WzzbMapper;
import com.good.plat.datacenter.independ.service.sj3index.Sj3WzzbService;
@Service(value="sj3WzzbService")
public class Sj3WzzbServiceImpl implements Sj3WzzbService {
	@Autowired
	private Sj3WzzbMapper sj3WzzbMapper;
	@Override
	public List<Sj3Wzzb> selectMaxJoinActorDetailList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<Sj3Wzzb> list=sj3WzzbMapper.selectMaxJoinActorDetailList(searchData);
		if(list!=null&&!list.isEmpty()){
			for(int i=0;i<list.size();i++){
				Sj3Wzzb wzzb=list.get(i);
				if(wzzb!=null){
					//排名
					wzzb.setRaking(String.valueOf(i+1));
					if(wzzb.getRate()!=null){
						wzzb.setRate(NumberUtil.multi100(wzzb.getRate(), 2));
					}
				}
			}
		}
		return list;
	}

	@Override
	public List<Sj3Wzzb> selectWzzbBetLevelList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<Sj3Wzzb> list=sj3WzzbMapper.selectWzzbBetLevelList(searchData);
		Map<String,List<Sj3Wzzb>> resultMap=new LinkedHashMap<String, List<Sj3Wzzb>>();
		if(list!=null&&!list.isEmpty()){
			//用日期，区服id作为每组数组
			for(Sj3Wzzb e:list){
				String key=e.getStatdate()+"~"+e.getAreaid()+"~"+e.getLevel();
				List<Sj3Wzzb> wzzbList=resultMap.get(key);
				if(wzzbList==null){
					wzzbList=new ArrayList<Sj3Wzzb>();
				}
				wzzbList.add(e);
				resultMap.put(key, wzzbList);
			}
		}
		List<Sj3Wzzb> resultList=new LinkedList<Sj3Wzzb>();
		//汇总处理
		List<Entry<String,List<Sj3Wzzb>>> nList=new ArrayList<Entry<String,List<Sj3Wzzb>>>(resultMap.entrySet());
		if(!nList.isEmpty()){
			for(Entry<String,List<Sj3Wzzb>> k:nList){
				List<Sj3Wzzb> kList=k.getValue();
				String key=k.getKey();
				//
				if(!kList.isEmpty()){
					Sj3Wzzb  sumData=new Sj3Wzzb();
					int sum_groupBetCnt=0;
					int sum_groupBetTimes=0;
					int sum_finalsBetCnt=0;
					int sum_finalsBetTimes=0;
					for(Sj3Wzzb wzzb:kList){
						sum_groupBetCnt+=wzzb.getGroupBetCnt();
						sum_groupBetTimes+=wzzb.getGroupBetTimes();
						sum_finalsBetCnt+=wzzb.getFinalsBetCnt();
						sum_finalsBetTimes+=wzzb.getFinalsBetTimes();
					}
					//汇总
					sumData.setGroupBetCnt(sum_groupBetCnt);
					sumData.setGroupBetTimes(sum_groupBetTimes);
					sumData.setFinalsBetCnt(sum_finalsBetCnt);
					sumData.setFinalsBetTimes(sum_finalsBetTimes);
					//
					if(key.split("~")[0]!=null){
						sumData.setStatdate(key.split("~")[0]);
					}else{
						sumData.setStatdate("未知");
					}
					if(key.split("~")[1]!=null){
						sumData.setAreaid(Integer.valueOf(key.split("~")[1]));
					}else{
						sumData.setAreaid(-1);
					}
					if(key.split("~")[2]!=null){
						sumData.setLevel(Integer.valueOf(key.split("~")[2]));
					}else{
						sumData.setLevel(1);
					}
					sumData.setJobs("全部");
					//添加
					kList.add(0,sumData);
				}
				//添加到列表
				if(!kList.isEmpty()){
					resultList.addAll(kList);
				}
				k.setValue(kList);
			}
		}
		//
		return resultList;
	}

	@Override
	public List<Sj3Wzzb> selectWzzbBetList(IndependBaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<Sj3Wzzb> list=sj3WzzbMapper.selectWzzbBetList(searchData);
		Map<String,List<Sj3Wzzb>> resultMap=new LinkedHashMap<String, List<Sj3Wzzb>>();
		if(list!=null&&!list.isEmpty()){
			//用日期，区服id作为每组数组
			for(Sj3Wzzb e:list){
				String key=e.getStatdate()+"~"+e.getAreaid();
				List<Sj3Wzzb> wzzbList=resultMap.get(key);
				if(wzzbList==null){
					wzzbList=new ArrayList<Sj3Wzzb>();
				}
				wzzbList.add(e);
				resultMap.put(key, wzzbList);
			}
		}
		List<Sj3Wzzb> resultList=new LinkedList<Sj3Wzzb>();
		//汇总处理
		List<Entry<String,List<Sj3Wzzb>>> nList=new ArrayList<Entry<String,List<Sj3Wzzb>>>(resultMap.entrySet());
		if(!nList.isEmpty()){
			for(Entry<String,List<Sj3Wzzb>> k:nList){
				List<Sj3Wzzb> kList=k.getValue();
				String key=k.getKey();
				//
				if(!kList.isEmpty()){
					Sj3Wzzb  sumData=new Sj3Wzzb();
					int sum_betCnt=0;
					int sum_betTimes=0;
					int sum_betCost=0;
					int sum_betGain=0;
					for(Sj3Wzzb wzzb:kList){
						sum_betCnt+=wzzb.getBetCnt();
						sum_betTimes+=wzzb.getBetTimes();
						sum_betCost+=wzzb.getBetCost();
						sum_betGain+=wzzb.getBetGain();
					}
					//汇总
					sumData.setBetCnt(sum_betCnt);
					sumData.setBetTimes(sum_betTimes);
					sumData.setBetCost(sum_betCost);
					sumData.setBetGain(sum_betGain);
					//
					if(key.split("~")[0]!=null){
						sumData.setStatdate(key.split("~")[0]);
					}else{
						sumData.setStatdate("未知");
					}
					if(key.split("~")[1]!=null){
						sumData.setAreaid(Integer.valueOf(key.split("~")[1]));
					}else{
						sumData.setAreaid(-1);
					}
					sumData.setJobs("全部");
					//添加
					kList.add(0,sumData);
				}
				//添加到列表
				if(!kList.isEmpty()){
					resultList.addAll(kList);
				}
				k.setValue(kList);
			}
		}
		//
		return resultList;
	}

	@Override
	public List<Sj3Wzzb> selectWzzbEquipDetailList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<Sj3Wzzb> list=sj3WzzbMapper.selectWzzbEquipDetailList(searchData);
		return list;
	}

	@Override
	public List<Sj3Wzzb> selectWzzbList(IndependBaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<Sj3Wzzb> list=sj3WzzbMapper.selectWzzbList(searchData);
		Map<String,List<Sj3Wzzb>> resultMap=new LinkedHashMap<String, List<Sj3Wzzb>>();
		if(list!=null&&!list.isEmpty()){
			//用日期，区服id作为每组数组
			for(Sj3Wzzb e:list){
				String key=e.getStatdate()+"~"+e.getAreaid();
				List<Sj3Wzzb> wzzbList=resultMap.get(key);
				if(wzzbList==null){
					wzzbList=new ArrayList<Sj3Wzzb>();
				}
				wzzbList.add(e);
				resultMap.put(key, wzzbList);
			}
		}
		List<Sj3Wzzb> resultList=new LinkedList<Sj3Wzzb>();
		//汇总处理
		List<Entry<String,List<Sj3Wzzb>>> nList=new ArrayList<Entry<String,List<Sj3Wzzb>>>(resultMap.entrySet());
		if(!nList.isEmpty()){
			for(Entry<String,List<Sj3Wzzb>> k:nList){
				List<Sj3Wzzb> kList=k.getValue();
				String key=k.getKey();
				//
				if(!kList.isEmpty()){
					Sj3Wzzb  sumData=new Sj3Wzzb();
					int sum_joinCnt=0;
					int sum_trainCnt=0;
					int sum_jnCnt=0;
					int sum_mwCnt=0;
					int sum_lhCnt=0;
					int sum_watchCnt=0;
					int sum_watchTimes=0;
					for(Sj3Wzzb wzzb:kList){
						sum_joinCnt+=wzzb.getJoinCnt();
						sum_trainCnt+=wzzb.getTrainCnt();
						sum_jnCnt+=wzzb.getJnCnt();
						sum_mwCnt+=wzzb.getMwCnt();
						sum_lhCnt+=wzzb.getLhCnt();
						sum_watchCnt+=wzzb.getWatchCnt();
						sum_watchTimes+=wzzb.getWatchTimes();
					}
					//汇总
					sumData.setJoinCnt(sum_joinCnt);
					sumData.setTrainCnt(sum_trainCnt);
					sumData.setJnCnt(sum_jnCnt);
					sumData.setMwCnt(sum_mwCnt);
					sumData.setLhCnt(sum_lhCnt);
					sumData.setWatchCnt(sum_watchCnt);
					sumData.setWatchTimes(sum_watchTimes);
					//
					if(key.split("~")[0]!=null){
						sumData.setStatdate(key.split("~")[0]);
					}else{
						sumData.setStatdate("未知");
					}
					if(key.split("~")[1]!=null){
						sumData.setAreaid(Integer.valueOf(key.split("~")[1]));
					}else{
						sumData.setAreaid(-1);
					}
					sumData.setJobs("全部");
					//添加
					kList.add(0,sumData);
				}
				//添加到列表
				if(!kList.isEmpty()){
					resultList.addAll(kList);
				}
				k.setValue(kList);
			}
		}
		//
		return resultList;
	}

	@Override
	public List<Sj3Wzzb> selectWzzbLsList(IndependBaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<Sj3Wzzb> list=sj3WzzbMapper.selectWzzbLsList(searchData);
		if(list!=null&&!list.isEmpty()){
			for(int i=0;i<list.size();i++){
				Sj3Wzzb wzzb=list.get(i);
				if(wzzb!=null){
					//排名
					wzzb.setRaking(String.valueOf(i+1));
				}
			}
		}
		return list;
	}

}
