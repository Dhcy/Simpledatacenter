package com.good.plat.datacenter.independ.service.impl.sj3index;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.independ.entity.sj3index.Achieve;
import com.good.plat.datacenter.independ.entity.sj3index.Sj3Wzzb;
import com.good.plat.datacenter.independ.mapper.sj3index.AchieveMapper;
import com.good.plat.datacenter.independ.service.sj3index.AchieveService;
@Service(value="achieveService")
public class AchieveServiceImpl implements AchieveService {
	@Autowired
	private AchieveMapper achieveMapper;
	@Override
	public List<Achieve> selectAchieveFinishNumList(BaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		List<Achieve> achieveList=new ArrayList<Achieve>();
		achieveList=achieveMapper.selectAchieveFinishNumList(searchData);
		if(achieveList!=null&&achieveList.size()!=0){
			for(Achieve e:achieveList){
				if(e!=null){
					e.setFinish_rate(NumberUtil.multi100(e.getFinish_rate()==null?0.0:e.getFinish_rate(), NumberUtil.DEFAULT_SCALE));
				}
			}
		}
		Map<String,List<Achieve>> resultMap=new LinkedHashMap<String, List<Achieve>>();
		//
		if(achieveList!=null&&!achieveList.isEmpty()){
			for(Achieve e:achieveList){
				StringBuffer sb=new StringBuffer();
				sb.append(e.getStatdate());
				sb.append("~");
				sb.append(e.getChannelName());
				sb.append("~");
				sb.append(e.getAchieveId());
				sb.append("~");
				sb.append(e.getAchieveName());
				String key=sb.toString();
				List<Achieve> aList=resultMap.get(key);
				if(aList==null){
					aList=new ArrayList<Achieve>();
				}
				aList.add(e);
				resultMap.put(key, aList);
			}
		}
		List<Achieve> resultList=new LinkedList<Achieve>();
		//汇总处理
		List<Entry<String,List<Achieve>>> nList=new ArrayList<Entry<String,List<Achieve>>>(resultMap.entrySet());
		if(!nList.isEmpty()){
			for(Entry<String,List<Achieve>> k:nList){
				List<Achieve> kList=k.getValue();
				String key=k.getKey();
				//
				if(!kList.isEmpty()){
					Achieve sumData=new Achieve();
					int sum_usercnt=0;
					Double sum_finish_rate=0.0;
					for(Achieve achieve:kList){
						sum_usercnt+=achieve.getUsercnt();
						sum_finish_rate+=achieve.getFinish_rate();
					}
					//汇总
					sumData.setUsercnt(sum_usercnt);
					sumData.setFinish_rate(sum_finish_rate);
					//
					if(key.split("~")[0]!=null){
						sumData.setStatdate(key.split("~")[0]);
					}else{
						sumData.setStatdate("未知");
					}
					if(key.split("~")[1]!=null){
						sumData.setChannelName(key.split("~")[1]);
					}else{
						sumData.setChannelName("未知");
					}
					if(key.split("~")[2]!=null){
						sumData.setAchieveId(Integer.valueOf(key.split("~")[2]));
					}else{
						sumData.setAchieveId(-1);
					}
					if(key.split("~")[3]!=null){
						sumData.setAchieveName(key.split("~")[3]);
					}else{
						sumData.setAchieveName("未知");
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
	@Override
	public List<Achieve> selectAchieveFinishTotalNumList(
			BaseSearchData searchData) throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		List<Achieve> totalList=new ArrayList<Achieve>();
		totalList=achieveMapper.selectAchieveFinishTotalNumList(searchData);
		
		if(totalList!=null&&totalList.size()!=0){
			for(Achieve e:totalList){
				if(e!=null){
					e.setFinish_rate(NumberUtil.multi100(e.getFinish_rate()==null?0.0:e.getFinish_rate(), NumberUtil.DEFAULT_SCALE));
				}
			}
		}
		Map<String,List<Achieve>> resultMap=new LinkedHashMap<String, List<Achieve>>();
		//
		if(totalList!=null&&!totalList.isEmpty()){
			for(Achieve e:totalList){
				StringBuffer sb=new StringBuffer();
				sb.append(e.getStatdate());
				sb.append("~");
				sb.append(e.getChannelName());
				sb.append("~");
				String key=sb.toString();
				List<Achieve> aList=resultMap.get(key);
				if(aList==null){
					aList=new ArrayList<Achieve>();
				}
				aList.add(e);
				resultMap.put(key, aList);
			}
		}
		List<Achieve> resultList=new LinkedList<Achieve>();
		//汇总处理
		List<Entry<String,List<Achieve>>> nList=new ArrayList<Entry<String,List<Achieve>>>(resultMap.entrySet());
		if(!nList.isEmpty()){
			for(Entry<String,List<Achieve>> k:nList){
				List<Achieve> kList=k.getValue();
				String key=k.getKey();
				//
				if(!kList.isEmpty()){
					Achieve sumData=new Achieve();
					int sum_total_cnt=0;
					Double sum_avg_achieve_cnt=0.0;
					for(Achieve achieve:kList){
						sum_total_cnt+=achieve.getTotal_cnt();
						sum_avg_achieve_cnt+=achieve.getAvg_achieve_cnt();
					}
					//汇总
					sumData.setTotal_cnt(sum_total_cnt);
					sumData.setAvg_achieve_cnt(sum_avg_achieve_cnt);
					//
					if(key.split("~")[0]!=null){
						sumData.setStatdate(key.split("~")[0]);
					}else{
						sumData.setStatdate("未知");
					}
					if(key.split("~")[1]!=null){
						sumData.setChannelName(key.split("~")[1]);
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
