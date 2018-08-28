package com.good.plat.datacenter.datastat.mapper.index;

import java.util.List;

import com.good.plat.datacenter.datastat.entity.index.LogTypeAtrrCodeValue;
/**
 * 日志实时数据
 * @ClassName: LogRealTimeDataMapper
 * @Description: TODO
 * @author hwj
 * @date 2017-8-21 下午04:48:34
 */
public interface LogRealTimeDataMapper {
	/**
	 * 根据静态编码获取日志类型的值及描述
	 * @Title: selectValueDescListByCode
	 * @Description: TODO
	 * @param LogRealTimeData
	 * @return
	 * List<LogRealTimeData>
	 * @author hwj
	 * @date 2017-8-21 下午04:51:37
	 */
	List<LogTypeAtrrCodeValue> selectLogTypeValueDescByCode (String attrCode);
	
}
