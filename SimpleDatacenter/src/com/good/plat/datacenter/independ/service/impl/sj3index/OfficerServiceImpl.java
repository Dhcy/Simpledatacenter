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
import com.good.plat.datacenter.independ.entity.sj3index.Officer;
import com.good.plat.datacenter.independ.entity.sj3index.Sj3Wzzb;
import com.good.plat.datacenter.independ.entity.sj3index.Stage;
import com.good.plat.datacenter.independ.mapper.sj3index.OfficerMapper;
import com.good.plat.datacenter.independ.service.sj3index.OfficerService;
@Service(value="officerService")
public class OfficerServiceImpl implements OfficerService {
	@Autowired
private OfficerMapper officerMapper;
	@Override
	public List<Officer> selectOfficerDistributionList(BaseSearchData searchData)
			throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		List<Officer> list=officerMapper.selectOfficerDistributionList(searchData);
		if(list!=null){
			for(Officer e:list){
				if(e!=null){
					if(e.getLmshCnt()==null){
						e.setLmshCnt(0);
					}
					if(e.getYmzyCnt()==null){
						e.setYmzyCnt(0);
					}
					if(e.getYymgCnt()==null){
						e.setYymgCnt(0);
					}
					if(e.getTotalCnt()==null){
						e.setTotalCnt(0);
					}
					e.setLmshRate(e.getTotalCnt()==0?0.0:NumberUtil.multi100(e.getLmshCnt().doubleValue()/ e.getTotalCnt(), NumberUtil.DEFAULT_SCALE));
					e.setYmzyRate(e.getTotalCnt()==0?0.0:NumberUtil.multi100(e.getYmzyCnt().doubleValue()/e.getTotalCnt(), NumberUtil.DEFAULT_SCALE));
					e.setYymgRate(e.getTotalCnt()==0?0.0:NumberUtil.multi100(e.getYymgCnt().doubleValue()/e.getTotalCnt(), NumberUtil.DEFAULT_SCALE));
				}
			}
		}
		Map<String,List<Officer>> resultMap=new LinkedHashMap<String, List<Officer>>();
		//
		if(list!=null&&!list.isEmpty()){
			for(Officer e:list){
				StringBuffer sb=new StringBuffer();
				sb.append(e.getStatdate());
				sb.append("~");
				sb.append(e.getChannelName());
				sb.append("~");
				sb.append(e.getOfficerName());
				String key=sb.toString();
				List<Officer> aList=resultMap.get(key);
				if(aList==null){
					aList=new ArrayList<Officer>();
				}
				aList.add(e);
				resultMap.put(key, aList);
			}
		}
		List<Officer> resultList=new LinkedList<Officer>();
		//汇总处理
		List<Entry<String,List<Officer>>> nList=new ArrayList<Entry<String,List<Officer>>>(resultMap.entrySet());
		if(!nList.isEmpty()){
			for(Entry<String,List<Officer>> k:nList){
				List<Officer> kList=k.getValue();
				String key=k.getKey();
				//
				if(!kList.isEmpty()){
					Officer sumData=new Officer();
					int sum_lmshCnt=0;
					int sum_ymzyCnt=0;
					int sum_yymgCnt=0;
					double sum_lmshRate=0.0;
					double sum_ymzyRate=0.0;
					double sum_yymgRatet=0.0;
					for(Officer officer:kList){
						sum_lmshCnt+=officer.getLmshCnt();
						sum_ymzyCnt+=officer.getYmzyCnt();
						sum_yymgCnt+=officer.getYymgCnt();
						sum_lmshRate+=officer.getLmshRate();
						sum_ymzyRate+=officer.getYmzyRate();
						sum_yymgRatet+=officer.getYymgRate();
					}
					//汇总
					sumData.setLmshCnt(sum_lmshCnt);
					sumData.setYmzyCnt(sum_ymzyCnt);
					sumData.setYymgCnt(sum_yymgCnt);
					sumData.setLmshRate(sum_lmshRate);
					sumData.setYmzyRate(sum_ymzyRate);
					sumData.setYymgRate(sum_yymgRatet);
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
						sumData.setOfficerName(key.split("~")[2]);
					}else{
						sumData.setOfficerName("未知");
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
