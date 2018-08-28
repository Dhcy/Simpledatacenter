package com.good.plat.datacenter.independ.mapper.yhindex;

import java.util.List;

import com.good.plat.datacenter.common.base.IndependBaseSearchData;
import com.good.plat.datacenter.independ.entity.yhindex.YhPlaymineStat;

/**
 * 银河采矿数据
 * @ClassName: YhPlaymineStatMapper
 * @Description: TODO
 * @author hwj
 * @date 2018-1-17 上午09:44:37
 */
public interface YhPlaymineStatMapper {
	/**
	 * 打矿成功率
	 * @Title: selectPlaymineSuccRateList
	 * @Description: TODO
	 * @param searchData
	 * @return
	 * @throws Exception
	 * List<YhPlaymineStat>
	 * @author hwj
	 * @date 2018-1-17 上午10:22:35
	 */
	public List<YhPlaymineStat> selectPlaymineSuccRateList(IndependBaseSearchData searchData)throws Exception;

}
