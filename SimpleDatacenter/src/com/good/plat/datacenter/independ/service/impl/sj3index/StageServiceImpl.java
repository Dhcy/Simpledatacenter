package com.good.plat.datacenter.independ.service.impl.sj3index;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.independ.entity.sj3index.Stage;
import com.good.plat.datacenter.independ.mapper.sj3index.StageMapper;
import com.good.plat.datacenter.independ.service.sj3index.StageService;
@Service(value="stageService")
public class StageServiceImpl implements StageService {
	@Autowired
	private StageMapper stageMapper;

	@Override
	public List<Stage> selectStageTaskList(IndependBaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<Stage> list=stageMapper.selectStageTaskList(searchData);
		if(list!=null&&list.size()>0){
			for(Stage e:list){
				if(e.getStageType()!=null){
					String stageTypeStr=getStageType(e.getStageType().intValue());
					e.setStageTypeStr(stageTypeStr);
				}
			}
		}
		
		Map<String,List<Stage>> resultMap=new LinkedHashMap<String, List<Stage>>();
		//
		if(list!=null&&!list.isEmpty()){
			for(Stage e:list){
				StringBuffer sb=new StringBuffer();
				sb.append(e.getStatdate());
				sb.append("~");
				sb.append(e.getChannelName());
				sb.append("~");
				sb.append(e.getStageType());
				sb.append("~");
				sb.append(e.getStageId());
				sb.append("~");
				sb.append(e.getStageName());
				String key=sb.toString();
				List<Stage> aList=resultMap.get(key);
				if(aList==null){
					aList=new ArrayList<Stage>();
				}
				aList.add(e);
				resultMap.put(key, aList);
			}
		}
		List<Stage> resultList=new LinkedList<Stage>();
		//汇总处理
		List<Entry<String,List<Stage>>> nList=new ArrayList<Entry<String,List<Stage>>>(resultMap.entrySet());
		if(!nList.isEmpty()){
			for(Entry<String,List<Stage>> k:nList){
				List<Stage> kList=k.getValue();
				String key=k.getKey();
				//
				if(!kList.isEmpty()){
					Stage sumData=new Stage();
					int sum_entercnt=0;
					int sum_succdcnt=0;
					int sum_failcnt=0;
					int sum_enter_uid_cnt=0;
					int sum_succ_uid_cnt=0;
					int sum_fail_uid_cnt=0;
					Double avg_succ_time=0.0;
					for(Stage stage:kList){
						sum_entercnt+=stage.getEntercnt();
						sum_succdcnt+=stage.getSuccdcnt();
						sum_failcnt+=stage.getFailcnt();
						sum_enter_uid_cnt+=stage.getEnter_uid_cnt();
						sum_succ_uid_cnt+=stage.getSucc_uid_cnt();
						sum_fail_uid_cnt+=stage.getFail_uid_cnt();
						avg_succ_time+=stage.getAvg_succ_time();
					}
					//汇总
					sumData.setEntercnt(sum_entercnt);
					sumData.setSuccdcnt(sum_succdcnt);
					sumData.setFailcnt(sum_failcnt);
					sumData.setEnter_uid_cnt(sum_enter_uid_cnt);
					sumData.setSucc_uid_cnt(sum_succ_uid_cnt);
					sumData.setFail_uid_cnt(sum_fail_uid_cnt);
					sumData.setAvg_succ_time(avg_succ_time);
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
						sumData.setStatType(key.split("~")[2]);
					}else{
						sumData.setStatType("未知");
					}
					if(key.split("~")[3]!=null){
						sumData.setStageId(Integer.valueOf(key.split("~")[3]));
					}else{
						sumData.setStageId(-1);
					}
					if(key.split("~")[4]!=null){
						sumData.setStageName(key.split("~")[4]);
					}else{
						sumData.setStageName("未知");
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
	
	private String getStageType(int type){
		String stageType=null;
		switch (type) {
		case 1:
			stageType="关卡任务";
			break;
		case 2:
			stageType="非关卡任务";
			break;
		default:
			break;
		}
		return stageType;
	}

	@Override
	public List<Stage> selectStageList(IndependBaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=(IndependBaseSearchData) searchData.searchDataFilter(searchData);
		List<Stage> stageList=stageMapper.selectStageList(searchData);
		Map<String,List<Stage>> resultMap=new LinkedHashMap<String, List<Stage>>();
		//按日期，渠道，关卡分组
		if(stageList!=null&&!stageList.isEmpty()){
			for(Stage stage:stageList){
				Integer avgSuccTime=stage.getSucctimes()==0?0:Math.round(stage.getSumtime()/stage.getSucctimes());
				stage.setAvg_succ_time(avgSuccTime.doubleValue());
				StringBuffer sb=new StringBuffer();
				sb.append(stage.getStatdate());
				sb.append("~");
				sb.append(stage.getChannelName());
				sb.append("~");
				sb.append(stage.getStageId());
				sb.append("~");
				sb.append(stage.getStageName());
				String key=sb.toString();
				List<Stage> aList=resultMap.get(key);
				if(aList==null){
					aList=new ArrayList<Stage>();
				}
				aList.add(stage);
				resultMap.put(key, aList);
			}
		}
		
		List<Stage> resultList=new LinkedList<Stage>();
		//汇总关卡下所有的职业处理
		List<Entry<String,List<Stage>>> nList=new ArrayList<Entry<String,List<Stage>>>(resultMap.entrySet());
		if(!nList.isEmpty()){
			for(Entry<String,List<Stage>> k:nList){
				List<Stage> kList=k.getValue();
				String key=k.getKey();
				//
				if(!kList.isEmpty()){
					Stage sumData=new Stage();
					int sum_opencnt=0;
					int sum_entercnt=0;
					int sum_succdcnt=0;
					int sum_failcnt=0;
					int sum_exitcnt=0;
					int sum_open_uid_cnt=0;
					int sum_enter_uid_cnt=0;
					int sum_succ_uid_cnt=0;
					int sum_fail_uid_cnt=0;
					Double avg_succ_time=0.0;
					//汇总所有职业包括jobs=-1
					for(Stage stage:kList){
						sum_opencnt+=stage.getOpencnt();
						sum_entercnt+=stage.getEntercnt();
						sum_succdcnt+=stage.getSuccdcnt();
						sum_failcnt+=stage.getFailcnt();
						sum_exitcnt+=stage.getExitcnt();
						sum_open_uid_cnt+=stage.getOpenuidcnt();
						sum_enter_uid_cnt+=stage.getEnteruidcnt();
						sum_succ_uid_cnt+=stage.getSuccduidcnt();
						sum_fail_uid_cnt+=stage.getFailuidcnt();
						avg_succ_time+=stage.getAvg_succ_time();
					}
					//汇总
					sumData.setOpencnt(sum_opencnt);
					sumData.setEntercnt(sum_entercnt);
					sumData.setSuccdcnt(sum_succdcnt);
					sumData.setFailcnt(sum_failcnt);
					sumData.setExitcnt(sum_exitcnt);
					sumData.setOpenuidcnt(sum_open_uid_cnt);
					sumData.setEnteruidcnt(sum_enter_uid_cnt);
					sumData.setSuccduidcnt(sum_succ_uid_cnt);
					sumData.setFailuidcnt(sum_fail_uid_cnt);
					sumData.setAvg_succ_time(avg_succ_time);
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
						sumData.setStageId(Integer.valueOf(key.split("~")[2]));
					}else{
						sumData.setStageId(-1);
					}
					if(key.split("~")[3]!=null){
						sumData.setStageName(key.split("~")[3]);
					}else{
						sumData.setStageName("未知");
					}
					sumData.setJobs("全部");
					//添加
					kList.add(0,sumData);
				}
				
				if(!kList.isEmpty()){
					//job=-1只有账号数,不需要显示移除掉
					Iterator<Stage> it=kList.iterator();
					while(it.hasNext()){
						Stage stage=it.next();
						if(stage.getJobs().equals("-1")){
							it.remove();
						}
					}
					//添加到列表
					resultList.addAll(kList);
				}
				k.setValue(kList);
			}
		}
		return resultList;
	}

}
