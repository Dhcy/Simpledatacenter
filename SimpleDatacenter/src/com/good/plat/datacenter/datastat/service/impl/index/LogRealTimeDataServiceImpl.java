package com.good.plat.datacenter.datastat.service.impl.index;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.good.plat.datacenter.datastat.entity.index.LogTypeAtrrCodeValue;
import com.good.plat.datacenter.datastat.mapper.index.LogRealTimeDataMapper;
import com.good.plat.datacenter.datastat.service.index.LogRealTimeDataService;
@Service(value="logRealTimeDataService")
public class LogRealTimeDataServiceImpl implements LogRealTimeDataService {
	@Autowired
	private LogRealTimeDataMapper logRealTimeDataMapper;
	@Override
	public Map<String, String> getAllValueDescByCode(String attrCode)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String,String> valueMap = new HashMap<String,String>();
		List<LogTypeAtrrCodeValue> list=logRealTimeDataMapper.selectLogTypeValueDescByCode(attrCode);
		if(list!=null&&list.size()>0){
			for(LogTypeAtrrCodeValue e:list){
				valueMap.put(e.getAttr_value(), e.getAttr_desc());
			}
		}
		return valueMap;
	}

}
