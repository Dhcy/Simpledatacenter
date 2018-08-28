package com.good.plat.datacenter.independ.service.impl.sj3index;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.DateUtil;
import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.independ.entity.sj3index.SyMapSummary;
import com.good.plat.datacenter.independ.entity.sj3index.ZcqyCombat;
import com.good.plat.datacenter.independ.mapper.sj3index.ZcqyCombatMapper;
import com.good.plat.datacenter.independ.service.sj3index.ZcqyCombatService;
@Service(value="zcqyCombatService")
public class ZcqyCombatServiceImpl implements ZcqyCombatService{
	@Autowired
	private ZcqyCombatMapper zcqyCombatMapper;
	@Override
	public List<ZcqyCombat> selectPlayTypeCombatDetailList(
			IndependBaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<ZcqyCombat> list=zcqyCombatMapper.selectPlayTypeCombatDetailList(searchData);
		if(list!=null&&list.size()>0){
			for(ZcqyCombat e:list){
				if(e.getAvgCombatTime()!=null){
					e.setAvgCombatTimeStr(DateUtil.secondTransferToHMS(e.getAvgCombatTime().intValue()));
				}
			}
		}
		return list;
	}

	@Override
	public List<ZcqyCombat> selectZcCombatList(IndependBaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<ZcqyCombat> list=zcqyCombatMapper.selectZcCombatList(searchData);
		if(list!=null&&list.size()>0){
			for(ZcqyCombat e:list){
				if(e.getAvgCombatTime()!=null){
					e.setAvgCombatTimeStr(DateUtil.secondTransferToHMS(e.getAvgCombatTime().intValue()));
				}
			}
		}
		
		Map<String,List<ZcqyCombat>> resultMap=new LinkedHashMap<String, List<ZcqyCombat>>();
		//
		if(list!=null&&!list.isEmpty()){
			for(ZcqyCombat e:list){
				StringBuffer sb=new StringBuffer();
				sb.append(e.getStatdate());
				sb.append("~");
				sb.append(e.getAreaName());
				sb.append("~");
				sb.append(e.getCountry());
				sb.append("~");
				sb.append(e.getChannelName());
				String key=sb.toString();
				List<ZcqyCombat> aList=resultMap.get(key);
				if(aList==null){
					aList=new ArrayList<ZcqyCombat>();
				}
				aList.add(e);
				resultMap.put(key, aList);
			}
		}
		List<ZcqyCombat> resultList=new LinkedList<ZcqyCombat>();
		//汇总处理
		List<Entry<String,List<ZcqyCombat>>> nList=new ArrayList<Entry<String,List<ZcqyCombat>>>(resultMap.entrySet());
		if(!nList.isEmpty()){
			for(Entry<String,List<ZcqyCombat>> k:nList){
				List<ZcqyCombat> kList=k.getValue();
				String key=k.getKey();
				//
				if(!kList.isEmpty()){
					ZcqyCombat sumData=new ZcqyCombat();
					int sum_openCombatCnt=0;
					int sum_avgCombatTime=0;
					int sum_gamerCnt=0;
					int sum_successCnt=0;
					int sum_failCnt=0;
					for(ZcqyCombat zcqy:kList){
						sum_openCombatCnt+=zcqy.getOpenCombatCnt();
						sum_avgCombatTime+=zcqy.getAvgCombatTime();
						sum_gamerCnt+=zcqy.getGamerCnt();
						sum_successCnt+=zcqy.getSuccessCnt();
						sum_failCnt+=zcqy.getFailCnt();
						sum_failCnt+=zcqy.getFailCnt();
					}
					//汇总
					sumData.setOpenCombatCnt(sum_openCombatCnt);
					sumData.setAvgCombatTime(sum_avgCombatTime);
					sumData.setGamerCnt(sum_gamerCnt);
					sumData.setSuccessCnt(sum_successCnt);
					sumData.setFailCnt(sum_failCnt);
					//
					if(key.split("~")[0]!=null){
						sumData.setStatdate(key.split("~")[0]);
					}else{
						sumData.setStatdate("未知");
					}
					if(key.split("~")[1]!=null){
						sumData.setAreaName(key.split("~")[1]);
					}else{
						sumData.setAreaName("未知");
					}
					if(key.split("~")[2]!=null){
						sumData.setCountry(key.split("~")[2]);
					}else{
						sumData.setCountry("未知");
					}
					if(key.split("~")[3]!=null){
						sumData.setChannelName(key.split("~")[3]);
					}else{
						sumData.setChannelName("未知");
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
		return resultList;
	}

}
