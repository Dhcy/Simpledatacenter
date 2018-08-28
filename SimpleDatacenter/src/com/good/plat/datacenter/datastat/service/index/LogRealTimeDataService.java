package com.good.plat.datacenter.datastat.service.index;

import java.util.Map;

/**
 * 日志实时数据
 * @ClassName: LogRealTimeDataService
 * @Description: TODO
 * @author hwj
 * @date 2017-8-23 下午05:38:52
 */
public interface LogRealTimeDataService {
	
	public Map<String, String> getAllValueDescByCode(
			String attrCode) throws Exception;

}
