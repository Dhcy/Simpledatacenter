package com.good.plat.datacenter.independ.service.yhindex;

import java.util.List;

import com.good.plat.datacenter.common.base.BaseSearchData;
import com.good.plat.datacenter.independ.entity.yhindex.YhLightHouceData;
import com.good.plat.datacenter.independ.entity.yhindex.YhLoginStat;

public interface YhLightHouceDataService {
	/**
	 * 每个时间段每个环节的次数（成功跟失败）
	 * @Title: selectPerLinkCntForHour
	 * @Description: TODO
	 * @return
	 * List<YhLightHouceData>
	 * @author hwj
	 * @date 2018-5-14 下午01:18:12
	 */
	public List<YhLightHouceData> selectPerLinkCntForHour(BaseSearchData searchData) throws Exception;
	
	/**
	 * 登录成功转化率
	 * @Title: selectLoginSuccessTransformRate
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<YhLoginStat>
	 * @author hwj
	 * @date 2018-5-14 下午03:37:55
	 */
	public List<YhLoginStat> selectLoginSuccessTransformRate(BaseSearchData searchData);
	
	/**
	 * fps区间分布
	 * @Title: selectFpsDistributeRateList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<YhLightHouceData>
	 * @author hwj
	 * @date 2018-5-15 下午01:43:39
	 */
	public List<YhLightHouceData> selectFpsDistributeRateList(BaseSearchData searchData);
	/**
	 * 机型fps区间分布
	 * @Title: selectSystemHFpsDistributeRateList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<YhLightHouceData>
	 * @author hwj
	 * @date 2018-5-15 下午01:44:31
	 */
	public List<YhLightHouceData> selectSystemHFpsDistributeRateList(BaseSearchData searchData);
	/**
	 * 按时间段查询平均延迟时间
	 * @Title: selectAvgDelayTimeByHour
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<YhLightHouceData>
	 * @author hwj
	 * @date 2018-5-15 下午01:46:42
	 */
	public List<YhLightHouceData> selectAvgDelayTimeByHour(BaseSearchData searchData);
	/**
	 * 按区间段查询平均延迟次数
	 * @Title: selectAQvgDelayCountByInterval
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * List<YhLightHouceData>
	 * @author hwj
	 * @date 2018-5-15 下午01:48:35
	 */
	public List<YhLightHouceData> selectAvgDelayCountByInterval(BaseSearchData searchData);
}
