package com.good.plat.datacenter.independ.service.impl.sj3index;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.DateUtil;
import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.independ.entity.sj3index.Officer;
import com.good.plat.datacenter.independ.entity.sj3index.SyMapSummary;
import com.good.plat.datacenter.independ.mapper.sj3index.SyMapSummaryMapper;
import com.good.plat.datacenter.independ.service.sj3index.SyMapSummaryService;
@Service(value="syMapSummaryService")
public class SyMapSummaryServiceImpl implements SyMapSummaryService {
	@Autowired
	private SyMapSummaryMapper syMapSummaryMapper;
	@Override
	public List<SyMapSummary> selectSyCombatList(IndependBaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData)searchData.searchDataFilter(searchData);
		List<SyMapSummary> list=syMapSummaryMapper.selectSyCombatList(searchData);
		if(list!=null&&!list.isEmpty()){
			for(SyMapSummary e:list){
				if(e.getAvgCombatTime()!=null){
					String hms=DateUtil.secondTransferToHMS(e.getAvgCombatTime().intValue());
					e.setAvgCombatTimeStr(hms);
				}
//				if(e.getStarLevel().equals("全民圣域")){
//					e.setStar(0);
//				}else if(e.getStarLevel().equals("一星圣域")){
//					e.setStar(1);
//				}else if(e.getStarLevel().equals("二星圣域")){
//					e.setStar(2);
//				}else if(e.getStarLevel().equals("三星圣域")){
//					e.setStar(3);
//				}else if(e.getStarLevel().equals("四星圣域")){
//					e.setStar(4);
//				}else{
//					e.setStar(0);
//				}
			}
//			 Collections.sort(list, new Comparator<SyMapSummary>() {
//		            public int compare(SyMapSummary e1, SyMapSummary e2) {
//		            	Long starttime=DateUtil.parse_yyyy_MM_dd(e1.getStatdate()).getTime();
//		            	Long endTime=DateUtil.parse_yyyy_MM_dd(e2.getStatdate()).getTime();
//		                int a =starttime.intValue()-endTime.intValue();
//		                if (a != 0) {
//		                    return a > 0 ? 1 : -1;
//		                }
//		                a = e1.getAreaid() - e2.getAreaid();
//		                if (a != 0) {
//		                    return a > 0 ? 1: -1;
//		                }
//		                a = e1.getStar() - e2.getStar();
//		                return a > 0 ? 1 : -1;
//		            }
//		        });
		}
		
//		Map<String,List<SyMapSummary>> resultMap=new LinkedHashMap<String, List<SyMapSummary>>();
//		//
//		if(list!=null&&!list.isEmpty()){
//			for(SyMapSummary e:list){
//				StringBuffer sb=new StringBuffer();
//				sb.append(e.getStatdate());
//				sb.append("~");
//				sb.append(e.getAreaid());
//				sb.append("~");
//				sb.append(e.getStarLevel());
//				String key=sb.toString();
//				List<SyMapSummary> aList=resultMap.get(key);
//				if(aList==null){
//					aList=new ArrayList<SyMapSummary>();
//				}
//				aList.add(e);
//				resultMap.put(key, aList);
//			}
//		}
//		List<SyMapSummary> resultList=new LinkedList<SyMapSummary>();
//		//汇总星级为全部处理
//		List<Entry<String,List<SyMapSummary>>> nList=new ArrayList<Entry<String,List<SyMapSummary>>>(resultMap.entrySet());
//		if(!nList.isEmpty()){
//			for(Entry<String,List<SyMapSummary>> k:nList){
//				List<SyMapSummary> kList=k.getValue();
//				String key=k.getKey();
//				//
//				if(!kList.isEmpty()){
//					SyMapSummary sumData=new SyMapSummary();
//					int sum_openCombatCnt=0;
//					int sum_avgCombatTime=0;
//					int sum_actjoincnt=0;
//					int sum_actlootcnt=0;
//					int sum_lootcnt=0;
//					int sum_actleavecnt=0;
//					int sum_actfailcnt=0;
//					int sum_actexitcnt=0;
//					int sum_islandcnt=0;
//					for(SyMapSummary syMap:kList){
//						sum_openCombatCnt+=syMap.getOpenCombatCnt();
//						sum_avgCombatTime+=syMap.getAvgCombatTime();
//						sum_actjoincnt+=syMap.getActjoincnt();
//						sum_actlootcnt+=syMap.getActlootcnt();
//						sum_lootcnt+=syMap.getLootcnt();
//						sum_actleavecnt+=syMap.getActleavecnt();
//						sum_actfailcnt+=syMap.getActfailcnt();
//						sum_actexitcnt+=syMap.getActexitcnt();
//						sum_islandcnt+=syMap.getIslandcnt();
//					}
//					//汇总
//					sumData.setOpenCombatCnt(sum_openCombatCnt);
//					sumData.setAvgCombatTime(sum_avgCombatTime);
//					sumData.setActjoincnt(sum_actjoincnt);
//					sumData.setActlootcnt(sum_actlootcnt);
//					sumData.setLootcnt(sum_lootcnt);
//					sumData.setActleavecnt(sum_actleavecnt);
//					sumData.setActfailcnt(sum_actfailcnt);
//					sumData.setActexitcnt(sum_actexitcnt);
//					sumData.setIslandcnt(sum_islandcnt);
//					//
//					if(key.split("~")[0]!=null){
//						sumData.setStatdate(key.split("~")[0]);
//					}else{
//						sumData.setStatdate("未知");
//					}
//					if(key.split("~")[1]!=null){
//						sumData.setAreaid(Integer.valueOf(key.split("~")[1]));
//					}else{
//						sumData.setAreaid(-1);
//					}
//					if(key.split("~")[2]!=null){
//						sumData.setStarLevel(key.split("~")[2]);
//					}else{
//						sumData.setStarLevel("未知");
//					}
//					sumData.setJobs("全部");
//					//添加
//					kList.add(0,sumData);
//				}
//				//添加到列表
//				if(!kList.isEmpty()){
//					resultList.addAll(kList);
//				}
//				k.setValue(kList);
//			}
//		}
//		return resultList;
		return list;
	}
}
