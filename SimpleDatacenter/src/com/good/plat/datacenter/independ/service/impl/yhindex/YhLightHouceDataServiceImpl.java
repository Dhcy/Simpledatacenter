package com.good.plat.datacenter.independ.service.impl.yhindex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.codehaus.jackson.map.util.Comparators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.common.util.NumberUtil;
import com.good.plat.datacenter.datastat.controller.balanceplat.BalancePlatController.ChannelIncomeEntity;
import com.good.plat.datacenter.independ.entity.yhindex.YhLightHouceData;
import com.good.plat.datacenter.independ.entity.yhindex.YhLoginStat;
import com.good.plat.datacenter.independ.mapper.yhindex.YhLightHouceDataMapper;
import com.good.plat.datacenter.independ.service.yhindex.YhLightHouceDataService;
@Service(value="yhLightHouceDataService")
public class YhLightHouceDataServiceImpl implements YhLightHouceDataService {
	@Autowired
	private YhLightHouceDataMapper yhLightHouceDataMapper;
	/* (非 Javadoc) 
	* <p>Title: selectPerLinkCntForHour</p> 
	* <p>Description: </p> 
	* @param searchData
	* @return
	* @throws Exception 
	* @see com.good.plat.datacenter.independ.service.yhindex.YhLightHouceDataService#selectPerLinkCntForHour(com.good.plat.datacenter.common.base.BaseSearchData) 
	*/ 
	@Override
	public List<YhLightHouceData> selectPerLinkCntForHour(BaseSearchData searchData)throws Exception {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		List<YhLightHouceData> resultList=yhLightHouceDataMapper.selectPerLinkCntForHour(searchData);
		List<Integer> hasHourList=new LinkedList<Integer>();//库表有的时间点
		List<YhLightHouceData> returnList=new ArrayList<YhLightHouceData>();
		//
		Map<Integer,List<YhLightHouceData>> data_map=new HashMap<Integer,List<YhLightHouceData>>();
		if(resultList!=null&&!resultList.isEmpty()){
			for(int i=0;i<resultList.size();i++){
				YhLightHouceData e=resultList.get(i);
				int hour=e.getHour().intValue();
				e.setHourText(swithchText(hour));
				if(e.getType()==null){
					e.setType(-1);
				}
				//除了非启动游戏环节外的失败率=（上一环节的成功次数-当天环节的成功次数)/(上一环节的成功次数)*100%
				if(e.getType().intValue()>0&&e.getType().intValue()<8){
					int index=i-1;//
					YhLightHouceData preLoginStat=resultList.get(index);//获取上一环节
					Integer totalCnt=preLoginStat.getSuccnt();//(当前环节的总次数=上一环节的成功次数)
					Integer failCnt=totalCnt-e.getSuccnt();//当前环节的失败人数
					Double failRate=totalCnt==0?0.0:failCnt.doubleValue()/totalCnt;
					e.setFailRate(NumberUtil.multi100(failRate, 2));
					System.out.println("时间点:"+e.getHourText()+"|环节类型:"+e.getType()+"|失败人数:"+failCnt+"|总次数:"+totalCnt+"|失败率:"+failRate);
				}else{
					//启动游戏环节的失败率=当前环节失败次数/当前环节总次数
					Integer failCnt=e.getCount()-e.getSuccnt();//当前环节失败人数
					Integer totalCnt=e.getCount();//当前环节总次数
					Double failRate=totalCnt==0?0.0:failCnt.doubleValue()/totalCnt;
					e.setFailRate(NumberUtil.multi100(failRate, 2));
					System.out.println("时间点:"+e.getHourText()+"|环节类型:"+e.getType()+"|失败人数:"+failCnt+"|总次数:"+totalCnt+"|失败率:"+failRate);
				}
				if(!hasHourList.contains(e.getHour())){
					hasHourList.add(hour);//库表有的时间点
				}
				List<YhLightHouceData> dataList=data_map.get(hour);
				if(dataList==null){
					dataList=new LinkedList<YhLightHouceData>();
				}
				dataList.add(e);
				data_map.put(hour, dataList);
			}
		}
		//不足24个十点段则对0~23时间点,没有的补充0
		if(!hasHourList.isEmpty()&&hasHourList.size()<24){
			for(int i=0;i<24;i++){
				if(!hasHourList.contains(i)){
					List<YhLightHouceData> notExitTableData=new LinkedList<YhLightHouceData>();
					for(int k=0;k<8;k++){
						YhLightHouceData data=new YhLightHouceData();
						data.setHour(i);
						data.setHourText(swithchText(i));
						data.setType(k);
						data.setFailRate(0.0);
						data.setCount(0);
						data.setSuccnt(0);
						notExitTableData.add(data);
					}
					data_map.put(i, notExitTableData);
				}
			}
		}
		List<YhLightHouceData> returnData=new LinkedList<YhLightHouceData>();
		returnData=rowToCows(data_map);
		//按时间点排序
		Collections.sort(returnData, new Comparator<YhLightHouceData>() {
			public int compare(YhLightHouceData o1, YhLightHouceData o2) {
				return o1.getHour().compareTo(o2.getHour());
			}
		});
		return returnData;
		
	}
	// 各时间点各环节失败率行转列
	private List<YhLightHouceData> rowToCows(Map<Integer,List<YhLightHouceData>> data_map){
		List<Entry<Integer, List<YhLightHouceData>>> entryList=new ArrayList<Entry<Integer, List<YhLightHouceData>>>(data_map.entrySet());
		List<YhLightHouceData> resultData=new LinkedList<YhLightHouceData>();
		//
		for(Entry<Integer, List<YhLightHouceData>> entry:entryList){
			int hour=entry.getKey().intValue();
			YhLightHouceData data=new YhLightHouceData();
			List<YhLightHouceData> values=entry.getValue();
			data.setHour(hour);
			data.setHourText(swithchText(hour));
			if(!values.isEmpty()){
				for(YhLightHouceData e:values){
					switch (e.getType()) {
					case 0:
						data.setStartGameFailcntRate(e.getFailRate());
					     break;
					case 1:
						data.setGameInitFailcntRate(e.getFailRate());
						break;
					case 2:
						data.setDecompDataFailcntRate(e.getFailRate());
						break;
					case 3:
						data.setCheckUpdateFailcntRate(e.getFailRate());
						break;
					case 4:
						data.setUpdateResFailcntRate(e.getFailRate());
						break;
					case 5:
						data.setLoginAuthFailcntRate(e.getFailRate());
						break;
					case 6:
						data.setLoginGameServerFailcntRate(e.getFailRate());
						break;
					case 7:
						data.setEnterGameFailcntRate(e.getFailRate());
						break;
					default:
						data.setEnterGameFailcntRate(e.getFailRate());
						break;
					}
				}
			}
			resultData.add(data);
		}
		return resultData;
	}
	private String swithchText(int type){
		String str="";
		switch (type) {
		case 0:
			str="00:00";
			break;
		case 1:
			str="01:00";
			break;
		case 2:
			str="02:00";
			break;
		case 3:
			str="03:00";
			break;
		case 4:
			str="04:00";
			break;
		case 5:
			str="05:00";
			break;
		case 6:
			str="06:00";
			break;
		case 7:
			str="07:00";
			break;
		case 8:
			str="08:00";
			break;
		case 9:
			str="09:00";
			break;
		case 10:
			str="10:00";
			break;
		case 11:
			str="11:00";
			break;
		case 12:
			str="12:00";
			break;
		case 13:
			str="13:00";
			break;
		case 14:
			str="14:00";
			break;
		case 15:
			str="15:00";
			break;
		case 16:
			str="16:00";
			break;
		case 17:
			str="17:00";
			break;
		case 18:
			str="18:00";
			break;
		case 19:
			str="19:00";
			break;
		case 20:
			str="20:00";
			break;
		case 21:
			str="21:00";
			break;
		case 22:
			str="22:00";
			break;
		case 23:
			str="23:00";
			break;
		default:
			str="未知";
			break;
		}
		return str;
	}
	@Override
	public List<YhLoginStat> selectLoginSuccessTransformRate(
			BaseSearchData searchData) {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		List<YhLoginStat> list=yhLightHouceDataMapper.selectLoginSuccessTransformRate(searchData);
		List<YhLoginStat> resultList=new LinkedList<YhLoginStat>();
		if(list!=null&&!list.isEmpty()){
			for(int i=0;i<list.size();i++){
				YhLoginStat loginStat=list.get(i);
				if(loginStat.getType()==null){
					loginStat.setType(-1);
				}
				//除了启动游戏之外，其它环节的分母（总次数）为上一环节的成功次数
				if(loginStat.getType().intValue()>0&&loginStat.getType().intValue()<8){
					int index=i-1;
					YhLoginStat preLoginStat=list.get(index);
					loginStat.setCount(preLoginStat!=null?preLoginStat.getSuccnt():0);
				}
				//成功率=成功次数/总次数
				Double succnRate=loginStat.getSuccnt().doubleValue()/loginStat.getCount();
				loginStat.setSuccntRate(NumberUtil.multi100(succnRate, 2));
				resultList.add(loginStat);
				
			}
//			for(YhLoginStat e:list){
//					e.setSuccntRate(NumberUtil.multi100(e.getSuccntRate(), 2));
//					resultList.add(e);
//			}
		}
		return resultList;
	}
	@Override
	public List<YhLightHouceData> selectAvgDelayCountByInterval(
			BaseSearchData searchData) {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		List<YhLightHouceData> list=yhLightHouceDataMapper.selectAvgDelayCountByInterval(searchData);
		return list;
	}
	@Override
	public List<YhLightHouceData> selectAvgDelayTimeByHour(
			BaseSearchData searchData) {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		List<YhLightHouceData> list=yhLightHouceDataMapper.selectAvgDelayTimeByHour(searchData);
		List<Integer> hasHourList=new LinkedList<Integer>();//库表有的时间点
		List<YhLightHouceData> resultList=new LinkedList<YhLightHouceData>();
		if(list!=null&&!list.isEmpty()){
			for(YhLightHouceData e:list){
				hasHourList.add(e.getHour());
				e.setHourText(swithchText(e.getHour()));
			}
		}
		//不足24个时间点补充0
		if(!hasHourList.isEmpty()&&hasHourList.size()<24){
			for(int i=0;i<24;i++){
				//添加库表没有的时间点补充0
				if(!hasHourList.contains(i)){
					YhLightHouceData data=new YhLightHouceData();
					data.setHour(i);
					data.setHourText(swithchText(i));
					data.setAvgDelayTime_3G(0.0);
					data.setAvgDelayTime_4G(0.0);
					data.setAvgDelayTime_WIFI(0.0);
					resultList.add(data);
				}
			}
			//组合时间点数据
			if(list!=null&&!list.isEmpty()){
				for(YhLightHouceData e:list){
					resultList.add(e);//
				}
			}
		}else{
			resultList=list;
		}
		//排序时间点
		Collections.sort(resultList,new Comparator<YhLightHouceData>() {
			public int compare(YhLightHouceData o1, YhLightHouceData o2) {
				return o1.getHour().compareTo(o2.getHour());
			}
		});
		return resultList;
	}
	@Override
	public List<YhLightHouceData> selectFpsDistributeRateList(
			BaseSearchData searchData) {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		List<YhLightHouceData> list=yhLightHouceDataMapper.selectFpsDistributeRateList(searchData);
		if(!list.isEmpty()){
			for(YhLightHouceData e:list){
				e.setFpsRate(NumberUtil.multi100(e.getFpsRate(), 2));
			}
		}
		return list;
	}
	@Override
	public List<YhLightHouceData> selectSystemHFpsDistributeRateList(
			BaseSearchData searchData) {
		// TODO Auto-generated method stub
		searchData=searchData.searchDataFilter(searchData);
		List<YhLightHouceData> list=yhLightHouceDataMapper.selectSystemHFpsDistributeRateList(searchData);
		return list;
	}

}
